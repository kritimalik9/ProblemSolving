package DynamicProgramming;

import java.util.Scanner;

public class GetNumWaysToCross1dLadderBoardClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int NumCells = scan.nextInt();
		int cell[] = new int[NumCells];
		int ladderLen = scan.nextInt();
		for(int i = 0 ; i < cell.length ; i++)
		{
			cell[i] = scan.nextInt();
		}
		int NumWaysToCross1dLadderBoard = GetNumWaysToCross1dLadderBoardDpIterative(cell,ladderLen);
		System.out.println(NumWaysToCross1dLadderBoard);

	}

	private static int GetNumWaysToCross1dLadderBoardDpIterative(int[] cell, int ladderLen) {

		int T[] = new int[cell.length];
		for(int i = 0 ; i < cell.length ; i++)
		{
			T[i] = 0;
		}

		for(int i = 0 ; i < cell.length ; i++)
		{
			if(i == 0)
			{
				T[i] = 1; // First cell is guaranteed to be ladder instead of snake as per question
				continue;
			}
			if(cell[i] == 1) // this cell has a snake
			{
				T[i] = 0;
			}
			else if(cell[i] == 0) // this cell has a ladder
			{
				if(i-ladderLen >= 0)
				{
					T[i] = T[i-ladderLen];
				}
				T[i] += T[i-1];
			}
		}

		return T[cell.length-1];
	}

}
