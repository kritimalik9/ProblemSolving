package DynamicProgramming;

import java.util.Scanner;

public class IsSubsetSumEqualToGivenSum {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int arrLen = scan.nextInt();
		int[] arr = new int[arrLen];
		int sum = scan.nextInt();
		for(int i = 0 ; i < arr.length ; i++)
		{
			arr[i] = scan.nextInt();
		}
		boolean isSubsetSumEqualToGivenSum = IsSubsetSumEqualToGivenSumIterativeDp(arr,sum);

		if(isSubsetSumEqualToGivenSum)
		{
			System.out.println("Yes");	
		}
		else
		{
			System.out.println("No");	
		}


	}

	private static boolean IsSubsetSumEqualToGivenSumIterativeDp(int[] arr, int sum) {

		boolean T[][] = new boolean[arr.length+1][sum+1];

		for(int i = 0 ; i <= arr.length ; i++)
		{
			for(int j = 0 ; j <= sum ; j++)
			{
				if( i==0 || j == 0)
				{
					T[i][j] = false;
					continue;
				}
				int p = i-1;
				if(j < arr[p])
				{
					T[i][j] = T[i-1][j];
				}
				else if(j == arr[p])
				{
					T[i][j] = true;
				}
				else
				{
					T[i][j] = T[i-1][j] || T[i-1][j-arr[p]];
				}
			}
		}

		return T[arr.length][sum];
	}

}
