package Strings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class GetCharWithMaxFreqClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		if((str.length() >= 1) && (str.length() <= 1000))
		{
			System.out.println(GetCharWithMaxFreq(str));
		}
	}

	private static char GetCharWithMaxFreq(String str) {

		HashMap<Character,Integer> eachCharFreqList  = new HashMap<>();

		for(int i = 0 ; i < str.length() ; i++ )
		{
			char ch = str.charAt(i);
			int count = 0;
			if(eachCharFreqList.containsKey(ch))
			{
				count = eachCharFreqList.get(ch);
				count++;
			}
			else
			{
				count = 1;
			}
			eachCharFreqList.put(ch, count);			
		}

		Iterator it = eachCharFreqList.entrySet().iterator();
		int MaxFreq = Integer.MIN_VALUE;
		char charWithMaxFreq = 0;

		while(it.hasNext())
		{
			Map.Entry<Character,Integer> pair = (Map.Entry<Character,Integer>)it.next();
			if(MaxFreq < pair.getValue())
			{
				MaxFreq = pair.getValue();
				charWithMaxFreq = pair.getKey();
			}
		}
		
		return charWithMaxFreq;
	}
}
