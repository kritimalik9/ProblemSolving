import java.util.Scanner;

public class ValidateCharInputClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a character");
		char userInputChar = scan.next().charAt(0);
		if(userInputChar >= 'a' && userInputChar <= 'z')
		{
			System.out.println("lowercase");
		}
		else if(userInputChar >= 'A' && userInputChar <= 'Z')
		{
			System.out.println("UPPERCASE");
		}
		else
		{
			System.out.println("Invalid");
		}
	}

}
