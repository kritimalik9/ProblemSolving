package Strings;

import java.util.Scanner;

public class StringCompressionOutputClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String inputString  = scan.nextLine();
		String compressedString = ExtractCompressedString(inputString);
		System.out.println(compressedString);
	}

	private static String ExtractCompressedString(String inputString) {
		StringBuilder outputString = new StringBuilder();
		outputString.append(inputString.charAt(0));

		int count =1;
		int offset = 0;
		int startIn = 1;
		//int startOut = 1;

		while(startIn < inputString.length())
		{
			if(inputString.charAt(startIn) == inputString.charAt(startIn-1))
			{
				count++;				
			}
			else
			{
				if(count > 1)
				{
					String countStr = ""+count;
					offset = countStr.length();
					outputString.append(countStr);
					//startOut += offset;
					count=1;
				}
				outputString.append(inputString.charAt(startIn));
				//startOut++;
			}
			startIn++;
		}

		if(count > 1)
		{
			String countStr = ""+count;
			offset = countStr.length();
			outputString.append(countStr);
		}
		return outputString.toString();

	}

}
