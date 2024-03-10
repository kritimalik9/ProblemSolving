package MoreRecursion;

import java.util.Scanner;

public class RecursionLexicographicalOrderClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int num=scan.nextInt();
		RecursionLexicographicalOrder(num,0,"");
	}

	private static void RecursionLexicographicalOrder(int num,int pos,String result) {

		if((result.length() !=0 ) && (Integer.parseInt(result) == 0))
		{
			return;
		}

		for(int i=0;i<10;i++)
		{
			if(Integer.parseInt(result+i) <= num)
			{
				System.out.print(result+i+" ");
				RecursionLexicographicalOrder(num,pos+1,result+i);
			}
			else
			{
				break;
			}
		}
		return;
	}

}
