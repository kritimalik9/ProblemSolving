import java.util.Scanner;

public class OctalToBinaryConverterClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Octal to Binary Converter : Enter a number in Octal format : ");
		int numberInOctalFormat = scan.nextInt();
		if((numberInOctalFormat <= 0) || (numberInOctalFormat > 1000000000))
		{
			System.out.println("Number out of range");
		}
		else
		{
			long numberInBinaryFormat = OctalToBinaryConverter(numberInOctalFormat);
			System.out.println("It's equivalient binary number is : " + numberInBinaryFormat);
		}
	}

	private static long OctalToBinaryConverter(int numberInOctalFormat) {
		long numberInBinaryFormat = 0;
		long numberInDecimalFormat = 0;
		long multiplier = 1;
		while(numberInOctalFormat != 0)
		{
			int remainder = numberInOctalFormat % 10;
			numberInDecimalFormat = numberInDecimalFormat + remainder*multiplier;
			multiplier = 8 * multiplier;
			numberInOctalFormat = numberInOctalFormat / 10;
		}
		multiplier = 1;
		while(numberInDecimalFormat != 0)
		{
			long remainder = numberInDecimalFormat % 2;
			numberInBinaryFormat = numberInBinaryFormat + remainder*multiplier;
			multiplier = 10 * multiplier;
			numberInDecimalFormat = numberInDecimalFormat / 2;
		}
		return numberInBinaryFormat;
	}
}
