package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class GetAllAsciiSubsequencesOfStringClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();
		ArrayList<Character> list = new ArrayList<>();
		ArrayList<String> out = null;
		out = GetAllAsciiSubsequencesOfStringMentor(inputStr);
		System.out.println(out.size());
		for(int i = 0;i<out.size();i++)
		{
			System.out.print(out.get(i)+" ");
		}
		System.out.println();
	}
	private static ArrayList<String> GetAllAsciiSubsequencesOfStringMentor(String str) {
		ArrayList<String> outList = null;

		if(str.length() == 1)
		{
			outList = new ArrayList<>();
			outList.add("");
			outList.add(str);
			outList.add(""+(int)(str.charAt(0)));
			return outList;
		}

		outList = GetAllAsciiSubsequencesOfStringMentor(str.substring(1, str.length()));

		int originalListSize = outList.size();
		for(int i=0;i<originalListSize;i++)
		{
			String temp = outList.get(i);
			String out = "" + str.charAt(0) + temp;
			outList.add(out);			
		}
		for(int i=0;i<originalListSize;i++)
		{
			String temp = outList.get(i);
			String out = "" + (int)(str.charAt(0)) + temp;
			outList.add(out);			
		}

		// TODO Auto-generated method stub
		return outList;
	}
}
