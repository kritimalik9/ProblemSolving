package StacksQueues;

import java.util.Scanner;
import java.util.Stack;

public class ReverseStackUsingRecursiionClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int stackSize = scan.nextInt();
		Stack<Integer> inputStack = new Stack<>();
		for(int i = 0; i < stackSize; i++)
		{
			inputStack.push(scan.nextInt());
		}
		Stack<Integer> helperStack = new Stack<>();

		ReverseStackUsingRecursiion(0,inputStack,helperStack);

		while(!inputStack.isEmpty())
		{
			System.out.println(inputStack.pop());
		}
	}

	private static void ReverseStackUsingRecursiion(int idx , Stack<Integer> inputStack, Stack<Integer> helperStack) {
		if(inputStack.isEmpty())
		{
			return;
		}
		int item = inputStack.pop();
		ReverseStackUsingRecursiion(idx+1,inputStack, helperStack);
		helperStack.push(item);
		if(idx == 0)
		{
			while(!helperStack.isEmpty())
			{
				inputStack.push(helperStack.pop());
			}
		}
	}

}
