package MoreRecursion;

import java.util.Scanner;

public class RecursionWhatIsInParanthesisClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = "";
		RecursionWhatIsInParanthesis(str,result,false);		

	}

	private static void RecursionWhatIsInParanthesis(String str, String result, boolean isOpen) {

		if(str.length() == 0)
		{
			System.out.println("");
			return;
		}
		if(str.charAt(0) == '(')
		{
			isOpen = true;
		}
		else if((isOpen == true) && (str.charAt(0) == ')'))
		{
			System.out.println(result);
			return;
		}
		if((isOpen == true) && (str.charAt(0) != '('))
		{
			String temp = result + str.charAt(0);
			result = temp;
		}
		RecursionWhatIsInParanthesis(str.substring(1,str.length()),result,isOpen);
	}

}
