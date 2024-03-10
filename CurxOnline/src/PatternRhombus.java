import java.util.Scanner;

public class PatternRhombus {

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
			int numRows = (2*num)-1;
			int row = 1;
			int numCols = numRows;
			int colStartForFirstHalfRows = num;
			int colValForSecondHalfRows  = num-1; 
			System.out.println("Then Pattern Rhombus becomes :");
			while(row <= numRows)
			{
				int col = 1;
				if(row <= num)
				{
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
				}
				else
				{
					int colStartForSecondHalfRows = row-num+1;
					while(col <= numCols)
					{
						if((col >= colStartForSecondHalfRows) && (col <= (numRows-row+num)))
						{
							System.out.print(colValForSecondHalfRows + " ");
							colValForSecondHalfRows = (col < num) ? ++colValForSecondHalfRows: --colValForSecondHalfRows ;
						}
						else
						{
							System.out.print("  ");
						}
						col++;
					}
					colStartForSecondHalfRows++;
				}
				System.out.println();
				row++;
			}
		}
	}
}

