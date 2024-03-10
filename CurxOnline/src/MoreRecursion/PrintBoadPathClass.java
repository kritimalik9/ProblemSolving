package MoreRecursion;

import java.util.Scanner;

public class PrintBoadPathClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int start = scan.nextInt();
		int target = scan.nextInt();
		String result = "";
		System.out.println(PrintBoadPath(target-start,result));

	}

	private static int PrintBoadPath(int target,String result) {
		// TODO Auto-generated method stub
		boolean isTargetDiceVal = false;
		int count = 0;
		if((target == 1) || (target == 2) || (target == 3) || (target == 4) || (target == 5) || (target == 6))
		{
			System.out.println(result+target);
			isTargetDiceVal = true;
			if(target == 1)
			{
				return 1;// this means successfully reached positive base case
			}
		}
		String thisResult = result;
		for(int i = 0 ; (i < target-1) && (i < 6) ; i++)
		{
			int diceVal = i+1;
			int leftTarget = target - diceVal;
			result = thisResult+diceVal;
			int countprev = PrintBoadPath(leftTarget,result);
			count = count+countprev;
		}
		if(isTargetDiceVal)
		{
			count++;// this means successfully reached positive base case
		}
		return count;
	}

}
