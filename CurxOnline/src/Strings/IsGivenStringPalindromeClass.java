package Strings;
import java.util.Scanner;
public class IsGivenStringPalindromeClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		System.out.println(IsGivenStringPalindrome(str));
	}
	private static boolean IsGivenStringPalindrome(String str) {
		boolean IsPalindrome = true;

		for(int i = 0; i<(str.length()/2); i++)
		{
			if(str.charAt(i) != str.charAt(str.length()-1-i))
			{
				IsPalindrome = false;
				break;
			}
		}
		return IsPalindrome;
	}

}


