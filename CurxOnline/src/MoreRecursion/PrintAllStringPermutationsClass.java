package MoreRecursion;

import java.util.Scanner;

public class PrintAllStringPermutationsClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = "";
		PrintAllStringPermutations(str,result);
	}

	private static void PrintAllStringPermutations(String str, String result) {
		if(str.length() == 0)
		{
			System.out.println(result);
		}

		for(int i = 0 ;i< str.length();i++)
		{
			String nextStr = str.substring(0, i)+str.substring(i+1,str.length());
			String AppendedRes = result+str.charAt(i);
			PrintAllStringPermutations(nextStr,AppendedRes);
		}

	}

}
