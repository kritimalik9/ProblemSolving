package Recursion;

import java.util.Scanner;

public class NthTriangleRecursionClass {

	public static void main(String[] args) {
		System.out.println("Enter input N whose Nth triangle needs to be computed : ");
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		if((N >= 1) && (N <= 100))
		{
			System.out.println(NthTriangleRecursion(N));
		}
	}

	private static int NthTriangleRecursion(int n) {
		if(n == 1)
		{
			return 1;
		}
		else
		{
			return (n + NthTriangleRecursion(n-1));
		}
	}
}
