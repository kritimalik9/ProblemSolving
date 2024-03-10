package MoreRecursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GetAllPermutationsOfStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();
		//		ArrayList<Character> list = new ArrayList<>();
		//		ArrayList<String> listOutput = new ArrayList<>();
		//		PrintAllPermutationsOfStringMine(listOutput,inputStr,0,list);
		//		for(String val : listOutput)
		//		{
		//			System.out.println(val);
		//		}
		//		System.out.println(listOutput.size());
		ArrayList<String> out = PrintAllPermutationsOfStringMentor(inputStr);
		System.out.println(out.size());
		Collections.sort(out);
		for(int i=0;i<out.size();i++)
		{
			System.out.print(out.get(i)+" ");
		}
	}

	private static ArrayList<String> PrintAllPermutationsOfStringMentor(String str) {

		if(str.length() == 1) // or, in base case, add empty string if string length is 0
		{
			ArrayList<String> out = new ArrayList<String>();
			out.add(str);
			return out;
		}

		ArrayList<String> out = PrintAllPermutationsOfStringMentor(str.substring(1,str.length()));
		int originalListSize = out.size();

		for(int listIdx = 0; listIdx < originalListSize; listIdx++)
		{
			String temp = out.get(listIdx);
			for(int pos = 0; pos <= temp.length(); pos++)
			{
				//				StringBuffer temp1 = new StringBuffer(temp);
				//				temp1.insert(pos,str.charAt(0));
				//Insert a character in String inplace of StringBuffer
				String temp1 = temp.substring(0, pos)+str.charAt(0)+temp.substring(pos,temp.length());
				//Remove a character in String inplace of StringBuffer
				//				String temp2 = temp.substring(0, pos)+temp.substring(pos+1,temp.length());
				//Replace a character in String inplace of StringBuffer
				//				String temp2 = temp.substring(0, pos)+ ch + temp.substring(pos+1,temp.length());

				out.add(temp1.toString());
			}
		}

		for(int listIdx = 0; listIdx < originalListSize; listIdx++)
		{
			out.remove(0);
		}
		return out;
	}

	private static void PrintAllPermutationsOfStringMine(ArrayList<String> listOutput,String inputStr, int i,ArrayList<Character> list) {
		// TODO Auto-generated method stub
		if(i == inputStr.length())
		{
			listOutput.add(list.toString());
			return;
		}

		for(int j = 0; j < inputStr.length(); j++)
		{
			if(list.contains(inputStr.charAt(j)))
			{
				continue;
			}
			list.add(inputStr.charAt(j));
			PrintAllPermutationsOfStringMine(listOutput,inputStr,i+1,list);
			list.remove(list.size()-1);
		}
	}
}
