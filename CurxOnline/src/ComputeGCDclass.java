import java.util.Scanner;

public class ComputeGCDclass {

	public static void main(String[] args) {
		System.out.println("Compute GCD of 2 numbers");
		System.out.println("Number 1");
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		System.out.println("Number 2");
		int num2 = scan.nextInt();

		if((num1 <= 0) || (num1 >= 1000000000) || (num2 <= 0) || (num2 >= 1000000000))
		{
			System.out.println("Numbers not within defined range");
		}
		else
		{
			System.out.println(ComputeGCD(num1,num2));
		}
	}

	private static int ComputeGCD(int divident, int divisor) {
		int remainder = 0;
		while(divisor != 0)
		{
			remainder = divident%divisor;
			divident = divisor;
			divisor = remainder;		
		}
		return divident;
	}

}
