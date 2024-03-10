package MoreRecursion;

import java.util.Scanner;

public class PrintAllAsciiSubsequencesOfStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();
		String result = "";
		PrintAllAsciiSubsequencesOfString(inputStr,result);
	}

	private static void PrintAllAsciiSubsequencesOfString(String str, String result) {
		if(str.length() == 0)
		{
			System.out.println(result);
			return;
		}
		PrintAllAsciiSubsequencesOfString(str.substring(1, str.length()),result);
		String resultAddChar =  result + str.charAt(0);
		PrintAllAsciiSubsequencesOfString(str.substring(1, str.length()),resultAddChar);	
		resultAddChar =  result + (int)str.charAt(0);
		PrintAllAsciiSubsequencesOfString(str.substring(1, str.length()),resultAddChar);		
	}

}
