package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class GetMazePathClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int gridSize = scan.nextInt();
		int row = scan.nextInt();
		int col = scan.nextInt();
		ArrayList<String> out = GetMazePath(gridSize,row,col);
		System.out.println(out);

	}

	private static ArrayList<String> GetMazePath(int gridSize, int row, int col) {

		if((row == 0) && (col == 0))
		{
			ArrayList<String> BaseList = new ArrayList<String>();
			BaseList.add("");
			return BaseList;
		}
		ArrayList<String> myResultList = new ArrayList<>();
		ArrayList<String> rr = null;

		if((row != 0) && (col != 0))
		{
			rr = GetMazePath(gridSize,row-1,col);
			for(String val : rr)
			{
				myResultList.add(val + "V");
			}
			rr.clear();
			rr = GetMazePath(gridSize,row,col-1);
			for(String val : rr)
			{
				myResultList.add(val + "H");
			}
		}
		else if(row == 0)
		{
			rr = GetMazePath(gridSize,row,col-1);
			for(String val : rr)
			{
				myResultList.add(val + "H");
			}
		}
		else
		{
			rr = GetMazePath(gridSize,row-1,col);
			for(String val : rr)
			{
				myResultList.add(val + "V");
			}

		}
		return myResultList;

	}

}
