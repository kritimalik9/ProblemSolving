package MoreRecursion;
import java.util.Scanner;

public class RecursionMoveAllXtoEndForInputStringClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String result = "";
		RecursionMoveAllXtoEndForInputString(str,0,result);

	}

	private static void RecursionMoveAllXtoEndForInputString(String str, int i, String result) {

		if(i == str.length())
		{
			System.out.println(str+result);
			return;
		}
		if(str.charAt(i) == 'x')
		{
			RecursionMoveAllXtoEndForInputString(str.substring(0,i)+str.substring(i+1,str.length()),i,result+'x');
		}
		else
		{
			RecursionMoveAllXtoEndForInputString(str,i+1,result);
		}

	}

}
