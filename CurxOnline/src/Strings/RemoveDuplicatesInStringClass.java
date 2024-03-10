package Strings;

import java.util.ArrayList;
import java.util.Scanner;

public class RemoveDuplicatesInStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String inputString = scan.nextLine();
		System.out.println(RemoveDuplicatesInString(inputString));
	}

	private static String RemoveDuplicatesInString(String inputString) {

		StringBuilder StringOutputWithNoDuplicates = new StringBuilder(inputString);
		int start = 1;
		while(start < StringOutputWithNoDuplicates.length())
		{
			char chFirst = StringOutputWithNoDuplicates.charAt(start);
			char chSecond = StringOutputWithNoDuplicates.charAt(start-1);
			if(chFirst == chSecond)
			{
				StringOutputWithNoDuplicates.deleteCharAt(start);
				continue;
			}
			start++;
		}
		return StringOutputWithNoDuplicates.toString();

	}

}
