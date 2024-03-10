package MoreRecursion;
import java.util.ArrayList;
import java.util.Scanner;

public class PrintMazePathClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int gridSize = scan.nextInt();
		int startRow = scan.nextInt();
		int startCol = scan.nextInt();
		int endRow = scan.nextInt();
		int endCol = scan.nextInt();
		String result = "";
		System.out.println(PrintMazePath(gridSize,startRow,startCol,endRow,endCol,result));

	}

	private static int PrintMazePath(int gridSize, int startRow, int startCol,int endRow,int endCol,String result) {

		int count = 0;
		if((startRow == endRow) && (startCol == endCol))
		{
			System.out.println(result);
			return 1;

		}
		else if((startRow > endRow) || (startCol > endCol))
		{
			return 0;
		}

		count = PrintMazePath(gridSize,startRow,startCol+1,endRow,endCol,result+"H");

		count += PrintMazePath(gridSize,startRow+1,startCol,endRow,endCol,result+"V");
		return count;
	}

}
