import java.util.Scanner;

public class PatternMountain {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int numRows = scn.nextInt();
		if((numRows <= 0) || (numRows >= 10))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int row = 1;
			int numCols = (numRows*2) - 1;
			System.out.println("Then Pattern Mountain becomes :");
			while(row <= numRows)
			{
				int col = 1;
				int reverseNum = (row == numRows)?row-1:row;
				while(col <= numCols)
				{
					if(col <= numRows)
					{
						if(col <= row)
						{
							System.out.print(col + "  ");
						}
						else
						{
							System.out.print("  " + "  ");
						}
						if((col == numRows) && (row == numRows ))
						{
							System.out.print(" ");
						}
					}
					else
					{
						if(col >= (numCols-reverseNum+1))
						{
							System.out.print(reverseNum + "  ");
							reverseNum--;
						}
						else
						{
							System.out.print("  ");
						}
					}
					col++;
				}
				System.out.println();
				row++;
			}
		}
	}
}
