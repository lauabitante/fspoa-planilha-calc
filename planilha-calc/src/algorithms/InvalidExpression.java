package algorithms;

public class InvalidExpression extends Exception {
	private static final long serialVersionUID = -438560909159811265L;

	public InvalidExpression(String msg) {
		super("Invalid expression: " + msg);
	}
}
