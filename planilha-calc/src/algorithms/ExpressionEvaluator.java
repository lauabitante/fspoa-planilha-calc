package algorithms;

import java.util.Locale;
import java.util.Scanner;

import datastructures.Stack;

/**
 * This class provides an arithmetic expression evaluator, using a
 * stack evaluator.
 */
public class ExpressionEvaluator {
	
	private static String toPostfix(String expression, Locale locale)
			throws InvalidExpression
	{
		Scanner sc = new Scanner(expression);
		sc.useLocale(locale);
		String out = "";
		
		Stack<Operator> opStack = new Stack<>(expression.length()/2);
		
		while (sc.hasNext()) {
			if (sc.hasNext(Operator.REGEX())) {
				String op = sc.next();
				Operator oper = Operator.select(op);
				switch (oper) {
					case OPENPAR:
						opStack.push(oper);
						break;
					case CLOSEPAR:
						while (opStack.peek() != Operator.OPENPAR)
							out += " " + opStack.pop();
						opStack.pop();
						break;
					default:
						while(!opStack.isEmpty()
							  && opStack.peek().priority > oper.priority)
							out += " " + opStack.pop();
						opStack.push(oper);
						break;
				}
			} else {
				// it is assumed that what is not an operand,
				// so it is a value, variable or function.
				out += " " + sc.next().toUpperCase();
			}
		}
		while (!opStack.isEmpty())
			out += " " + opStack.pop();

		sc.close();
		return out;
	}
	
	/**
 	 * Computes the value of a given infix arithmetic expression. The
 	 * expresion is parsed used the number rules for en-US locale.
 	 * @param exp The arithmetic expression.
 	 * @return The result of the expression. 
	 * @throws InvalidExpression 
 	 */
	public static double compute(String exp) throws InvalidExpression {
		return compute(exp,Locale.US);
	}

	/**
 	 * Computes the value of a given infix arithmetic expression. The
 	 * expresion is parsed used the number rules of a given locale.
 	 * @param exp The arithmetic expression.
 	 * @param locale The locale rules to use.
 	 * @return The result of the expression. 
	 * @throws InvalidExpression 
 	 */
	public static double compute(String exp, Locale locale)
			throws InvalidExpression
	{
		// Add spaces around operators.
		String operators = Operator.REGEX();
		String rep = ("0 "+exp).replaceAll("("+operators+")"," $1 ");
		// Convert expression from postfix to infix.
		String expression = toPostfix(rep,locale);
		// Use a Scanner to tokenize expression.
		Scanner sc = new Scanner(expression);
		sc.useLocale(locale);
		// Avalia a expressao.
		Stack<Double> operands = new Stack<Double>(exp.length());
		while (sc.hasNext()) {
			if (sc.hasNext(operators)) {
				// if it's an operator, execute operation.
				double rhs = operands.pop();
				double lhs = operands.pop();
				double result = Operator.select(sc.next()).execute(lhs, rhs);
				operands.push(result);
			} else {
				if (sc.hasNextDouble()) {
					// if it's a value, push to stack.
					operands.push(sc.nextDouble());
				}
			}
		}
		sc.close();
		double result = operands.pop();
		// first element might be a "zero" to cope with expresions starting
		// with negative values.
		if (!operands.isEmpty()) {
			if (operands.pop() != 0 || !operands.isEmpty()) {
				throw new InvalidExpression(exp);
			}
		}
		return result;
	}

}
