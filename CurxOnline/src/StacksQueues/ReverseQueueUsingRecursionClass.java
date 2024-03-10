package StacksQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ReverseQueueUsingRecursionClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int queueSize = scan.nextInt();
		Queue<Integer> inputQ = new LinkedList<>();
		for(int i = 0; i < queueSize; i++)
		{
			inputQ.add(scan.nextInt());
		}
		ReverseQueueUsingRecursion(inputQ);

		while(!inputQ.isEmpty())
		{
			System.out.print(inputQ.poll()+" ");
		}
		System.out.println("END");
	}

	private static void ReverseQueueUsingRecursion(Queue<Integer> inputQ) {
		if(inputQ.isEmpty())
		{
			return;
		}
		int item = inputQ.poll();
		ReverseQueueUsingRecursion(inputQ);
		inputQ.add(item);
	}
}
