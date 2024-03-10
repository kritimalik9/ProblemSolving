package Recursion;

import java.util.Scanner;

public class PatternSolverWithRecursionClass {

	public static void main(String[] args) {
		System.out.println("Enter the length of triangular pattern to be printed : ");
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		PatternSolverWithRecursion(N,1,1);
	}

	private static void PatternSolverWithRecursion(int n, int row, int col) {
		if(row == (n+1))
		{
			return;
		}
		System.out.print("*");
		if(row == col)
		{
			System.out.println();
			row++;
			col=1;
		}
		else
		{
			col++;
		}
		PatternSolverWithRecursion(n,row,col);
	}

}
