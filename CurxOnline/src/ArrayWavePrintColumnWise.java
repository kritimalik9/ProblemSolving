import java.util.Scanner;

public class ArrayWavePrintColumnWise {
	public static void main(String[] args) {
		int[][] arr = takeInput2DArray();
		//System.out.println("*********Input 2D array :************");
		//display2DArray(arr);
		//System.out.println("*********Wave print of a given 2D array :*****************");
		display2DArrayInWaveform(arr);
	}

	private static void display2DArrayInWaveform(int[][] arr) {
		for(int col = 0 ; col < arr[0].length ; col++)
		{
			for(int row = 0 ; row < arr.length ; row++)
			{
				if(col % 2 == 0)
				{
					System.out.print(arr[row][col]+",  ");
				}
				else
				{
					System.out.print(arr[arr.length - row - 1][col]+",  ");
				}
			}
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
			System.out.println("Wrong input of number of rows/columns");
			return null;
		}
	}

}
