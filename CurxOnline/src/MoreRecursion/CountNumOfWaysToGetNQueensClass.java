package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class CountNumOfWaysToGetNQueensClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int gridNsize = scan.nextInt();
		ArrayList<String> list = new ArrayList<>();  
		System.out.println(CountNumOfWaysToGetNQueenCount(gridNsize,0,list));
		CountNumOfWaysToGetNQueen(gridNsize,0,list,"");
	}

	private static int CountNumOfWaysToGetNQueen(int gridNsize, int row,ArrayList<String> list,String result) {

		int count = 0;
		int totalCount = 0;
		for(int col = 0; col < gridNsize ; col++)
		{
			count = 0;
			if(list.size() == gridNsize)
			{
				System.out.print(result+" ");
				return 1;
			}
			boolean isThisRowColCanHaveQueen = true;
			for(String val : list)
			{
				int rowPrev = Integer.parseInt(val.substring(0, 1));
				int colPrev = Integer.parseInt(val.substring(1, 2));
				int j = row - rowPrev;
				{
					if(((row > (j-1)) &&(row-j == rowPrev) && (col == colPrev))
							|| ((col > (j-1)) && (row == rowPrev) && (col-j == colPrev))
							|| ((row > (j-1)) && (col > (j-1)) && (row-j == rowPrev) && (col-j == colPrev))
							|| ((col < (gridNsize-1)) && (row > (j-1)) && (row-j == rowPrev) && (col+j == colPrev))
							)
					{
						isThisRowColCanHaveQueen = false;
						break;
					}
				}
				if(!isThisRowColCanHaveQueen)
				{
					break;
				}
			}
			if(!isThisRowColCanHaveQueen)
			{
				continue;
			}
			list.add(""+row+col);

			count = CountNumOfWaysToGetNQueen(gridNsize,row+1,list,result+'{'+(row+1)+'-'+(col+1)+"} ");
			list.remove(list.size()-1);
			if(row == 0)
			{
				totalCount += count;
			}
			else
			{
				totalCount += count;
			}

		}
		return totalCount;
	}

	private static int CountNumOfWaysToGetNQueenCount(int gridNsize, int row,ArrayList<String> list) {

		int count = 0;
		int totalCount = 0;
		for(int col = 0; col < gridNsize ; col++)
		{
			count = 0;
			if(list.size() == gridNsize)
			{
				//System.out.println(list);
				return 1;
			}
			boolean isThisRowColCanHaveQueen = true;
			for(String val : list)
			{
				int rowPrev = Integer.parseInt(val.substring(0, 1));
				int colPrev = Integer.parseInt(val.substring(1, 2));
				int j = row - rowPrev;
				{
					if(((row > (j-1)) &&(row-j == rowPrev) && (col == colPrev))
							|| ((col > (j-1)) && (row == rowPrev) && (col-j == colPrev))
							|| ((row > (j-1)) && (col > (j-1)) && (row-j == rowPrev) && (col-j == colPrev))
							|| ((col < (gridNsize-1)) && (row > (j-1)) && (row-j == rowPrev) && (col+j == colPrev))
							)
					{
						isThisRowColCanHaveQueen = false;
						break;
					}
				}
				if(!isThisRowColCanHaveQueen)
				{
					break;
				}
			}
			if(!isThisRowColCanHaveQueen)
			{
				continue;
			}
			list.add(""+row+col);

			count = CountNumOfWaysToGetNQueenCount(gridNsize,row+1,list);
			list.remove(list.size()-1);
			if(row == 0)
			{
				totalCount += count;
			}
			else
			{
				totalCount += count;
			}

		}
		return totalCount;
	}

}
