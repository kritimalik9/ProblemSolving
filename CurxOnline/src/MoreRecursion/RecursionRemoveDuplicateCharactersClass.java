package MoreRecursion;
import java.util.Scanner;

public class RecursionRemoveDuplicateCharactersClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = str.substring(0,1);
		RecursionDuplicateCharacterFormatting(str,1,result);

	}

	private static void RecursionDuplicateCharacterFormatting(String str, int i, String result) {

		if(i == str.length())
		{
			System.out.println(result);
			return;
		}
		if(str.charAt(i) == str.charAt(i-1))
		{
			RecursionDuplicateCharacterFormatting(str,i+1,result);
		}
		else
		{
			RecursionDuplicateCharacterFormatting(str,i+1,result+str.charAt(i));
		}

	}

}
