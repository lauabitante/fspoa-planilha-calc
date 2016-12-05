package controller;

import algorithms.ExpressionEvaluator;
import algorithms.InvalidExpression;
import datastructures.Celula;
import datastructures.Coluna;
import datastructures.ListaEncadeada;
import model.SpreadsheetModel;
import view.Spreadsheet;

public class PlanilhaDeCalculo {

	private ListaEncadeada<Coluna> colunas = new ListaEncadeada<>();

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
				System.out.println("Celula: " + cell + " - Formula: " + formula);
				try {
					System.out.println("Valor: " + ExpressionEvaluator.compute(formula));
				} catch (InvalidExpression e) {
					System.out.println("Erro na expressão!");
				}
			}
		};		
		
		(new Spreadsheet(m)).start();
	}
}
