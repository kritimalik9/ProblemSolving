import java.util.Scanner;

public class PrintFahrenheitCelciusTable {

	public static void main(String[] args) {
		int counter = 0;

		while(counter <= 300)
		{
			//			int celsius = ((counter-32)*5)/9;
			//			int celsius = (int)((5.0/9)*(counter-32));
			int celsius = (int)((5.0f/9)*(counter-32));
			System.out.println(counter + "  " + celsius );
			counter+=20;
		}

		Scanner scn = new Scanner(System.in);
		char ch = scn.next().charAt(0);

		if (ch >= 'A' && ch <= 'Z') 
			//		if (ch >= 65 && ch <=90) 
		{
			System.out.println("Uppercase");
		}
		else if(ch >= 97 && ch <= 122)
		{
			System.out.println("Lowercase");
		}
		else
		{
			System.out.println("Invalid Character");
		}
	}

}
