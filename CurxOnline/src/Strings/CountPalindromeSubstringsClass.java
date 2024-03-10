package Strings;
import java.util.ArrayList;
import java.util.Scanner;

public class CountPalindromeSubstringsClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		if((str.length() >= 1) && (str.length() <= 1000))
		{
			System.out.println(IsGivenStringPalindrome(str));
		}
	}
	private static int IsGivenStringPalindrome(String str) {
		boolean IsPalindrome = true;
		int count = 0;
		ArrayList<String> palindromeList = new ArrayList<>();
		for(int i = 0; i<str.length(); i++)
		{
			for(int j = i; j <str.length(); j++)
			{
				String substr = str.substring(i, j+1);
				IsPalindrome = true;
				for(int k = 0; k<(substr.length()/2); k++)
				{
					if(substr.charAt(k) != substr.charAt(substr.length()-1-k))
					{
						IsPalindrome = false;
						break;
					}
				}
				if(IsPalindrome)// && (!palindromeList.contains(substr)))
				{
					count++;
					palindromeList.add(substr);
				}
			}
		}
		System.out.println(palindromeList);
		return count;
	}

}
