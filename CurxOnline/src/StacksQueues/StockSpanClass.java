package StacksQueues;

import java.util.Scanner;
import java.util.Stack;

public class StockSpanClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		int[] arr = new int[size];
		for(int i = 0; i < size ; i++)
		{
			arr[i] = scan.nextInt();
		}
		StockSpan(arr);
	}

	private static void StockSpan(int[] arr) {


		Stack<Integer> stack = new Stack<>();
		System.out.print(1 + " ");
		stack.push(0);
		for(int i = 1; i < arr.length; i++)
		{
			if((arr[i-1] <= arr[i]))
			{
				while (!stack.isEmpty() && (arr[stack.peek()] <= arr[i]))
				{
					stack.pop();
				}
				if(stack.isEmpty())
				{
					System.out.print((i+1) + " ");
				}
				else
				{
					System.out.print((i-stack.peek()) + " ");	
				}
			}
			else
			{
				System.out.print(1 + " ");
			}
			stack.push(i);
		}
		System.out.print("END");

	}

}
