import java.util.*;
public class FindPrime {
	public static void main(String args[]) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number");
		int num = scn.nextInt();

		if(num <=2 || num > 1000000000)
		{
			System.out.println("Number out of range");
		}
		else
		{
			int counter = 2;
			boolean isPrime = true;

			while(counter <= (num-1))
			{
				if((num % counter) == 0)
				{
					isPrime = false;
					break;
				}
				counter++;
			}
			if(isPrime)
			{
				System.out.println("Prime");
			}
			else
			{
				System.out.println("NotPrime");
			}
		}
	}
} 