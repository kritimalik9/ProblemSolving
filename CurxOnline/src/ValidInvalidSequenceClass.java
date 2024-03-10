import java.util.Scanner;

public class ValidInvalidSequenceClass {

	public static void main(String[] args) {
		System.out.println("Enter a number N");
		Scanner scan =new Scanner(System.in);
		int number = scan.nextInt();
		if(number <= 0 || number >= 1000)
		{
			System.out.println("Number N not in defined range");
		}
		else
		{
			int seqI = 0;
			int seqPrevI = 0;
			boolean isDecreasing = true;
			boolean isIncreasing = false;
			boolean notAValidSeq = false;
			for(int i = 0 ; i < number ; i++)
			{
				seqI = scan.nextInt();
				if(seqI <= 0 || seqI >= 1000000000)
				{
					System.out.println("Invalid input to form the sequence");
					break;
				}
				else
				{
					if((i != 0) && (!notAValidSeq))
					{
						if(seqPrevI == seqI)
						{
							isDecreasing = false;
							isIncreasing = false;
							notAValidSeq = true;
						}
						else if(seqPrevI < seqI)
						{
							isIncreasing = true;
						}
						else if(seqPrevI > seqI)
						{
							if(isIncreasing)
							{
								isDecreasing = false;
								isIncreasing = false;
								notAValidSeq = true;
							}
							else
							{
								isDecreasing = true;
							}
						}
						else
						{
							isDecreasing = false;
							isIncreasing = false;
						}
					}
					seqPrevI = seqI;
				}
			}
			System.out.println(isIncreasing || isDecreasing);
		}
	}

}
