package MoreRecursion;
import java.util.Scanner;

public class CountNumberOfAAAInStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		CountNumberOfAAAInString(str,0,0,0,0);

	}

	private static void CountNumberOfAAAInString(String str, 
			int i, 
			int overlapCount,
			int nonOverlapCount,
			int nonOverlapStart) 
	{

		if(i == str.length())
		{
			System.out.println(overlapCount);
			System.out.println(nonOverlapCount);
			return;
		}
		if(((i+2) < str.length()) 
				&& (str.charAt(i) == str.charAt(i+1)) 
				&& (str.charAt(i) == str.charAt(i+2))
				)
		{
			overlapCount++;
			if(nonOverlapStart == i)
			{
				nonOverlapCount++;
				nonOverlapStart = i+3;
			}
		}
		CountNumberOfAAAInString(str,i+1,overlapCount,nonOverlapCount,nonOverlapStart);
	}

}
