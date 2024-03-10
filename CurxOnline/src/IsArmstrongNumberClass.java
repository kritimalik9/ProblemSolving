import java.util.Scanner;

public class IsArmstrongNumberClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number to be checked if it's an armstrong number :");
		int number = scan.nextInt();
		if(number <= 0 || number >= 1000000000)
		{
			System.out.println("Number out of defined range");
		}
		else
		{
			System.out.println(IsArmstrongNumber(number));
		}


	}

	private static boolean IsArmstrongNumber(int number) {
		int ArmstrongComputedNum = 0;
		int numDigitsInNum = 0;
		int numberTemp = number;
		while(numberTemp != 0)
		{
			numDigitsInNum++;
			numberTemp /= 10;
		}
		numberTemp = number;
		while(numberTemp != 0)
		{
			int remainder = numberTemp % 10;
			int multiplier = 1;
			for(int powerVal = 0 ; powerVal < numDigitsInNum ; powerVal++)
			{
				multiplier *= remainder;
			}
			ArmstrongComputedNum += multiplier;
			numberTemp /= 10;
		}
		// TODO Auto-generated method stub
		return (number == ArmstrongComputedNum);
	}
}
