import java.util.*;
public class SumOfOddPlacedAndEvenPlacedDigits {
	public static void main(String args[]) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number");
		int num = scn.nextInt();

		if(num <=0 || num > 1000000000)
		{
			System.out.println("Number out of range");
		}
		else
		{
			int oddSum  = 0;
			int evenSum = 0;

			while(num != 0)
			{
				oddSum  += (num%10);
				num      = num/10;
				evenSum += (num%10);
				num      = num/10;

			}
			System.out.println(oddSum);
			System.out.println(evenSum);
		}
	}
} 