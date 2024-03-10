package MoreRecursion;

import java.util.Scanner;

public class RecursionDuplicateCharacterFormattingClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = "";
		RecursionDuplicateCharacterFormatting(str,0,result);

	}

	private static void RecursionDuplicateCharacterFormatting(String str, int i, String result) {

		if(i == str.length()-1)
		{
			System.out.println(result+str.charAt(i));
			return;
		}
		if(str.charAt(i) == str.charAt(i+1))
		{
			RecursionDuplicateCharacterFormatting(str,i+1,result+str.charAt(i)+'*');
		}
		else
		{
			RecursionDuplicateCharacterFormatting(str,i+1,result+str.charAt(i));
		}

	}

}
