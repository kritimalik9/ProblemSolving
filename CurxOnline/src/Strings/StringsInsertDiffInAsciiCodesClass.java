package Strings;
import java.util.Scanner;
public class StringsInsertDiffInAsciiCodesClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		StringBuilder StringInput = new StringBuilder(str);
		System.out.println(StringsInsertDiffInAsciiCodes(StringInput));
	}

	private static StringBuilder StringsInsertDiffInAsciiCodes(StringBuilder StringInput) {

		StringBuilder strInsertDiffInAsciiCodes = new StringBuilder(StringInput);
		int offset = 0;
		for(int i= 1; i < StringInput.length() ; i++)
		{
			char chfirst = StringInput.charAt(i);
			char chSecond = StringInput.charAt(i-1);
			int diff = (int)chfirst - (int)chSecond;
			//diff = Math.abs(diff);
			String diffInString = "" + diff;
			strInsertDiffInAsciiCodes.insert(i+offset, diffInString);
			offset = diffInString.length();
		}
		return strInsertDiffInAsciiCodes;
	}

}
