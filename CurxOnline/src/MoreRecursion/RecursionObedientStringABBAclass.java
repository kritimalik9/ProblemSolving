package MoreRecursion;

import java.util.Scanner;

public class RecursionObedientStringABBAclass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();

		boolean out = IsRecursionObedientStringLikeABBA(str,0);
		System.out.println(out);

	}

	private static boolean IsRecursionObedientStringLikeABBA(String str, int i) {

		if(i == str.length())
		{
			return true;
		}

		if(i == 0)
		{
			if(str.charAt(0) != 'a')
			{
				return false;
			}
			else if(i+1 == str.length())
			{
				return true;
			}
			else
			{
				i++;
			}
		}
		boolean isStringObedient = true;
		if((str.charAt(i-1) == 'a')
				&& ((str.charAt(i) == 'a')
						|| (((i+1) < str.length()) && (str.charAt(i) == 'b') && (str.charAt(i+1) == 'b')))
				)
		{
			if(str.charAt(i) == 'a')
			{
				isStringObedient = IsRecursionObedientStringLikeABBA(str,i+1);
			}
			else
			{
				isStringObedient = IsRecursionObedientStringLikeABBA(str,i+2);		
			}
		}
		else if((str.charAt(i) == 'a') 
				&& (((i-2) > 0) && (str.charAt(i-1) == 'b') && (str.charAt(i-2) == 'b'))
				)
		{
			isStringObedient = IsRecursionObedientStringLikeABBA(str,i+1);
		}
		else
		{
			isStringObedient = false;
		}
		return isStringObedient;
	}

}
