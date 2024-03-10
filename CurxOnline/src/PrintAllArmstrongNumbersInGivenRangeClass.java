import java.util.Scanner;

public class PrintAllArmstrongNumbersInGivenRangeClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter range N1 :");
		int N1 = scan.nextInt();
		System.out.println("Enter range N2 :");
		int N2 = scan.nextInt();
		if((N1 <= 0) || (N1 >= 1000) || (N2 <= N1) || (N2 >= 10000))
		{
			System.out.println("Defined range is incorrect!");
		}
		else
		{
			PrintAllArmstrongNumbersInGivenRange(N1,N2);
		}


	}

	private static void PrintAllArmstrongNumbersInGivenRange(int n1, int n2) {

		int number = n1;
		while(number <= n2)
		{
			if(IsArmstrongNumber(number))
			{
				System.out.println(number + "  ");
			}
			number++;
		}
		return;
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
