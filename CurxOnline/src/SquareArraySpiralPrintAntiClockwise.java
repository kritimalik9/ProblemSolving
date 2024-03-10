import java.util.Scanner;

public class SquareArraySpiralPrintAntiClockwise{

	public static void main(String[] args) {
		int[][] arr = takeInput2DArray();
		if (arr != null)
		{
			//System.out.println("*********Input 2D array :************");
			//display2DArray(arr);
			//System.out.println("*********Spiral print of a given 2D array :*****************");
			display2DSquareArraySpiralPrint(arr);
		}
		else
		{
			//System.out.println("Error! Given 2D array is a matrix with number of rows/cols out of defined range .");
		}
	}

	private static void display2DSquareArraySpiralPrint(int[][] arr) {
		int numSpiralIterations = (arr[0].length % 2 == 0) ? arr[0].length/2 : (arr[0].length+1)/2;
		int row = 0;
		int col = 0;
		int nextRowLen = arr.length-1;
		int nextColLen = arr[row].length-1;
		while(col < numSpiralIterations)
		{
			int tempRow = col;
			if((nextRowLen + 1) == col)
			{
				break;
			}
			while(tempRow <= nextRowLen)
			{
				System.out.print(arr[tempRow][col] + ",  ");
				tempRow++;
			}
			int tempCol = col;
			while(tempCol+ 1 <= nextColLen)
			{
				System.out.print(arr[nextRowLen][tempCol+1] + ",  ");
				tempCol++;
			}
			if(((col+1) == numSpiralIterations) && (arr.length % 2 == 0) && (arr[0].length % 2 != 0))
			{
				break;
			}
			tempRow = nextRowLen;
			while(tempRow - 1 >= col)
			{
				System.out.print(arr[tempRow-1][nextColLen] + ",  ");
				tempRow--;
			}
			tempCol = nextColLen;
			while(tempCol - 1 >= col+1)
			{
				System.out.print(arr[col][tempCol-1] + ",  ");
				tempCol--;
			}
			nextRowLen--;
			nextColLen--;
			col++;
		}
		System.out.println("END");
	}

	private static void display2DArray(int[][] arr) {
		// TODO Auto-generated method stub
		for(int row = 0 ; row < arr.length ; row++)
		{
			for(int col = 0 ; col < arr[row].length ; col++)
			{
				System.out.print(arr[row][col] + "\t");
			}
			System.out.println();
		}
	}

	private static int[][] takeInput2DArray() {
		Scanner scan = new Scanner(System.in);
		//System.out.println("Enter number of rows");
		int numRows = scan.nextInt();
		int[][] arr = new int[numRows][];
		int numCols = scan.nextInt();
		if((numRows >= 1) && (numRows <= 10) && (numCols >= 1) && (numCols <= 10))
		{
			for(int row = 0 ; row < arr.length ; row++)
			{
				arr[row] = new int[numCols];
				for(int col = 0 ; col < arr[row].length ; col++)
				{
					//System.out.println("Enter value of arr["+row+"]["+col+"] = ");
					arr[row][col] = scan.nextInt();
				}
			}
			return arr;
		}
		else
		{
			//System.out.println("Wrong input of number of rows/columns");
			return null;
		}
	}

}
