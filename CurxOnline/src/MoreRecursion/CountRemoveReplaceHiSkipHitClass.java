package MoreRecursion;
import java.util.Scanner;

public class CountRemoveReplaceHiSkipHitClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = "";
		CountRemoveReplaceHiSkipHit(str,0,result,0);

	}

	private static void CountRemoveReplaceHiSkipHit(String str, int i, String result, int count) {

		if(i == str.length())
		{
			System.out.println(count);
			System.out.println(str);
			System.out.println(result);
			return;
		}
		if((str.charAt(i) == 'h') && ((i+1) < str.length()) && (str.charAt(i+1) == 'i')
				&& !(((i+2) < str.length()) && (str.charAt(i+2) == 't'))
				)
		{
			count++;
			CountRemoveReplaceHiSkipHit(str.substring(0,i)+str.substring(i+2,str.length()),i,result+"bye",count);
		}
		else
		{
			CountRemoveReplaceHiSkipHit(str,i+1,result+str.charAt(i),count);
		}

	}

}
