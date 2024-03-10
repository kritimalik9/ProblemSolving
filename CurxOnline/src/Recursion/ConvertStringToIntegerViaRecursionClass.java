package Recursion;
import java.util.Scanner;

public class ConvertStringToIntegerViaRecursionClass {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String number = scan.nextLine();
		System.out.println(ConvertStringToIntegerViaRecursion(number,number.length()));
	}

	private static int ConvertStringToIntegerViaRecursion(String number,int idx) {
		if(idx == 1)
		{
			return Integer.parseInt(number.substring(idx-1,idx)) ;
		}
		else
		{
			int a = Integer.parseInt(number.substring(idx-1,idx)) ;
			return (a +  10*ConvertStringToIntegerViaRecursion(number,idx-1));

		}
	}
}
