package Recursion;

import java.util.Scanner;

public class FindSumOfDigitsInStringClass {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String number = scan.nextLine();
		System.out.println(FindSumOfDigitsInString(number,0));
	}

	private static int FindSumOfDigitsInString(String number,int idx) {
		if(idx == number.length())
		{
			return 0;
		}
		else
		{
			int a = Integer.parseInt(number.substring(idx,idx+1)) ;
			return (a + FindSumOfDigitsInString(number,idx+1));
		}
	}
}
