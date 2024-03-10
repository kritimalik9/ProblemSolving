package DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

public class LongestSubseqInTwoStringsClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String str1 = scan.nextLine();
		String str2 = scan.nextLine();

		int count  = 0;

		long start = System.currentTimeMillis();
		count = LongestSubseqInTwoStrings(str1,str2,0);
		long end = System.currentTimeMillis();
		System.out.println("Time taken to execute this normal recursive function MINE " + (end-start)+"ms");
		System.out.println(count);

		start = System.currentTimeMillis();
		count = LongestSubseqInTwoStringsMentor(str1,str2);
		end = System.currentTimeMillis();
		System.out.println("Time taken to execute this normal recursive function MENTOR " + (end-start)+"ms");
		System.out.println(count);

		start = System.currentTimeMillis();
		count = LongestSubseqInTwoStringsDpRecursive(str1,str2,0,new HashMap<String,Integer>());
		end = System.currentTimeMillis();
		System.out.println("Time taken to execute this dp recursive function MINE " + (end-start)+"ms");
		System.out.println(count);
	}

	private static int LongestSubseqInTwoStringsDpRecursive(String str1, String str2, int numCharMatch,
			HashMap<String, Integer> map) {
		if((str1.length() == 0) || (str2.length() == 0))
		{
			return numCharMatch;
		}
		String mapKey = str1+"*"+str2;
		if(map.containsKey(mapKey))
		{
			return (numCharMatch + map.get(mapKey));
		}
		int maxCharMatch = 0;
		if(str1.charAt(0) == str2.charAt(0))
		{
			numCharMatch++; 
			maxCharMatch = LongestSubseqInTwoStringsDpRecursive(str1.substring(1,str1.length()), str2.substring(1,str2.length()), numCharMatch,map);
			map.put(mapKey,maxCharMatch >= numCharMatch ? (maxCharMatch-numCharMatch+1) : 0);

		}
		else
		{
			int maxCharMatch1 = LongestSubseqInTwoStringsDpRecursive(str1, str2.substring(1,str2.length()), numCharMatch,map);
			int maxCharMatch2 = LongestSubseqInTwoStringsDpRecursive(str1.substring(1,str1.length()), str2, numCharMatch,map);
			maxCharMatch = Math.max(maxCharMatch1, maxCharMatch2);
			map.put(mapKey,maxCharMatch > numCharMatch ? (maxCharMatch-numCharMatch) : 0);

		}
		return maxCharMatch;
	}

	private static int LongestSubseqInTwoStrings(String str1, String str2,int numCharMatch) {

		if((str1.length() == 0) || (str2.length() == 0))
		{
			return numCharMatch;
		}
		int maxCharMatch = 0;
		if(str1.charAt(0) == str2.charAt(0))
		{
			numCharMatch++; 
			maxCharMatch = LongestSubseqInTwoStrings(str1.substring(1,str1.length()), str2.substring(1,str2.length()), numCharMatch);
		}
		else
		{
			int maxCharMatch1 = LongestSubseqInTwoStrings(str1, str2.substring(1,str2.length()), numCharMatch);
			int maxCharMatch2 = LongestSubseqInTwoStrings(str1.substring(1,str1.length()), str2, numCharMatch);
			maxCharMatch = Math.max(maxCharMatch1, maxCharMatch2);
		}

		return maxCharMatch;
	}


	private static int LongestSubseqInTwoStringsMentor(String str1, String str2) {

		if((str1.length() == 0) || (str2.length() == 0))
		{
			return 0;
		}
		int maxCharMatch = 0;
		if(str1.charAt(0) == str2.charAt(0))
		{
			maxCharMatch = 1 + LongestSubseqInTwoStringsMentor(str1.substring(1,str1.length()), str2.substring(1,str2.length()));
		}
		else
		{
			int maxCharMatch1 = LongestSubseqInTwoStringsMentor(str1, str2.substring(1,str2.length()));
			int maxCharMatch2 = LongestSubseqInTwoStringsMentor(str1.substring(1,str1.length()), str2);
			maxCharMatch = Math.max(maxCharMatch1, maxCharMatch2);
		}

		return maxCharMatch;
	}

}
