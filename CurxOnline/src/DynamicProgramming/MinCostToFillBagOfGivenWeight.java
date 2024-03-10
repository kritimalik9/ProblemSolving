package DynamicProgramming;

import java.util.Scanner;

public class MinCostToFillBagOfGivenWeight {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numFriends = scan.nextInt();
		int W = scan.nextInt();

		int[] packets = new int[W];

		//		int countPktsNotForSale = 0;
		for(int i = 0 ; i < W ; i++)
		{
			packets[i] = scan.nextInt();
			if(packets[i] == -1)
			{
				packets[i] = Integer.MAX_VALUE;
			}
		}
		//		int[] pktsForSale = new int[W-countPktsNotForSale];
		//
		//		int j=0;
		//		for(int i = 0 ; i < W ; i++)
		//		{
		//			if(packets[i] == -1)
		//			{
		//				continue;
		//			}
		//			pktsForSale[j++] = packets[i]; 
		//		}

		int minCost = MinCostToFillBagOfGivenWeight(W,packets);
		System.out.println(minCost);

	}

	private static int MinCostToFillBagOfGivenWeight(int W, int[] pkts) 
	{
		int[][] T = new int[W+1][pkts.length+1];

		for(int i = 0 ; i <= pkts.length ; i++)
		{
			for(int j = 0 ; j <= W ; j++)
			{
				if(i == 0 || j == 0)
				{
					if((i == 0) && (j == 0))
					{
						T[i][j] = 0;
					}
					else if(i == 0)
					{
						T[i][j] = Integer.MAX_VALUE;
					}
					else
					{
						T[i][j] = 0;
					}
					continue;
				}
				int p = i-1;
				if(i > j)
				{
					T[i][j] = T[i-1][j];
				}
				else
				{
					if((T[i][j-i] != Integer.MAX_VALUE) && (pkts[p] != Integer.MAX_VALUE))
					{
						T[i][j] = T[i][j-i] + pkts[p];
					}
					else
					{
						T[i][j] = Integer.MAX_VALUE;	
					}

					T[i][j] = Math.min(T[i][j],T[i-1][j]);

				}
			}
		}

		if(T[W][pkts.length] == Integer.MAX_VALUE)
			return -1;

		return T[W][pkts.length];
	}

}