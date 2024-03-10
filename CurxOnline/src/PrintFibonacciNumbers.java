import java.util.Scanner;

public class PrintFibonacciNumbers {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows :");
		int num = scn.nextInt();
		if((num <= 0) || (num > 1000))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			int counter = 0;
			int numFirst  = 0;
			int numSecond = 1;
			int sum = 0;
			System.out.println("Then Fibonacci Pattern becomes :");
			while(numFirst <=  num)
			{
				System.out.println(numFirst+" ");
				sum = numFirst + numSecond;
				numFirst = numSecond;
				numSecond = sum;
				counter++;
			}
		}
	}
}

