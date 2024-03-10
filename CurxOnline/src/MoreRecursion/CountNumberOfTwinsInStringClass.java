package MoreRecursion;
import java.util.Scanner;

public class CountNumberOfTwinsInStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		CountNumberOfTwinsInString(str,0,0);

	}

	private static void CountNumberOfTwinsInString(String str, int i, int count) {

		if(i == str.length())
		{
			System.out.println(count);
			return;
		}
		if(((i+2) < str.length()) && (str.charAt(i) == str.charAt(i+2)) )
		{
			count++;
		}
		CountNumberOfTwinsInString(str,i+1,count);
	}

}
