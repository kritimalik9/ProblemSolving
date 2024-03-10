package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class GetAllSubsequencesOfStringClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();
		ArrayList<Character> list = new ArrayList<>();
		ArrayList<String> out = null;
		//		out = GetAllSubsequencesOfStringMine(inputStr,0,list,out);
		//		System.out.println(out);
		//		System.out.println(out.size());
		out = GetAllSubsequencesOfStringMentor(inputStr);
		System.out.println(out.size());
		for(int i=0;i<out.size();i++)
		{
			System.out.print(out.get(i));
			if(i%2 != 0)
			{
				System.out.print(" ");
			}
		}
	}
	private static ArrayList<String> GetAllSubsequencesOfStringMentor(String str) {
		ArrayList<String> outList = null;

		if(str.length() == 1)
		{
			outList = new ArrayList<>();
			outList.add(" ");
			outList.add(str);
			return outList;
		}

		outList = GetAllSubsequencesOfStringMentor(str.substring(1, str.length()));

		int originalListSize = outList.size();
		for(int i=0;i<originalListSize;i++)
		{
			String temp = outList.get(i);
			String out = "" + str.charAt(0) + temp;
			outList.add(out);			
		}

		// TODO Auto-generated method stub
		return outList;
	}
	private static ArrayList<String> GetAllSubsequencesOfStringMine(String str,int index,ArrayList<Character> list,ArrayList<String> out)
	{
		if(list.size() == str.length())
		{
			return new ArrayList<String>();
		}

		for(int i = index; i < str.length(); i++)
		{
			list.add(str.charAt(i));
			out = GetAllSubsequencesOfStringMine(str,i+1,list,out);
			if(!out.contains(list.toString()))
			{
				out.add(list.toString());
			}
			if((list.size()  == 1) && (list.get(list.size()-1) == str.charAt(str.length()-1)))
			{
				out.add("' '");
			}
			list.remove(list.size()-1);
		}

		return out;
	}

}
