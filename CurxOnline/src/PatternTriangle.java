import java.util.Scanner;

public class PatternTriangle {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int num = scn.nextInt();
		if((num <= 0) || (num >= 10))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int numRows = num;
			int row = 1;
			int numCols = (2*num)-1;
			int colStartForFirstHalfRows = num;
			System.out.println("Then Pattern Triangle becomes :");
			while(row <= numRows)
			{
				int col = 1;
				int colValForFirstHalfRows = row;
				while(col <= numCols)
				{
					if((col >= colStartForFirstHalfRows) && (col <= (colStartForFirstHalfRows+(2*(row-1)))))
					{
						System.out.print(colValForFirstHalfRows + " ");
						colValForFirstHalfRows = (col < num) ? ++colValForFirstHalfRows: --colValForFirstHalfRows ;
					}
					else
					{
						System.out.print("  ");
					}
					col++;
				}
				colStartForFirstHalfRows--;
				System.out.println();
				row++;
			}
		}
	}
}

