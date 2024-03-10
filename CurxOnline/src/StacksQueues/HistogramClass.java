package StacksQueues;

import java.util.Scanner;
import java.util.Stack;

public class HistogramClass {

	public static void main(String[] args)
	{
		Stack<Integer> minValOrLargerValIdxStack = new Stack<>();

		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		long[] arr = new long[size];
		for(int i = 0; i < size ; i++)
		{
			arr[i] = scan.nextInt();
		}

		long maxArea = Histogram(arr,minValOrLargerValIdxStack);
		System.out.println(maxArea);

	}

	private static long Histogram(long[] arr, Stack<Integer> minValOrLargerValIdxStack) {

		long maxArea = 0;
		int i;
		for(i = 0 ; i < arr.length; i++)
		{
			if(i == 0)
			{
				minValOrLargerValIdxStack.push(i);
				continue;
			}
			while(arr[i] < arr[minValOrLargerValIdxStack.peek()])
			{
				int index_out = minValOrLargerValIdxStack.pop();
				if(minValOrLargerValIdxStack.isEmpty())
				{
					maxArea = Math.max(maxArea,arr[index_out]*i);
					break;
				}
				else
				{
					maxArea = Math.max(maxArea,arr[index_out]*(i-minValOrLargerValIdxStack.peek()-1));
				}
			}
			minValOrLargerValIdxStack.push(i);

		}

		while(!minValOrLargerValIdxStack.isEmpty())
		{
			int index_out = minValOrLargerValIdxStack.pop();
			if(minValOrLargerValIdxStack.isEmpty())
			{
				maxArea = Math.max(maxArea,arr[index_out]*i);
				break;
			}
			else
			{
				maxArea = Math.max(maxArea,arr[index_out]*(i-minValOrLargerValIdxStack.peek()-1));
			}
		}
		return maxArea;
	}


}
