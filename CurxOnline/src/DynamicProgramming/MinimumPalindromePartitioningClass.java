package DynamicProgramming;

import java.util.Scanner;

public class MinimumPalindromePartitioningClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numTC = scan.nextInt();
		String[] input = new String[numTC];
		for(int i = 0 ; i < numTC ; i++)
		{
			input[i] = scan.next();
		}

		for(int i = 0 ; i < numTC ; i++)
		{
			int numPartitions = MinimumPalindromePartitioningIterativeDp(input[i]);
			System.out.println(numPartitions);
		}
	}

	private static int MinimumPalindromePartitioningIterativeDp(String input) {

		int[][] T = new int[input.length()][input.length()];
		for(int i = 0; i < T.length; i++)
		{
			T[i] = new int[input.length()];
			T[i][i] = 0;
		}

		for(int j = 1 ; j < T.length ; j++)
		{
			for(int i = 0,k=j ; i+k < T[i].length ; i++)
			{
				boolean isPal = true;
				int lastELement = 0;
				for(int p = i ; p < ((k+1)/2+i) ; p++)
				{
					if(input.charAt(p) != input.charAt(i+k-lastELement))
					{
						isPal = false;
						break;
					}
					lastELement++;
				}
				if(isPal)
				{
					T[i][i+k] = 0;
				}
				else
				{	
					int minPrevPartitions = Integer.MAX_VALUE;
					for(int q = i ; q < i+k ; q++)
					{
						minPrevPartitions = Math.min(minPrevPartitions, T[i][q]+T[q+1][i+k]);
					}
					T[i][i+k] = 1+minPrevPartitions;
				}
			}
		}
		return T[0][T.length-1];
	}

}
