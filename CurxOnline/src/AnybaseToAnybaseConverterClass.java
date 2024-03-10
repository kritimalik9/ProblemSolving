import java.util.Scanner;

public class AnybaseToAnybaseConverterClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter source number system base : ");
		int sb = scan.nextInt();
		System.out.println("Enter destination number system base : ");
		int db = scan.nextInt();
		System.out.println("Enter number in source format : ");
		int sn = scan.nextInt();
		if((sn <= 0) || (sn > 1000000000))
		{
			System.out.println("Number out of range");
		}
		else
		{
			long numberInDstBaseFormat = AnybaseToAnybaseConverter(sn,sb,db);
			System.out.println("It's equivalient number in base "+db+" is : " + numberInDstBaseFormat);
		}
	}

	private static long AnybaseToAnybaseConverter(int numberInSrcBaseFormat,int srcBase,int dstBase) {
		long numberInDstBaseFormat = 0;
		long numberInDecimalFormat = 0;
		long multiplier = 1;
		while(numberInSrcBaseFormat != 0)
		{
			int remainder = numberInSrcBaseFormat % 10;
			numberInDecimalFormat = numberInDecimalFormat + remainder*multiplier;
			multiplier = srcBase * multiplier;
			numberInSrcBaseFormat = numberInSrcBaseFormat / 10;
		}
		multiplier = 1;
		while(numberInDecimalFormat != 0)
		{
			long remainder = numberInDecimalFormat % dstBase;
			numberInDstBaseFormat = numberInDstBaseFormat + remainder*multiplier;
			multiplier = 10 * multiplier;
			numberInDecimalFormat = numberInDecimalFormat / dstBase;
		}
		return numberInDstBaseFormat;
	}
}
