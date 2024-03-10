import java.util.Scanner;

public class MirrorDiamondPattern {

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
			int colStartForFirstHalfRows = center;
			int colEndForFirstHalfRows  = center;

			System.out.println("Then Mirror Star Pattern becomes :");
			while(row <= numRows)
			{
				int col = 1;
				if(row <= center)
				{
					while(col <= numCols)
					{
						if((col >= colStartForFirstHalfRows)
								&& (col <= colEndForFirstHalfRows))
						{
							System.out.print("* ");
						}
						else
						{
							System.out.print("  ");
						}
						col++;
					}
					--colStartForFirstHalfRows;
					++colEndForFirstHalfRows;
				}
				else
				{
					int colStartForSecondHalfRows = row-center+1;
					int colEndForSecondHalfRows   = center+num-row;
					while(col <= numCols)
					{
						if((col >= colStartForSecondHalfRows)
								&& (col <= colEndForSecondHalfRows))
						{
							System.out.print("* ");
						}
						else
						{
							System.out.print("  ");
						}
						col++;
					}
					++colStartForSecondHalfRows;
					--colEndForSecondHalfRows;
				}
				System.out.println();
				row++;
			}
		}
	}
}

