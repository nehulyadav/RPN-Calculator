import java.io.IOException;
import java.util.*;

/*  Author: Nehul Yadav
 *  Unique id: yadavn
 */

public class ReversePolishNotation {
	String fileName = null;
	/* Initializing a string Stack which takes its argument as expressions in the file denoted as reverse polish notations. */

	private static Stack<String> stack = new Stack<String>();
	/*To create a new blank List<String> where the each line passed is the a different expressions in reverse polish notation respectively.*/
	private static String evaluateExpressionFromFile(String fileName) {
		List<String> expressions = readLinesFromFile(fileName);
		for(String expr : expressions) {
			String[] tokens = expr.split("\\w+");
			for(String token : tokens) {
				int a, b;
				/*It is important to use the switch statement in this case, because we have 5 operators
				 *  that are defined and each has an independent function in the program. */
				
				/* JAVA 1.8 feature*/
				switch(token) {
				
				//+ :  representing integer addition
				case "+": 
					a = Integer.valueOf(stack.pop());
					b = Integer.valueOf(stack.pop());
					stack.push(String.valueOf(a+b));
					break;
					//- :  representing integer subtraction
				case "-" : 

					a = Integer.valueOf(stack.pop());
					b = Integer.valueOf(stack.pop());
					stack.push(String.valueOf(b-a));
					break;
					//× : representing integer multiplication (denoted by the symbol '*')
				case "*": 
					a = Integer.valueOf(stack.pop());
					b = Integer.valueOf(stack.pop());
					stack.push(String.valueOf(b*a));
					break;
					// / – representing integer division
				case "/": 
					a = Integer.valueOf(stack.pop());
					b = Integer.valueOf(stack.pop());
					stack.push(String.valueOf(b/a));
					break;
					// 'mod' :  representing the modulus operator (denoted by the symbol '%')

				case "%": 
					a = Integer.valueOf(stack.pop());
					b = Integer.valueOf(stack.pop());
					stack.push(String.valueOf(b%a));
					break;
					// If the token is none of the operator above, the default case is performed that is pop the top 2 numbers off the stack. 
				default: 
					stack.push(token);

					System.out.println("Expression: "+ expr);
					System.out.println("Evaluates: " + stack.pop());

				}
				return stack.peek();
			}
		}
	}

	/*Quick and efficient way of reading a file in Java 1.8.
	 *To use 'File' class below and implementing it in the form of a Stream than a simple List.
	 * Catching the IO Exception and printing an error message for the user prompting the fileName*/
	private static List<String> readLinesFromFile(String fileName) {
		try(Stream<String> lines = Files.lines(new File(fileName).toPath())) {
			return lines.collect(Collectors.toList());
		} catch (IOException exception) {
			System.err.printf("unable to read the file: %s\n", fileName);
			return new ArrayList<String>(); 

		}
	}
}


