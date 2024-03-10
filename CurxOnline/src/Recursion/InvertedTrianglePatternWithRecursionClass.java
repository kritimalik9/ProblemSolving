package Recursion;

import java.util.Scanner;

public class InvertedTrianglePatternWithRecursionClass {

	public static void main(String[] args) {
		System.out.println("Enter the length of inverted triangular pattern to be printed : ");
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		InvertedTrianglePatternWithRecursion(N,1,1);
	}

	private static void InvertedTrianglePatternWithRecursion(int n, int row, int col) {
		if(row == (n+1))
		{
			return;
		}
		System.out.print("*\t");
		if((n-row+1) == col)
		{
			System.out.println();
			row++;
			col=1;
		}
		else
		{
			col++;
		}
		InvertedTrianglePatternWithRecursion(n,row,col);
	}

}
