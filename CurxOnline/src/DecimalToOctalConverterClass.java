import java.util.Scanner;

public class DecimalToOctalConverterClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Decimal to Octal Converter : Enter a number in decimal format : ");
		int numberInDecimalFormat = scan.nextInt();
		if((numberInDecimalFormat <= 0) || (numberInDecimalFormat > 1000000000))
		{
			System.out.println("Number out of range");
		}
		else
		{
			long numberInOctalFormat = DecimalToOctalConverter(numberInDecimalFormat);
			System.out.println("It's equivalient octal number is : " + numberInOctalFormat);
		}
	}

	private static long DecimalToOctalConverter(int numberInDecimalFormat) {
		long numberInOctalFormat = 0;
		long multiplier = 1;
		while(numberInDecimalFormat != 0)
		{
			int remainder = numberInDecimalFormat % 8;
			numberInOctalFormat = numberInOctalFormat + remainder*multiplier;
			multiplier = 10 * multiplier;
			numberInDecimalFormat = numberInDecimalFormat / 8;
		}
		return numberInOctalFormat;
	}
}
