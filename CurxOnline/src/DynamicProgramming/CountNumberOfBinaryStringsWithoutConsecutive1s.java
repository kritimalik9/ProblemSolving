package DynamicProgramming;

import java.util.Scanner;

public class CountNumberOfBinaryStringsWithoutConsecutive1s {

	public static void main(String args[])
	{
		Scanner scan  = new Scanner(System.in);
		int numTCs = scan.nextInt();
		int[] tc  = new int[numTCs];
		for(int i = 0 ; i < numTCs ; i++)
		{
			tc[i] = scan.nextInt();
		}

		for(int i = 0 ; i < numTCs ; i++)
		{
			int count = CountNumberOfBinaryStringsWithoutConsecutive1sIterativeDp(tc[i]);
			System.out.println(count);
		}
	}

	private static int CountNumberOfBinaryStringsWithoutConsecutive1sIterativeDp(int num) {
		int T[] = new int[num+1];
		T[0] = 1;
		T[1] = 2;
		for(int i = 2;i <= num;i++)
		{
			T[i] = T[i-1] + T[i-2];
		}
		return T[num];
	}
}
