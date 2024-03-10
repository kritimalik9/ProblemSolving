package MoreRecursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GetMazePathVaryingRowColSizeClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int row = scan.nextInt();
		int col = scan.nextInt();
		ArrayList<String> out = GetMazePath(row-1,col-1);
		System.out.println(out.size());
		Collections.sort(out,Collections.reverseOrder());
		for(String val : out)
		{
			System.out.print(val+" ");
		}

	}

	private static ArrayList<String> GetMazePath(int row, int col) {

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
			rr = GetMazePath(row-1,col);
			for(String val : rr)
			{
				myResultList.add(val + "V");
			}
			rr.clear();
			rr = GetMazePath(row,col-1);
			for(String val : rr)
			{
				myResultList.add(val + "H");
			}
		}
		else if(row == 0)
		{
			rr = GetMazePath(row,col-1);
			for(String val : rr)
			{
				myResultList.add(val + "H");
			}
		}
		else
		{
			rr = GetMazePath(row-1,col);
			for(String val : rr)
			{
				myResultList.add(val + "V");
			}

		}
		return myResultList;

	}

}
