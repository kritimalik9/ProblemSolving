package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetMazePathCountDp {

	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		int gridsize = scan.nextInt();
		int count =0;

		long start = System.currentTimeMillis();
		count = GetMazePathCount(gridsize,0,0);
		long end = System.currentTimeMillis();
		System.out.println("Time taken to execute this normal recursive function " + (end-start)+"ms");
		System.out.println(count);

		start = System.currentTimeMillis();
		count = GetMazePathCountDpRecursive(gridsize,0,0,new HashMap<String,Integer>());
		end = System.currentTimeMillis();
		System.out.println("Time taken to execute this dp recursive function " + (end-start)+"ms");
		System.out.println(count);

		start = System.currentTimeMillis();
		count = GetMazePathCountDpIterative(gridsize,0,0);
		end = System.currentTimeMillis();
		System.out.println("Time taken to execute this dp iterative function " + (end-start)+"ms");
		System.out.println(count);

	}

	private static int GetMazePathCountDpIterative(int gridsize, int row, int col) {

		int[][] T= new int[gridsize+1][gridsize+1];
		for(int i = 0 ; i < gridsize+1 ; i++)
		{
			T[i][0] = 0;
		}
		for(int i = 0 ; i < gridsize+1 ; i++)
		{
			T[0][i] = 0;
		}
		T[row+1][col+1] = 0;
		T[row+2][col+1] = 1;
		T[row+1][col+2] = 1;

		for(int i = row+1; i < gridsize+1 ; i++)
		{
			for(int j = col+1; j < gridsize+1; j++)
			{
				if(((i == row+2) && (j == col+1))
						|| ((i == row+1) && (j == col+2)))
				{
					continue;
				}
				T[i][j] = T[i-1][j] + T[i][j-1];

			}
		}

		return T[gridsize][gridsize];
	}


	private static int GetMazePathCountDpRecursive(int gridsize, int row, int col,Map<String, Integer> map) {
		if((row == gridsize-1) &&(col == gridsize-1))
		{
			return 1;
		}
		else if((row > gridsize-1) || (col > gridsize-1))
		{
			return 0;
		}
		int count = 0;
		String mapKey = ""+row+"*"+col;
		if(map.containsKey(mapKey))
		{
			return (int)map.get(mapKey);
		}
		count += GetMazePathCountDpRecursive(gridsize,row+1,col,map);
		count += GetMazePathCountDpRecursive(gridsize,row,col+1,map);

		map.put(mapKey, count);

		return count;
	}

	private static int GetMazePathCount(int gridsize, int row, int col)
	{
		if((row == gridsize-1) &&(col == gridsize-1))
		{
			return 1;
		}
		else if((row > gridsize-1) || (col > gridsize-1))
		{
			return 0;
		}
		int count = 0;
		count += GetMazePathCount(gridsize,row+1,col);
		count += GetMazePathCount(gridsize,row,col+1);
		return count;

	}

}
