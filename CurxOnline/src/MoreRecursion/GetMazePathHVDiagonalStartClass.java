package MoreRecursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GetMazePathHVDiagonalStartClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int row = scan.nextInt();
		int col = scan.nextInt();
		ArrayList<String> out = GetMazePathHVDiagonalStart(0,0,row-1,col-1);
		Collections.sort(out,Collections.reverseOrder());
		System.out.println(out.size());
		for(String val : out)
		{
			System.out.print(val+" ");
		}
	}

	private static ArrayList<String> GetMazePathHVDiagonalStart(int startRow, int startCol,int endRow,int endCol) {

		if((startRow == endRow) && (startCol == endCol))
		{
			ArrayList<String> BaseList = new ArrayList<String>();
			BaseList.add("");
			return BaseList;
		}
		else if((startRow > endRow) || (startCol > endCol))
		{
			ArrayList<String> BaseList = new ArrayList<String>();
			return BaseList;
		}
		ArrayList<String> myResultList = new ArrayList<>();
		ArrayList<String> rr = null;

		rr = GetMazePathHVDiagonalStart(startRow,startCol+1,endRow,endCol);
		for(String val : rr)
		{
			myResultList.add(val + "H");
		}
		rr.clear();
		rr = GetMazePathHVDiagonalStart(startRow+1,startCol,endRow,endCol);
		for(String val : rr)
		{
			myResultList.add(val + "V");
		}
		rr.clear();
		if(startRow == startCol)
		{
			rr = GetMazePathHVDiagonalStart(startRow+1,startCol+1,endRow,endCol);
			for(String val : rr)
			{
				myResultList.add(val + "D");
			}
		}

		return myResultList;

	}

}
