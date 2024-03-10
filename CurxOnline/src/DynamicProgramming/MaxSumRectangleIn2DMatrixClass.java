package DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

public class MaxSumRectangleIn2DMatrixClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numTc  = scan.nextInt();
		int[][][] T = new int[numTc][][];
		for(int i = 0; i < T.length ; i++)
		{
			int numRows = scan.nextInt();
			int numCols = scan.nextInt();
			T[i] = new int[numRows][numCols];
			for(int j = 0 ; j < numRows ; j++)
			{
				for(int k = 0; k < numCols ; k++)
				{
					T[i][j][k] = scan.nextInt();
				}
			}
		}
		for(int i = 0; i < T.length ; i++)
		{
			int maxsum = MaxSumRectangleIn2DMatrix(T[i],new HashMap<String,Integer>());
			System.out.println(maxsum);
		}


	}

	private static int MaxSumRectangleIn2DMatrix(int[][] T,HashMap<String,Integer> map) {

		int startrow = 0;
		int endrow = 0;
		int startcol = 0;
		int endcol = 0;
		int maxsum = Integer.MIN_VALUE;
		for(int i = 0 ; i < T.length ; i++)
		{
			for(int j = 0 ; j < T[i].length ; j++)
			{
				endrow = i;
				endcol = j;
				startrow = 0;
				if(i == 0 && j == 0)
				{
					maxsum = Math.max(maxsum, T[i][j]);
					map.put(""+startrow+endrow+startcol+endcol, maxsum);
					continue;
				}
				for(;startrow <= endrow ; startrow++)
				{
					startcol = 0;
					for(;startcol <= endcol ; startcol++)
					{
						int sum = T[i][j];
						if(startcol != endcol)
						{
							String key1 = ""+startrow+endrow+startcol+(endcol-1);
							if(map.containsKey(key1))
							{
								sum += map.get(key1);
							}
						}
						if(startrow != endrow)
						{
							String key2 = ""+startrow+(endrow-1)+endcol+endcol;
							if(map.containsKey(key2))
							{
								sum += map.get(key2);
							}
						}
						maxsum = Math.max(maxsum,sum);
						map.put(""+startrow+endrow+startcol+endcol,sum);
					}
				}
			}
		}
		return maxsum;
	}
}
