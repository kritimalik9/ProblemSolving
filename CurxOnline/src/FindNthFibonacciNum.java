import java.util.Scanner;

public class FindNthFibonacciNum {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number N");
		int num = scn.nextInt();
		if((num < 0) || (num > 1000))
		{
			System.out.println("number N out of range");
		}
		else
		{
			int first  = 0;
			int second = 1;
			if((num == 0) || (num == 1))
			{
				System.out.println(num + "th Fibonacci number is " + num);
			}
			else
			{	
				int counter = 2;
				int sum = 0;
				while(counter <= num)
				{
					sum = first + second;
					first = second;
					second = sum;
					counter++;
				}
				System.out.println(num + "th Fibonacci number is " + sum);
			}
		}
	}

}
