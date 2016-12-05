package datastructures;

import algorithms.ExpressionEvaluator;
import algorithms.InvalidExpression;

public class Formula {

	private String formula;
	
	public double getValue() throws InvalidExpression {
		return ExpressionEvaluator.compute(formula);
	}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public String getFormula() {
		return this.formula;
	}
	
}
