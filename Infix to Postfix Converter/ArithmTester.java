/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsl62
 */
/*
    ***More Documentation in Evaluator class***
*/
import java.util.*;
public class ArithmTester {
    public static void main(String[] args)
    {
        ArithmeticExpressionEvalulator eval1 = new ArithmeticExpressionEvalulator();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an expression in infix form");
        String toEval = input.nextLine();
        System.out.println("The infix expression is:\n" + toEval);
        String postfixForm = eval1.InfixToPostfix(toEval);
        System.out.println("Converted to postfix form:\n" + postfixForm);
        System.out.println("Evaluated Result:\n" + eval1.EvaluatePostfix(postfixForm));
    }
}
    
