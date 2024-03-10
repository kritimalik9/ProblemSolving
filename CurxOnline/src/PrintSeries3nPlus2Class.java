import java.util.Scanner;

public class PrintSeries3nPlus2Class {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of elements to be printed in series :");
		int numElements = scan.nextInt();
		System.out.println("Enter multiplier which should not be present in any of the elements printed in series :");
		int multiplier = scan.nextInt();
		if((numElements <= 0) || (numElements >= 100) || (multiplier <= 0) || (multiplier >= 100))
		{
			System.out.println("Inputs out of defined range");
		}
		else
		{
			PrintSeries3nPlus2(numElements,multiplier);
		}
	}

	private static void PrintSeries3nPlus2(int numElements, int multiplier) {
		int countElements = 0;
		int nIn3nPlus2 = 1;
		int ThreeMultiplyPlus2 = 0;
		while(countElements != numElements)
		{
			ThreeMultiplyPlus2 = nIn3nPlus2*3+2;
			if(ThreeMultiplyPlus2 % multiplier != 0)
			{
				System.out.println(ThreeMultiplyPlus2);
				countElements++;
			}

			nIn3nPlus2++;
		}
		return;
	}

}
