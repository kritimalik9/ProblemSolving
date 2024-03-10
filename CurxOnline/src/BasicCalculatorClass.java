import java.util.Scanner;

public class BasicCalculatorClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		while(true)
		{
			System.out.println("Enter the operation to be performed");
			char operator = scan.next().charAt(0);
			if((operator == '+') || (operator == '-') || (operator == '*') || (operator == '/') || (operator == '%'))
			{
				System.out.println("Enter first number N1");
				int N1 = scan.nextInt();
				System.out.println("Enter first number N2");
				int N2 = scan.nextInt();
				if((N1 <= 0)
						|| (N1 >= 1000000000)
						|| (N2 <= 0)
						|| (N2 >= 1000000000)
						|| ((N2 == 0) && (operator == '/'))
						|| ((N2 == 0) && (operator == '%'))
						)
				{
					System.out.println("Numbers N1 and N2 not in defined range for this operation");
				}
				else
				{
					System.out.println(getCalcOutput(operator,N1,N2));
				}

			}
			else if((operator == 'x') || (operator == 'X'))
			{
				break;
			}
			else
			{
				System.out.println("Invalid operation. Try again.");
			}
		}

	}

	private static int getCalcOutput(char operator, int n1, int n2) {
		if(operator == '+')
		{
			return (n1+n2);
		}
		else if(operator == '-')
		{
			return (n1-n2);
		}
		else if(operator == '*')
		{
			return (n1*n2);	
		}
		else if(operator == '/')
		{
			return (n1/n2);
		}
		else if(operator == '%')
		{
			return (n1%n2);
		}
		else
		{
			return 0;
		}
	}

}
