package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class RecursionCodesOfStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		ArrayList<String> out = getCodedOutputForInputString(str);
		System.out.println(out);
	}

	private static ArrayList<String> getCodedOutputForInputString(String str) {

		if(0 == str.length())
		{
			ArrayList<String> out = new ArrayList<>();
			out.add("");
			return out;
		}

		ArrayList<String> myResultList = new ArrayList<>();
		ArrayList<String> returnList = getCodedOutputForInputString(str.substring(1,str.length()));
		char codedChar = getCode(Integer.parseInt(str.substring(0,1)));
		for(String val : returnList)
		{
			myResultList.add(""+codedChar+val);
		}

		if((str.length() > 1)
				&& Integer.parseInt(str.substring(0, 2)) <= 26)
		{
			returnList = getCodedOutputForInputString(str.substring(2,str.length()));
			codedChar = getCode(Integer.parseInt(str.substring(0,2)));
			for(String val : returnList)
			{
				myResultList.add(""+codedChar+val);
			}
		}


		return myResultList;
	}

	private static char getCode(int num)
	{
		switch(num)
		{
		case 1 :
			return 'a';
		case 2 :
			return 'b';
		case 3 :
			return 'c';
		case 4 :
			return 'd';
		case 5 :
			return 'e';
		case 6:
			return 'f';
		case 7 :
			return 'g';
		case 8 :
			return 'h';
		case 9 :
			return 'i';
		case 10 :
			return 'j';
		case 11 :
			return 'k';
		case 12 :
			return 'l';
		case 13 :
			return 'm';
		case 14 :
			return 'n';
		case 15 :
			return 'o';
		case 16 :
			return 'p';
		case 17 :
			return 'q';
		case 18 :
			return 'r';
		case 19 :
			return 's';
		case 20 :
			return 't';
		case 21 :
			return 'u';
		case 22 :
			return 'v';
		case 23 :
			return 'w';
		case 24 :
			return 'x';
		case 25 :
			return 'y';
		case 26 :
			return 'z';
		default :
			return '*';
		}
	}

}
