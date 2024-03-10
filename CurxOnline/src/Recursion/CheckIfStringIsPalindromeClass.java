package Recursion;
import java.util.Scanner;

public class CheckIfStringIsPalindromeClass {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		System.out.println(CheckIfStringIsPalindrome(str,0));
	}
	private static boolean CheckIfStringIsPalindrome(String str,int index) {
		if(index == (str.length()/2))
		{
			return true;
		}
		else
		{
			return ((str.charAt(index) == str.charAt(str.length()-1-index)) && CheckIfStringIsPalindrome(str,index+1));
		}
	}

}


