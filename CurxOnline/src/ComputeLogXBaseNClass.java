import java.util.Scanner;

public class ComputeLogXBaseNClass {
	/*Assume here that X and N are such that Log of X to the base N will be a whole number.*/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter X in LogXBaseN :");
		int X = scan.nextInt();
		System.out.println("Enter N in LogXBaseN :");
		int N = scan.nextInt();
		if((X <= 0) || (X >= 1000000000) || (N <= 0) || (N >= 1000))
		{
			System.out.println("Number out of defined range");
		}
		else
		{
			System.out.println(ComputeLogXBaseN(X,N));
		}


	}

	private static int ComputeLogXBaseN(int x, int n) {
		int logVal = 0;
		while(x != 0)
		{
			if(x % n == 0)
			{
				logVal++;
			}
			x /= n;
		}
		return logVal;
	}
}
