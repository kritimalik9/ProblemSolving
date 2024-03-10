import java.util.Scanner;

public class ComputeN1PowerN2Class {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number1 :");
		int N1 = scan.nextInt();
		System.out.println("Enter number2 :");
		int N2 = scan.nextInt();
		if(N1 <= 0 || N2 >= 1000000000)
		{
			System.out.println("Number out of defined range");
		}
		else
		{
			System.out.println(ComputeN1PowerN2(N1,N2));
		}


	}

	private static int ComputeN1PowerN2(int n1, int n2) {
		int multiplier = n1;
		int N1PowerN2 = 1;
		for(int i = 0; i < n2; i++)
		{
			N1PowerN2 = N1PowerN2*multiplier;
		}        		
		return N1PowerN2;
	}
}
