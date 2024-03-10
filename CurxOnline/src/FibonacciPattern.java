import java.util.Scanner;

public class FibonacciPattern {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int num = scn.nextInt();
		if((num <= 0) || (num >= 100))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int numRows = num;
			int row = 1;
			int numFirst  = 0;
			int numSecond = 1;
			int sum = 0;
			System.out.println("Then Fibonacci Pattern becomes :");
			while(row <= numRows)
			{
				int col = 1;
				while(col <= row)
				{

					System.out.print(numFirst+" ");
					sum = numFirst + numSecond;
					numFirst = numSecond;
					numSecond = sum;
					col++;
				}
				System.out.println();
				row++;
			}
		}
	}
}

