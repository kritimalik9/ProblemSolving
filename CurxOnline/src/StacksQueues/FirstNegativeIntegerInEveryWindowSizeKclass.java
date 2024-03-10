package StacksQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FirstNegativeIntegerInEveryWindowSizeKclass {

	private static final Queue<Integer> Queue = null;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numTCs = scan.nextInt();

		int[][] arrList = new int[numTCs][];
		int[] windowSize = new int[numTCs]; 
		for (int i = 0 ; i < numTCs ; i++)
		{
			int size = scan.nextInt();
			windowSize[i] = scan.nextInt();
			arrList[i] = new int[size];
			for(int j = 0; j < size ; j++)
			{
				arrList[i][j] = scan.nextInt();
			}
		}

		for (int i = 0 ; i < numTCs ; i++)
		{
			int[] outList = FirstNegativeIntegerInEveryWindowSizeK(arrList[i],windowSize[i]);
			if(outList != null)
			{
				for(int val : outList)
				{
					System.out.print(val + " ");
				}
			}
			System.out.println();
		}

	}

	private static int[] FirstNegativeIntegerInEveryWindowSizeK(int[] arr, int k) {

		if(arr.length < k)
		{
			return null;
		}
		int[] out = new int[arr.length-k+1];
		Queue<Integer> q = new LinkedList<>();

		for(int i = 0; i < k; i++ )
		{
			q.add(i);
		}

		int count = 0;
		int j = 0;
		for(int i = k; i < arr.length; i++ )
		{
			if(arr[q.peek()] < 0)
			{
				out[j] = arr[q.peek()];
				j++;
				while(count != 0)
				{
					out[j] = arr[q.peek()];
					j++;
					count--;
				}
			}
			else
			{
				count++;
			}

			if(count == k)
			{
				out[j] = 0;
				count--;
				j++;
			}

			q.poll();
			q.add(i);
		}
		out[out.length-1] = 0;
		while(!q.isEmpty())
		{
			if(arr[q.peek()] < 0)
			{
				out[j] = arr[q.peek()];
				j++;
				while(count != 0)
				{
					out[j] = arr[q.peek()];
					j++;
					count--;
				}
				break;
			}			
			q.poll();	
		}
		return out;
	}

}
