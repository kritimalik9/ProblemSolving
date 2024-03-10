import java.util.Scanner;

public class PatternWithZeros {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int numRows = scn.nextInt();
		if((numRows <= 0) || (numRows >= 100))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int row = 1;
			System.out.println("Then Pattern With Zeros becomes :");
			while(row <= numRows)
			{
				int col = 1;
				while(col <= row)
				{
					if((col == 1) || (col == row))
					{
						System.out.print(row + "     ");
					}
					else
					{
						System.out.print("0     ");
					}
					col++;
				}
				System.out.println();
				row++;
			}
		}
	}

}
