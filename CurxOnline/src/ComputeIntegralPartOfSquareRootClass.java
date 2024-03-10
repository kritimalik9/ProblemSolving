import java.util.Scanner;

public class ComputeIntegralPartOfSquareRootClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number whose square root needs to be computed :");
		int number = scan.nextInt();
		if(number < 0 || number >= 1000000000)
		{
			System.out.println("Number out of defined range");
		}
		else
		{
			System.out.println(ComputeIntegralPartOfSquareRoot(number));
		}
	}

	private static int ComputeIntegralPartOfSquareRoot(int number) {
		//int squareRoot = 0;
		if(number == 0)
		{
			return number;
		}
		else
		{
			int nextNum = 1;
			int squareOfNextNum = nextNum*nextNum;
			//int halfOfNumber = number >> 1;
			while(nextNum <= number)
			{
				if(squareOfNextNum > number)
				{
					nextNum--;
					break;
				}
				else if(squareOfNextNum == number)
				{
					break;
				}
				nextNum ++ ;
				squareOfNextNum = nextNum*nextNum;
			} 
			return nextNum;
		}
	}
}
