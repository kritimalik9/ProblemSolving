package StacksQueues;

import java.util.Scanner;
import java.util.Stack;

public class CheckDuplicateParanthesisClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String str = scan.nextLine();

		boolean hasDuplicate = CheckDuplicateParanthesis(str);
		System.out.println(hasDuplicate);

	}

	private static boolean CheckDuplicateParanthesis(String str) {

		Stack<Character> stack = new Stack<>();
		boolean hasDuplicate = false;

		for(int i = 0; i< str.length(); i++)
		{
			if(str.charAt(i) != ')')
			{
				stack.push(str.charAt(i));
			}
			else
			{
				hasDuplicate = true;
				while(stack.peek() != '(')
				{
					stack.pop();
					hasDuplicate = false;
				}
				stack.pop();
				if(hasDuplicate)
				{
					return hasDuplicate;
				}
			}
		}

		return false;
	}

}
