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
***Thomas Lu
***Sept 11 2021
***OCS25

***Algorithms***
Algorithm for infixToPostfix: Looping through input string from left-right
1. Put numbers into output string
2. If operator:
   Check if any saved operators should go in front (and pop them)
   Otherwise, save for later (put in stack and wait until the second number is read)
3. If opening parenthesis: save in stack (delimiter for closing)
4. If closing parenthesis: pop all operators until reaching an opening parenthesis
5. Pop all operators saved for later

Algorithm for EvaluatePostFix:
1. Put numbers into a stack
2. When operator: Use most recent 2 numbers in stack to eval and put result in stack
3. At end of expression, only 1 number should be in stack, is the result

***Input Constraints***
All numbers in input expression should be positive integers
Supports +,-,*,/ and parentheses

***Sample Outputs***
Enter an expression in infix form
(12-1)/2
The infix expression is:
(12-1)/2
Converted to postfix form:
12 1 -2 /
Evaluated Result:
5.5

Enter an expression in infix form
12-1/2
The infix expression is:
12-1/2
Converted to postfix form:
12 1 2 /-
Evaluated Result:
11.5

Enter an expression in infix form
(1+2)*3+4*5
The infix expression is:
(1+2)*3+4*5
Converted to postfix form:
1 2 +3 *4 5 *+
Evaluated Result:
29.0

Enter an expression in infix form
((1+1))+1
The infix expression is:
((1+1))+1
Converted to postfix form:
1 1 +1 +
Evaluated Result:
3.0
*/

public class ArithmeticExpressionEvalulator {
    //checks if operator comes before
    private static int orderOf(char i)
    {
        switch(i)
        {
            case '*':
            case '/':
                return 2;
                
            case '+':
            case '-':
                return 1;
                
            default:
                return 0;
        }
    }
    
    public String InfixToPostfix(String input)
    {
        Stack<Character> cache = new Stack<>();
        String output = "";
        for(int i = 0; i < input.length(); i++)
        {
            char letter = input.charAt(i);
            if(Character.isDigit(letter))
            {
                output += letter;
                //handle multi-digit number 
                while(i+1 < input.length() && Character.isDigit(input.charAt(i+1)))
                {
                    letter = input.charAt(i+1);
                    output += letter;
                    i++;
                }
                output += ' ';
            }
            else if(orderOf(letter) > 0)
            {
                while(!cache.isEmpty())
                {
                    Character tempOp = cache.pop();
                    if(orderOf(letter) > orderOf(tempOp))
                    {
                        cache.push(tempOp);
                        break;
                    }
                    else
                    {
                        output += tempOp;
                    }
                }
                cache.push(letter);
            }
            else if(letter == '(')
            {
                cache.push(letter);
            }
            else if(letter == ')')
            {
                while(!cache.isEmpty())
                {
                    Character tempOp = cache.pop();
                    if(!(tempOp == '('))
                    {
                        output += tempOp;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        while(!cache.isEmpty())
        {
            output += cache.pop();
        }
        return output;
    }
    
    public double EvaluatePostfix(String input)
    {
        Stack<Double> cache = new Stack<>();
        for(int i = 0; i < input.length(); i++)
        {
            char letter = input.charAt(i);
            if(Character.isDigit(letter))
            {
                String number = "";
                number += letter;
                //handle multi-digit number 
                while(i+1 < input.length() && Character.isDigit(input.charAt(i+1)))
                {
                    letter = input.charAt(i+1);
                    number += letter;
                    i++;
                }
                cache.push(Double.parseDouble(number));
            }
            else if(orderOf(letter) > 0)
            {
                double num2 = cache.pop();
                double num1 = cache.pop();
                double result = 0;
                switch(letter)
                {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                cache.push(result);
            }
        }
        return cache.pop();
    }
}
