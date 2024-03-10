package Recursion;
import java.util.Scanner;

public class String1ReverseOfString2Class {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str1 = scan.nextLine();
		String str2 = scan.nextLine();
		System.out.println(String1ReverseOfString2(str1,str2,0));
	}
	private static boolean String1ReverseOfString2(String str1,String str2,int index) {
		if(str1.length() != str2.length())
		{
			return false;
		}
		else if(index == (str1.length()/2))
		{
			return true;
		}
		else
		{
			return ((str1.charAt(index) == str2.charAt(str2.length()-1-index)) && String1ReverseOfString2(str1,str2,index+1));
		}
	}

}


