import java.util.Scanner;

public class PatternNumberLadder {

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
			int patternVal = 1;
			System.out.println("Then number ladder becomes :");
			while(row <= numRows)
			{
				int col = 1;
				while(col <= row)
				{
					System.out.print(patternVal + "          ");
					patternVal++;
					col++;
				}
				System.out.println();
				row++;
			}
		}
	}

}
