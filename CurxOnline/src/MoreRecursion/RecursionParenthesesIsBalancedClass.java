package MoreRecursion;

import java.util.Scanner;

public class RecursionParenthesesIsBalancedClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String expression = scan.nextLine();

		boolean IsBalanced = ParenthesesIsBalanced(expression,"");
		System.out.println(IsBalanced);

	}

	private static boolean ParenthesesIsBalanced(String expression,String bracesCollect) {

		if(expression.length() == 0)
		{
			if(bracesCollect.length() == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		boolean IsBalanced = false;

		if((expression.charAt(0) == '{') 
				||(expression.charAt(0) == '[')
				||(expression.charAt(0) == '(')
				)
		{
			IsBalanced = ParenthesesIsBalanced(expression.substring(1, expression.length()),bracesCollect+expression.charAt(0));
		}
		else if((expression.charAt(0) == '}') 
				||(expression.charAt(0) == ']')
				||(expression.charAt(0) == ')')
				)
		{
			if((expression.charAt(0) == '}')
					&& ((bracesCollect.length() == 0)
							|| (bracesCollect.charAt(bracesCollect.length()-1) != '{')))
			{
				return false;
			}
			else if((expression.charAt(0) == ']')
					&& ((bracesCollect.length() == 0)
							|| (bracesCollect.charAt(bracesCollect.length()-1) != '[')))
			{
				return false;
			}
			else if((expression.charAt(0) == ')')
					&& ((bracesCollect.length() == 0)
							||(bracesCollect.charAt(bracesCollect.length()-1) != '(')))
			{
				return false;
			}
			IsBalanced = ParenthesesIsBalanced(expression.substring(1, expression.length()),bracesCollect.substring(0,bracesCollect.length()-1));
		}
		else
		{
			IsBalanced = ParenthesesIsBalanced(expression.substring(1, expression.length()),bracesCollect);
		}
		return IsBalanced;
	}

}
