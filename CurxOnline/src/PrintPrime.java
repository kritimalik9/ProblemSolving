import java.util.*;
public class PrintPrime {
	public static void main(String args[]) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number");
		int num = scn.nextInt();

		if(num < 1 || num > 1000)
		{
			System.out.println("Number out of range");
		}
		else
		{
			if(num == 1)
			{
				System.out.println("Integer 1 is NOT prime");
			}
			else
			{
				boolean isPrime = true;
				int numberToValidate = 3;
				System.out.println("2");
				while(numberToValidate <=  num)
				{
					int counter = 2;
					isPrime = true;
					while(counter <= (numberToValidate-1))
					{
						if((numberToValidate % counter) == 0)
						{
							isPrime = false;
							break;
						}
						counter++;
					}
					if(isPrime)
					{
						System.out.println(numberToValidate);
					}
					numberToValidate++;
				}
			}
		}
	}
} 