package algorithms;

import java.util.function.BinaryOperator;

enum Operator {
	// To extend the calculator, add the operands here.
	OPENPAR("(", (a,b)-> 0.0, 0),
	CLOSEPAR(")", (a,b)-> 0.0, 0),
	PLUS("+", (a,b) -> a + b, 1),
	MINUS("-", (a,b) -> a - b, 1),
	TIMES("*", (a,b) -> a * b, 2),
	DIVIDE("/", (a,b) -> a / b, 2),
	POWER("^", (a,b) -> Math.pow(a,b), 3),
	MODULUS("%", (a,b) -> (double)((int)Math.round(a)) % ((int)Math.round(b)), 2);

	public static Operator select(String op) throws InvalidExpression {
		for (Operator oper : values()) {
			if (oper.repr.equals(op))
				return oper;
		}
		throw new InvalidExpression("Operand " + op + " is unknown.");
	}
	
	public static String REGEX() {
		String ops = "";
		for (Operator oper : values())
			ops += oper.repr;
		String regex =  ops.replaceAll("(.)", "\\\\$1");
		
		return "[" + regex + "]";
	}
	
	private final BinaryOperator<Double> operation;
	public final int priority;
	private final String repr;
	
	private Operator(String repr, BinaryOperator<Double> oper, int priority) {
		this.repr = repr;
		this.operation = oper;
		this.priority = priority;
	}
	public double execute(double a, double b) {
		return operation.apply(a,b);
	}
	public String toString() {
		return repr;
	}
};
