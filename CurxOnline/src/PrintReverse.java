import java.util.*;
public class PrintReverse {
	public static void main(String args[]) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number");
		int num = scn.nextInt();

		if(num < 0 || num > 1000000000)
		{
			System.out.println("Number out of range");
		}
		else
		{
			int fetchDigit  = 0;
			int reverseNum  = 0;

			while(num != 0)
			{
				fetchDigit   = (num%10);
				reverseNum   = (reverseNum*10) +  fetchDigit;
				num = num / 10;
			}
			System.out.println(reverseNum);
		}
	}
} 