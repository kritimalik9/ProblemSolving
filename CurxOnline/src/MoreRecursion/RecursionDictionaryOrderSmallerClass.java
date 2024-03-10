package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class RecursionDictionaryOrderSmallerClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		ArrayList<String> listOut = RecursionDictionaryOrderSmaller(str,str,true,0,"",false);
		for(String val : listOut)
		{
			System.out.println(val+" ");
		}
	}

	private static ArrayList<String> RecursionDictionaryOrderSmaller(String str, String charList,boolean checkOrder,int pos,String result,boolean IsInSeq) {

		if(charList.length() == 0)
		{
			ArrayList<String> myResultList = new ArrayList<>();
			if(!checkOrder)
			{
				myResultList.add(result);
			}
			return myResultList;
		}
		char charAtCurrentPos = str.charAt(pos);

		ArrayList<String> myResultList = new ArrayList<>();
		for(int i=0; i<charList.length();i++)
		{
			ArrayList<String> rr = null;
			if(checkOrder)
			{
				if(charList.charAt(i) == charAtCurrentPos)
				{
					rr = RecursionDictionaryOrderSmaller(str,charList.substring(0,i)+charList.substring(i+1,charList.length()),true,pos+1,result+charList.charAt(i),IsInSeq);
				}
				else if(charList.charAt(i) < str.charAt(pos))
				{
					rr = RecursionDictionaryOrderSmaller(str,charList.substring(0,i)+charList.substring(i+1,charList.length()),false,pos+1,result+charList.charAt(i),IsInSeq);
				}
				else
				{
					continue;
				}
			}
			else
			{
				if((charList.charAt(i) <= str.charAt(pos))
						|| IsInSeq
						)
				{
					rr = RecursionDictionaryOrderSmaller(str,charList.substring(0,i)+charList.substring(i+1,charList.length()),false,pos,result+charList.charAt(i),true);
				}
				else
				{
					continue;
				}
			}
			for(String val : rr)
			{
				myResultList.add(val);
			}
			checkOrder = false;
		}

		return myResultList;
	}

}
