package DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

public class LongestCommonSubseqWIthThreeStringsClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String str1 = scan.nextLine();
		String str2 = scan.nextLine();
		String str3 = scan.nextLine();

		int count  = 0;
		long start = 0;
		long end = 0;

		start = System.currentTimeMillis();
		count = LongestSubseqInTwoStringsDpIterative(str1,str2,str3);
		end = System.currentTimeMillis();
		//System.out.println("Time taken to execute this dp recursive function MINE " + (end-start)+"ms");
		System.out.println(count);
	}

	/*My recursive dp function takes 13s approx for following input
	 AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12AGGT12
     12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB12TXAYB
     12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA12XBA
	 */
	private static int LongestSubseqInTwoStringsDpRecursive(String str1, String str2, String str3,
			HashMap<String, Integer> map) {
		if((str1.length() == 0) || (str2.length() == 0) || (str3.length() == 0))
		{
			return 0;
		}
		String mapKey = str1+"*"+str2+"*"+str3;
		if(map.containsKey(mapKey))
		{
			return map.get(mapKey);
		}
		int maxCharMatch = 0;
		if((str1.charAt(0) == str2.charAt(0))
				&& (str2.charAt(0) == str3.charAt(0)))
		{
			maxCharMatch = 1+LongestSubseqInTwoStringsDpRecursive(str1.substring(1,str1.length()), str2.substring(1,str2.length()), str3.substring(1,str3.length()),map);
		}
		else
		{
			int maxCharMatch1 = LongestSubseqInTwoStringsDpRecursive(str1,str2, str3.substring(1,str3.length()),map);
			int maxCharMatch2 = LongestSubseqInTwoStringsDpRecursive(str1,str2.substring(1,str2.length()), str3,map);
			int maxCharMatch3 = LongestSubseqInTwoStringsDpRecursive(str1.substring(1,str1.length()),str2, str3.substring(1,str3.length()),map);
			int maxCharMatch4 = LongestSubseqInTwoStringsDpRecursive(str1.substring(1,str1.length()),str2.substring(1,str2.length()), str3,map);
			int maxCharMatch5 = LongestSubseqInTwoStringsDpRecursive(str1.substring(1,str1.length()),str2, str3,map);

			maxCharMatch = Math.max(maxCharMatch1, maxCharMatch2);
			maxCharMatch = Math.max(maxCharMatch, maxCharMatch3);
			maxCharMatch = Math.max(maxCharMatch, maxCharMatch4);
			maxCharMatch = Math.max(maxCharMatch, maxCharMatch5);


		}
		map.put(mapKey,maxCharMatch);

		return maxCharMatch;
	}

	private static int LongestSubseqInTwoStringsDpIterative(String str1, String str2, String str3) {
		
		int[][][] T = new int[str1.length()+1][str2.length()+1][str3.length()+1];
		
		for(int i=0; i<= str1.length(); i++)
		{
			for(int j=0; j<= str2.length(); j++)
			{
				for(int k=0; k<= str3.length(); k++)
				{
					if(i == 0 || j == 0 || k ==0)
					{
						T[i][j][k] = 0;
						continue;
					}
					int p = i-1;
					int q = j-1;
					int r = k-1;
					if((str1.charAt(p) == str2.charAt(q))
							&& (str2.charAt(q) == str3.charAt(r)))	
					{
						T[i][j][k] = 1 + T[i-1][j-1][k-1]; 
					}
					else
					{
						int maxCharMatch1 = T[i][j][k-1];
						int maxCharMatch2 = T[i][j-1][k];
						int maxCharMatch3 = T[i-1][j][k-1];
						int maxCharMatch4 = T[i-1][j-1][k];;
						int maxCharMatch5 = T[i-1][j][k];

						T[i][j][k]  = Math.max(maxCharMatch1, maxCharMatch2);
						T[i][j][k]  = Math.max(T[i][j][k] , maxCharMatch3);
						T[i][j][k]  = Math.max(T[i][j][k] , maxCharMatch4);
						T[i][j][k]  = Math.max(T[i][j][k] , maxCharMatch5);				
					}
				}
			}	
		}
		
		return T[str1.length()][str2.length()][str3.length()];
	
	}
}
