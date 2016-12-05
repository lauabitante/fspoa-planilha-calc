package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import algorithms.ExpressionEvaluator;
import algorithms.InvalidExpression;
import datastructures.Celula;
import datastructures.Coluna;
import datastructures.ListaEncadeada;
import datastructures.Node;
import model.SpreadsheetModel;
import view.Spreadsheet;

public class PlanilhaDeCalculo {

	private ListaEncadeada<Coluna> colunas = new ListaEncadeada<>();

	// This function will return the column name from a cell name. E.g: "A1" -> "A"
	private String getColumnName(String cell) {
		String columnName = cell.substring(0, 1);
		return columnName;
	}
	
	// Search for a column with a given name. E.g: "A", "K", and so on.
	private Coluna getColumn(String columnName){
		columnName = getColumnName(columnName); // Remove the number from columnName
		Node<Coluna> node = colunas.primeiro;
		for(int i=0; i < colunas.totalDeElementos; i++){
			// If the column name is the same, then return the node value.
			if(node.valor().getColuna().equals(columnName)){
				return node.valor();
			}
			else{
				// Otherwise, go to the next
				node = node.proximo();
			}
		}
		// In case of no columns with this name, create a new column and add it to the list.
		Coluna novaColuna = new Coluna(columnName);
		colunas.adiciona(novaColuna);
		return novaColuna;
	}

	// Check if string is a number
	public boolean isNumeric(String str) {
	    return str.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$");
	}
	
	// Calculate a formula
	private String calculateFormula(String formula) {
		// Math expression regex
		String calcRegex = "([-+]?[0-9]*\\.?[0-9]+[\\/\\+\\-\\*])+([-+]?[0-9]*\\.?[0-9]+)";
		// Creating the pattern matcher.
	    Matcher cellMatcher = Pattern.compile("[A-Z]{1}\\d+").matcher(formula);
	    
	    // Checking for occurencies of cell names in the expression.
	    while (cellMatcher.find()) {
	        String cell = cellMatcher.group(); // E.g: A1
	        Coluna column = getColumn(cell);
			Celula c = column.getCelula(cell);
			// Replace string E.g: From A1+2 to 2+2
	        formula = formula.replace(cell, ""+calculateFormula(c.formula.getFormula()));
	    }

	    // If it's a number, print it.
	    if (isNumeric(formula)) {
	    	return formula;
	    }
	    
	    // Otherwise, compute the formula.
	    Pattern p = Pattern.compile(calcRegex);
	    if (p.matcher(formula).matches()) {
	    	try {
				return ""+ExpressionEvaluator.compute(formula);
			} catch (InvalidExpression e) {
				return "#ERRO#"; // Returning an error in case of calculation error
			}
	    } else {
	    	return "#ERRO#"; // Returning an error in case of invalid math expression
	    }
	}

	public static void main(String[] args) {
		(new PlanilhaDeCalculo()).run();
	}

	private void run() {
		SpreadsheetModel m = new SpreadsheetModel(){

			@Override
			//This function will return the value to be printed in a cell
			public String getValue(String cell) {
				Coluna column = getColumn(cell);
				Celula c = column.getCelula(cell);
				String result = "";
				// If the cell doesn't have a formula, return the cell name
				if (c.formula.getFormula().isEmpty()) {
					result = cell;	
				} else {
					// If so, calculate the formula and return it.
					result = calculateFormula(c.formula.getFormula());
				}
				return result;
			}

			@Override
			// This function will return the formula inside a cell
			public String getFormula(String cell) {
				Coluna column = getColumn(cell);
				Celula c = column.getCelula(cell);
				return c.formula.getFormula();
			}

			@Override
			// This function will set a formula according to a given cell name
			public void setFormula(String cell, String formula) {
				Coluna column = getColumn(cell); //Get column
				Celula c = column.getCelula(cell); // Get cell
				c.formula.setFormula(formula); // Set formula
			}
		};		
		(new Spreadsheet(m)).start();
	}
}
