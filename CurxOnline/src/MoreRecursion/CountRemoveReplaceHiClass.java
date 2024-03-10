package MoreRecursion;
import java.util.Scanner;

public class CountRemoveReplaceHiClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = "";
		CountRemoveReplaceHi(str,0,result,0);

	}

	private static void CountRemoveReplaceHi(String str, int i, String result, int count) {

		if(i == str.length())
		{
			System.out.println(count);
			System.out.println(str);
			System.out.println(result);
			return;
		}
		if((str.charAt(i) == 'h') && ((i+1) < str.length()) && (str.charAt(i+1) == 'i') )
		{
			count++;
			CountRemoveReplaceHi(str.substring(0,i)+str.substring(i+2,str.length()),i,result+"bye",count);
		}
		else
		{
			CountRemoveReplaceHi(str,i+1,result+str.charAt(i),count);
		}

	}

}
