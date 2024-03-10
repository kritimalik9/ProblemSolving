package StacksQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class NextGreaterElementClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numTCs = scan.nextInt();

		int[][] arrList = new int[numTCs][];
		for (int i = 0 ; i < numTCs ; i++)
		{
			int size = scan.nextInt();
			arrList[i] = new int[size];
			for(int j = 0; j < size ; j++)
			{
				arrList[i][j] = scan.nextInt();
			}
		}

		for (int i = 0 ; i < numTCs ; i++)
		{
			NextGreaterElement(arrList[i]);
		}

	}

	private static void NextGreaterElement(int[] arr) {

		if(arr.length == 0)
		{
			return;
		}
		else if(arr.length == 1)
		{
			System.out.println(arr[0]+",-1");
			return;
		}

		Stack<Integer> stack = new Stack<>();

		stack.push(0);

		for(int i = 1 ; i < arr.length ; i++)
		{
			while((!stack.isEmpty()) && (arr[i] > arr[stack.peek()]))
			{
				System.out.println(arr[stack.peek()]+","+arr[i]);
				stack.pop();
			}
			stack.push(i);
		}

		while(!stack.isEmpty())
		{
			System.out.println(arr[stack.peek()]+",-1");
			stack.pop();
		}


	}

}
