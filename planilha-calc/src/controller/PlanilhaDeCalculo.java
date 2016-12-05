package controller;

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

	private String getColumnName(String cell) {
		String columnName = cell.substring(0, 1);
		return columnName;
	}
	
	// Search for a column with a given name. E.g: "A", "K", and so on.
	private Coluna getColumn(String columnName){
		columnName = getColumnName(columnName);
		Node<Coluna> node = colunas.primeiro;
		for(int i=0; i < colunas.totalDeElementos; i++){
			if(node.valor().getColuna().equals(columnName)){
				return node.valor();
			}
			else{
				node = node.proximo();
			}
		}
		// In case of no columns with this name, create a new column and add it to the list.
		Coluna novaColuna = new Coluna(getColumnName(columnName));
		colunas.adiciona(novaColuna);
		return novaColuna;
	}

	public static void main(String[] args) {
		(new PlanilhaDeCalculo()).run();
	}

	private void run() {
		SpreadsheetModel m = new SpreadsheetModel(){

			@Override
			public String getValue(String cell) {
				return cell;
			}

			@Override
			public String getFormula(String cell) {
				return cell;
			}

			@Override
			public void setFormula(String cell, String formula) {
				
				System.out.println(getColumn(cell));
				System.out.println(colunas.toString());
				
				
//				System.out.println("Celula: " + cell + " - Formula: " + formula);
//				try {
//					System.out.println("Valor: " + ExpressionEvaluator.compute(formula));
//				} catch (InvalidExpression e) {
//					System.out.println("Erro na expressão!");
//				}
			}
		};		
		
		(new Spreadsheet(m)).start();
	}
}
