import java.util.Scanner;

public class IsMirrorInverseClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number whose inverse is to be computed");
		int number = scan.nextInt();
		//need to check uniqueness of each digit in this number!!!
		System.out.println(IsMirrorInverse(number));
	}

	private static boolean IsMirrorInverse(int number) {
		// TODO Auto-generated method stub
		return (number == InverseOfANumber(number));
	}

	private static int InverseOfANumber(int number) {
		int numberInverse = 0;
		int position = 1;
		while(number != 0)
		{
			int valAtPosition = number % 10;
			numberInverse += position * computeTenPowerN(valAtPosition-1);
			position++;
			number /= 10;
		}
		return numberInverse;
	}

	private static int computeTenPowerN(int N) {
		int tenPowerN = 1;
		for(int i = 0; i< N ; i++)
		{
			tenPowerN *= 10;
		}
		return tenPowerN;
	}

}
