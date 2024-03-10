import java.util.Scanner;

public class BinaryToDecimalConverterClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Binary to Decimal Converter : Enter a number in binary format : ");
		int numberInBinaryFormat = scan.nextInt();
		if((numberInBinaryFormat <= 0) || (numberInBinaryFormat > 1000000000))
		{
			System.out.println("Number out of range");
		}
		else
		{
			int numberInDecimalFormat = BinaryToDecimalConverter(numberInBinaryFormat);
			System.out.println("It's equivalient decimal number is : " + numberInDecimalFormat);
		}
	}

	private static int BinaryToDecimalConverter(int numberInBinaryFormat) {
		int numberInDecimalFormat = 0;
		int multiplier = 1;
		while(numberInBinaryFormat != 0)
		{
			int remainder = numberInBinaryFormat % 10;
			numberInDecimalFormat = numberInDecimalFormat + remainder*multiplier;
			multiplier = 2 * multiplier;
			numberInBinaryFormat = numberInBinaryFormat / 10;
		}
		return numberInDecimalFormat;
	}
}
