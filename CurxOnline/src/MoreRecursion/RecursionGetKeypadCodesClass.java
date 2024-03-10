package MoreRecursion;

import java.util.Scanner;

public class RecursionGetKeypadCodesClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String keys = scan.nextLine();

		System.out.println(RecursionGetKeypadCodesCount(keys,""));

		RecursionGetKeypadCodes(keys,"");


	}

	private static int RecursionGetKeypadCodes(String keys,String result) {
		// TODO Auto-generated method stub
		int count = 0;
		if(keys.length() == 0)
		{
			count++;
			System.out.print(result+" ");
			return count;
		}
		String code = getKeyCode(keys.substring(0,1));

		for(int i = 0 ; i < code.length() ; i++)
		{
			count += RecursionGetKeypadCodes(keys.substring(1,keys.length()),result+code.charAt(i));
		}

		return count;
	}

	private static int RecursionGetKeypadCodesCount(String keys,String result) {
		// TODO Auto-generated method stub
		int count = 0;
		if(keys.length() == 0)
		{
			count++;
			//System.out.println(result);
			return count;
		}
		String code = getKeyCode(keys.substring(0,1));

		for(int i = 0 ; i < code.length() ; i++)
		{
			count += RecursionGetKeypadCodesCount(keys.substring(1,keys.length()),result+code.charAt(i));
		}

		return count;
	}


	private static String getKeyCode(String str) {

		switch(str)
		{
		case "1":
			return "abc";
		case "2":
			return "def";
		case "3":
			return "ghi";
		case "4":
			return "jkl";
		case "5":
			return "mno";
		case "6":
			return "pqrs";
		case "7":
			return "tuv";
		case "8":
			return "wx";
		case "9":
			return "yz";
		default:
			return "";

		}

	}

}
