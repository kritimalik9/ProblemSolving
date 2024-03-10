import java.util.Scanner;

public class PascalTrianglePattern3 {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int numRows = scn.nextInt();
		int[][] PrevRowAllCols = new int[2][numRows];
		if((numRows <= 0) || (numRows >= 100))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int row = 0;
			int RowUpLeftCol = 0;
			int RowUp = 0;
			int rowPingPongBuf = 0;
			System.out.println("Then Pascal Triangle(Pattern 3) becomes :");
			while(row < numRows)
			{
				int col = 0;
				while(col <= row)
				{
					if(row == 0)
					{
						PrevRowAllCols[rowPingPongBuf][col] = 1;
						System.out.print(PrevRowAllCols[rowPingPongBuf][col] + "        ");
					}
					else
					{
						if(col == 0)
						{
							RowUpLeftCol = 0;
							RowUp = PrevRowAllCols[rowPingPongBuf^1][col];
						}
						else if(col == row)
						{

							RowUpLeftCol = PrevRowAllCols[rowPingPongBuf^1][col-1];
							RowUp = 0;
						}
						else 
						{
							RowUpLeftCol = PrevRowAllCols[rowPingPongBuf^1][col-1];
							RowUp   	 = PrevRowAllCols[rowPingPongBuf^1][col];
						}
						PrevRowAllCols[rowPingPongBuf][col]= RowUpLeftCol + RowUp;
						System.out.print(PrevRowAllCols[rowPingPongBuf][col] + "        ");
					}
					col++;
				}
				System.out.println();
				row++;
				rowPingPongBuf = rowPingPongBuf ^ 1;
			}
		}
	}
}
