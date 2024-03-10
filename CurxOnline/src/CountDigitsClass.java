import java.util.Scanner;

public class CountDigitsClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number : ");
		int number = scan.nextInt();
		System.out.println("Enter a digit to be counted for in this number : ");
		int digit = scan.nextInt();

		if((number < 0) || (number > 1000000000) || (digit < 0) || (digit > 9))
		{
			System.out.println("Please specify number and digit within defined range");
		}
		else
		{
			System.out.println(CountDigits(number,digit));
		}
	}

	private static int CountDigits(int number,int digit) {
		int digitCount = 0;
		while(number != 0)
		{
			if(number%10 == digit)
			{
				digitCount++;
			}
			number = number/10;
		}
		return digitCount;
	}

}
