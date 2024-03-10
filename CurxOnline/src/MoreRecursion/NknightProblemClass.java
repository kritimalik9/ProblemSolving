package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class NknightProblemClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int gridNsize = scan.nextInt();
		ArrayList<String> list = new ArrayList<>();  
		System.out.println(CountNumOfWaysToGetNKnightsCount(gridNsize,0,0,list,""));
		CountNumOfWaysToGetNKnights(gridNsize,0,0,list,"");
	}

	private static int CountNumOfWaysToGetNKnights(int gridNsize, int row,int col,ArrayList<String> list,String result) {

		int count = 0;
		int totalCount = 0;
		if(list.size() == gridNsize)
		{
			System.out.print(result+" ");
			return 1 ;
		}
		for(int r=row ; r < gridNsize; r++)
		{
			int c;
			if(r == row)
			{
				c=col;
			}
			else
			{
				c=0;
			}
			while(c < gridNsize)
			{
				count = 0;
				boolean isThisRowColCanHaveKnight = true;
				for(String val : list)
				{
					int rowPrev = Integer.parseInt(val.substring(0, 1));
					int colPrev = Integer.parseInt(val.substring(1, 2));
					{
						if(((r-2 >= 0) && (c-1 >= 0) && (r-2 == rowPrev) && (c-1 == colPrev))
								|| ((r-2 >= 0) && (c+1 < gridNsize) && (r-2 == rowPrev) && (c+1 == colPrev))
								|| ((r-1 >= 0) && (c-2 >= 0) && (r-1 == rowPrev) && (c-2 == colPrev))
								|| ((r-1 >= 0) && (c+2 < gridNsize) && (r-1 == rowPrev) && (c+2 == colPrev))
								|| ((r+1 < gridNsize) && (c-2 >= 0) && (r+1 == rowPrev) && (c-2 == colPrev))
								|| ((r+1 < gridNsize) && (c+2 < gridNsize) && (r+1 == rowPrev) && (c+2 == colPrev))
								|| ((r+2 < gridNsize) && (c-1 >= 0) && (r+2 == rowPrev) && (c-1 == colPrev))
								|| ((r+2 < gridNsize) && (c+1 < gridNsize) && (r+2 == rowPrev) && (c+1 == colPrev))
								)
						{
							isThisRowColCanHaveKnight = false;
							break;
						}
					}
					if(!isThisRowColCanHaveKnight)
					{
						break;
					}
				}
				if(!isThisRowColCanHaveKnight)
				{
					c++;
					continue;
				}
				list.add(""+r+c);

				count = CountNumOfWaysToGetNKnights(gridNsize,r,c+1,list,result+'{'+(r)+'-'+(c)+"} ");
				list.remove(list.size()-1);
				if(r == 0)
				{
					totalCount += count;
				}
				else
				{
					totalCount += count;
				}
				c++;
			}
		}
		return totalCount;
	}

	private static int CountNumOfWaysToGetNKnightsCount(int gridNsize, int row,int col,ArrayList<String> list,String result) {

		int count = 0;
		int totalCount = 0;
		if(list.size() == gridNsize)
		{
			//System.out.print(result+" ");
			return 1 ;
		}
		for(int r=row ; r < gridNsize; r++)
		{
			int c;
			if(r == row)
			{
				c=col;
			}
			else
			{
				c=0;
			}
			while(c < gridNsize)
			{
				count = 0;
				boolean isThisRowColCanHaveKnight = true;
				for(String val : list)
				{
					int rowPrev = Integer.parseInt(val.substring(0, 1));
					int colPrev = Integer.parseInt(val.substring(1, 2));
					{
						if(((r-2 >= 0) && (c-1 >= 0) && (r-2 == rowPrev) && (c-1 == colPrev))
								|| ((r-2 >= 0) && (c+1 < gridNsize) && (r-2 == rowPrev) && (c+1 == colPrev))
								|| ((r-1 >= 0) && (c-2 >= 0) && (r-1 == rowPrev) && (c-2 == colPrev))
								|| ((r-1 >= 0) && (c+2 < gridNsize) && (r-1 == rowPrev) && (c+2 == colPrev))
								|| ((r+1 < gridNsize) && (c-2 >= 0) && (r+1 == rowPrev) && (c-2 == colPrev))
								|| ((r+1 < gridNsize) && (c+2 < gridNsize) && (r+1 == rowPrev) && (c+2 == colPrev))
								|| ((r+2 < gridNsize) && (c-1 >= 0) && (r+2 == rowPrev) && (c-1 == colPrev))
								|| ((r+2 < gridNsize) && (c+1 < gridNsize) && (r+2 == rowPrev) && (c+1 == colPrev))
								)
						{
							isThisRowColCanHaveKnight = false;
							break;
						}
					}
					if(!isThisRowColCanHaveKnight)
					{
						break;
					}
				}
				if(!isThisRowColCanHaveKnight)
				{
					c++;
					continue;
				}
				list.add(""+r+c);

				count = CountNumOfWaysToGetNKnightsCount(gridNsize,r,c+1,list,result+'{'+(r)+'-'+(c)+"} ");
				list.remove(list.size()-1);
				if(r == 0)
				{
					totalCount += count;
				}
				else
				{
					totalCount += count;
				}
				c++;
			}
		}
		return totalCount;
	}

}
