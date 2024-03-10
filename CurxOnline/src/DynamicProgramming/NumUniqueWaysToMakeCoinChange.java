package DynamicProgramming;

import java.util.ArrayList;
import java.util.Scanner;

public class NumUniqueWaysToMakeCoinChange {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int numCents = scan.nextInt();
		int numCoinTypes = scan.nextInt();

		int[] coinVal = new int[numCoinTypes];

		for(int i = 0; i< coinVal.length;i++)
		{
			coinVal[i] = scan.nextInt();
		}

		int count = NumUniqueWaysToMakeCoinChangeIterative(numCents,coinVal);

		System.out.println(count);


	}

	private static int NumUniqueWaysToMakeCoinChangeIterative(int numCents, int[] coinVal) 
	{
		int[][] T = new int[coinVal.length+1][numCents+1];

		for(int i = 0; i <= coinVal.length ; i++)
		{
			for(int j = 0; j <= numCents; j++)
			{
				if(i == 0 || j == 0)
				{
					T[i][j] = 0;
					continue;
				}
				int p = i-1;
				if(coinVal[p] > j)
				{
					T[i][j] = T[i-1][j];
				}
				else if(coinVal[p] == j)
				{
					T[i][j] = 1+T[i-1][j];
				}
				else
				{
					T[i][j] = T[i-1][j] + T[i][j-coinVal[p]];
				}
			}
		}

		return T[coinVal.length][numCents];
	}

}
