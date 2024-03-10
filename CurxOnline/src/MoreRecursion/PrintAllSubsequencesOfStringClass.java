package MoreRecursion;

import java.util.Scanner;

public class PrintAllSubsequencesOfStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();
		String result = "";
		PrintAllSubsequencesOfString(inputStr,result);
	}

	private static void PrintAllSubsequencesOfString(String str, String result) {
		if(str.length() == 0)
		{
			System.out.println(result);
			return;
		}
		PrintAllSubsequencesOfString(str.substring(1, str.length()),result);
		String resultAddChar =  result + str.charAt(0);
		PrintAllSubsequencesOfString(str.substring(1, str.length()),resultAddChar);		
	}

}
