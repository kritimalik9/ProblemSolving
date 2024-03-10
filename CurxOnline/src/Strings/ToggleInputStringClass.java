package Strings;

import java.util.Scanner;

public class ToggleInputStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		StringBuilder strToBeToggled = new StringBuilder(str);
		System.out.println(ToggleInputString(strToBeToggled));
	}

	private static StringBuilder ToggleInputString(StringBuilder strToBeToggled) {

		for(int i= 0; i < strToBeToggled.length() ; i++)
		{
			char ch = strToBeToggled.charAt(i);
			if(ch >= 'A' && ch <= 'Z')
			{
				ch = (char) ((int)ch + 32);
			}
			else if(ch >= 'a' && ch <= 'z')
			{
				ch = (char) ((int)ch - 32);
			}
			else
			{
				System.out.println("Invalid character in string"+ch);
				return null;
			}
			strToBeToggled.setCharAt(i,ch);
		}
		return strToBeToggled;
	}

}
