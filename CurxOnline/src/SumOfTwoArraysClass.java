import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SumOfTwoArraysClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the array 1 : ");
		int N = scan.nextInt();
		if(N <= 0 || N > 1000)
		{
			System.out.println("Invalid size of input array");
		}
		else
		{
			int[] arr1 = new int[N];
			for(int i=0; i<N; i++)
			{
				System.out.println("Enter element arr1["+i+"] = ");
				arr1[i] = scan.nextInt();
			}
			System.out.println("Enter the size of the array 2 : ");
			int M = scan.nextInt();
			if(M <= 0 || M > 1000)
			{
				System.out.println("Invalid size of input array");
			}
			else
			{
				int[] arr2 = new int[M];
				for(int i=0; i<M; i++)
				{
					System.out.println("Enter element arr2["+i+"] = ");
					arr2[i] = scan.nextInt();
				}
				System.out.println("Sum Of 2 arrays : ");
				int[] twoArraySum = SumOfTwoArrays(arr1,arr2);
				boolean zerosAtStart = false;
				boolean isTwoArraySumStart = false;
				for(int i=0 ; i< twoArraySum.length ; i++)
				{
					if((!isTwoArraySumStart) && (twoArraySum[i] == 0))
					{
						zerosAtStart = true;
					}
					else
					{
						System.out.print(twoArraySum[i]+", ");
						isTwoArraySumStart = true;
					}
				}
				System.out.println("END");

			}
		}
	}

	private static int[] SumOfTwoArrays(int[] arr1, int[] arr2) {
		int twoArraySumLen = (arr1.length > arr2.length) ? arr1.length+1 : arr2.length+1;
		int[] twoArraySum = new int[twoArraySumLen];
		int carry = 0;
		if(arr1.length > arr2.length)
		{
			int diff = arr1.length - arr2.length;
			int twoArraySumIdx = arr1.length;
			for(int i = arr2.length-1 ; i >= 0 ; i--)
			{
				twoArraySum[twoArraySumIdx] = carry + arr1[i+diff] + arr2[i] ;
				carry = twoArraySum[twoArraySumIdx] / 10 ;
				twoArraySum[twoArraySumIdx] = twoArraySum[twoArraySumIdx] % 10;
				twoArraySumIdx--;
			}
			while(diff > 0) {
				twoArraySum[twoArraySumIdx] = carry + arr1[diff-1];
				carry = twoArraySum[twoArraySumIdx] / 10 ;
				twoArraySum[twoArraySumIdx] = twoArraySum[twoArraySumIdx] % 10;
				twoArraySumIdx--;
				diff--;
			}
			if(carry != 0)
			{
				twoArraySum[twoArraySumIdx] = carry;
			}
		}
		else
		{
			int diff = arr2.length - arr1.length;
			int twoArraySumIdx = arr2.length;
			for(int i = arr1.length-1 ; i >= 0 ; i--)
			{
				twoArraySum[twoArraySumIdx] = carry + arr1[i] + arr2[i+diff] ;
				carry = twoArraySum[twoArraySumIdx] / 10 ;
				twoArraySum[twoArraySumIdx] = twoArraySum[twoArraySumIdx] % 10;
				twoArraySumIdx--;
			}
			while(diff > 0) {
				twoArraySum[twoArraySumIdx] = carry + arr2[diff-1];
				carry = twoArraySum[twoArraySumIdx] / 10 ;
				twoArraySum[twoArraySumIdx] = twoArraySum[twoArraySumIdx] % 10;
				twoArraySumIdx--;
				diff--;
			}
			if(carry != 0)
			{
				twoArraySum[twoArraySumIdx] = carry;
			}
		}
		return twoArraySum;
	}
}
