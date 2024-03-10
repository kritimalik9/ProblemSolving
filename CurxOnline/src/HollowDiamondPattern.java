import java.util.Scanner;

public class HollowDiamondPattern {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int num = scn.nextInt();
		if((num <= 0) || (num >= 10) || ((num%2) == 0))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int numRows = num;
			int center  = (num >> 1)+1;
			int row = 1;
			int numCols = numRows;
			int colStartForFirstHalfRows = center-1;
			int colEndForFirstHalfRows  = center+1;

			System.out.println("Then Hollow Diamond Pattern becomes :");
			while(row <= numRows)
			{
				int col = 1;
				if(row <= center)
				{
					while(col <= numCols)
					{
						if((row == 1) 
								|| (col <= colStartForFirstHalfRows)
								|| (col >= colEndForFirstHalfRows))
						{
							System.out.print("* ");
						}
						else
						{
							System.out.print("  ");
						}
						col++;
					}
					colStartForFirstHalfRows = (row == 1) ? colStartForFirstHalfRows :  --colStartForFirstHalfRows;
					colEndForFirstHalfRows   = (row == 1) ? colEndForFirstHalfRows   :  ++colEndForFirstHalfRows;
				}
				else
				{
					int colStartForSecondHalfRows = row-center+1;
					int colEndForSecondHalfRows   = center+num-row;
					while(col <= numCols)
					{
						if((row == num)
								|| (col <= colStartForSecondHalfRows)
								|| (col >= colEndForSecondHalfRows))
						{
							System.out.print("* ");
						}
						else
						{
							System.out.print("  ");
						}
						col++;
					}
					colStartForSecondHalfRows= (row == num) ? colStartForSecondHalfRows : ++colStartForSecondHalfRows;
					colEndForSecondHalfRows  = (row == num) ? colEndForSecondHalfRows   : --colEndForSecondHalfRows;
				}
				System.out.println();
				row++;
			}
		}
	}
}

