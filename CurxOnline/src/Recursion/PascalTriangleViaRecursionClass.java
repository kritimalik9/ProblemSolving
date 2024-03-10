package Recursion;

import java.util.Scanner;

public class PascalTriangleViaRecursionClass {

	public static void main(String[] args) {
		System.out.println("Enter the length of pascal pattern to be printed : ");
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int[][] PingPongArray = new int[2][N];
		PascalTriangleViaRecursion(PingPongArray,N,0,0,0);
	}

	private static void PascalTriangleViaRecursion(int[][] PingPongArray,int n, int row, int col,int PingPongIdx) {
		if(row == n)
		{
			return;
		}
		if((col == 0) || (col == row))
		{
			PingPongArray[PingPongIdx][col] = 1;
			System.out.print(PingPongArray[PingPongIdx][col]+"\t");
		}
		else
		{
			PingPongArray[PingPongIdx][col] = PingPongArray[PingPongIdx^1][col] + PingPongArray[PingPongIdx^1][col-1];
			System.out.print(PingPongArray[PingPongIdx][col]+"\t");
		}
		if(row == col)
		{
			System.out.println();
			row++;
			col=0;
			PingPongIdx = PingPongIdx^1;
		}
		else
		{
			col++;
		}
		PascalTriangleViaRecursion(PingPongArray,n,row,col,PingPongIdx);
	}

}
