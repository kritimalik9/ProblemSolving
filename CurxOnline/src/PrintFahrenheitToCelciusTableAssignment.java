import java.util.Scanner;

public class PrintFahrenheitToCelciusTableAssignment {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Min Fahrenheit Value :");
		int minF = scan.nextInt();
		System.out.println("Enter Max Fahrenheit Value :");
		int maxF = scan.nextInt();
		System.out.println("Enter stepsize of Fahrenheit to be kept :");
		int stepsizeF = scan.nextInt();

		if((minF < 0) || (minF >= 100) || (maxF <= minF) || (maxF >= 500))
		{
			System.out.println("Given values doesnt fit into the constraints provided to compute Fahrenheit To Celcius Table!");
		}
		else
		{
			PrintFahrenheitToCelciusTable(minF,maxF,stepsizeF);
		}
	}

	private static void PrintFahrenheitToCelciusTable(int minF, int maxF, int stepsizeF) {

		int startF = minF ;
		int endF  =  maxF ;

		while(startF <= endF)
		{
			//			int celcius = (5*(startF-32))/9;
			int celcius = (int)((5.0f/9)*(startF-32));
			System.out.println(startF + " " +celcius);
			startF = startF + stepsizeF;
		}
	}

}
