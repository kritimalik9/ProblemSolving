package Strings;
import java.util.Scanner;

public class StringsOddHighEvenLowCharClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		StringBuilder strOddHighEvenLowChar = new StringBuilder(str);
		StringsOddHighEvenLowChar(strOddHighEvenLowChar);
		System.out.println(strOddHighEvenLowChar);
	}

	private static void StringsOddHighEvenLowChar(StringBuilder strOddHighEvenLowChar) {

		for(int i= 0; i < strOddHighEvenLowChar.length() ; i++)
		{
			char ch = strOddHighEvenLowChar.charAt(i);
			if(i % 2 == 0)
			{
				ch = (char) ((int)ch + 1);
			}
			else
			{
				ch = (char) ((int)ch - 1);
			}
			strOddHighEvenLowChar.setCharAt(i,ch);
		}
	}

}
