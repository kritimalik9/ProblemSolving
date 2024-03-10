package MoreRecursion;

import java.util.Scanner;

public class OnlyLaddersClass {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int boardLength = scan.nextInt();
		boolean[] primeArray = getPrimeArray(boardLength);
		int[] ladderList = makeLadders(primeArray);
		int countPaths = AllPathsUsingOnlyLaddersCount(ladderList,"0 ",ladderList.length-1,0,false);
		System.out.println(countPaths);
		AllPathsUsingOnlyLadders(ladderList,"0 ",ladderList.length-1,0,false);

	}

	private static int AllPathsUsingOnlyLaddersCount(int[] ladderList, String result,int target,int total,boolean isLadder) {
		if(target == 0)
		{
			if(total <= (ladderList.length-1))
			{	
				//System.out.print(result+"END , ");
				return 1;
			}
			return 0;
		}
		int count = 0;
		for(int diceThrow = 1; diceThrow <= 6 ; diceThrow++ )
		{
			total = total + diceThrow;
			if(total > (ladderList.length-1))
			{
				break;
			}
			if(ladderList[total] != 0)
			{
				int diff = ladderList[total] - total;
				count += AllPathsUsingOnlyLaddersCount(ladderList,result+total+" ", target-diceThrow-diff,total+diff,true);
			}
			else
			{
				if(isLadder)
				{
					count += AllPathsUsingOnlyLaddersCount(ladderList,(total == (ladderList.length-1))?result+(total-diceThrow)+" ":result+total+" ", target-diceThrow,total,false);
				}
				else
				{
					count += AllPathsUsingOnlyLaddersCount(ladderList,(total == (ladderList.length-1))?result:result+total+" ", target-diceThrow,total,isLadder);
				}
			}
			total = total - diceThrow;
		}
		return count;
	}
	private static void AllPathsUsingOnlyLadders(int[] ladderList, String result,int target,int total,boolean isLadder) {
		if(target == 0)
		{
			if(total <= (ladderList.length-1))
			{	
				System.out.print(result+"END , ");
			}
			return;
		}
		for(int diceThrow = 1; diceThrow <= 6 ; diceThrow++ )
		{
			total = total + diceThrow;
			if(total > (ladderList.length-1))
			{
				break;
			}
			if(ladderList[total] != 0)
			{
				int diff = ladderList[total] - total;
				AllPathsUsingOnlyLadders(ladderList,result+total+" ", target-diceThrow-diff,total+diff,true);
			}
			else
			{
				if(isLadder)
				{
					AllPathsUsingOnlyLadders(ladderList,(total == (ladderList.length-1))?result+(total-diceThrow)+" ":result+total+" ", target-diceThrow,total,false);
				}
				else
				{
					AllPathsUsingOnlyLadders(ladderList,(total == (ladderList.length-1))?result:result+total+" ", target-diceThrow,total,isLadder);
				}
			}
			total = total - diceThrow;
		}
	}

	private static int[] makeLadders(boolean[] primeArray) {
		int center = primeArray.length/2;
		int[] ladderList = new int[primeArray.length];
		int left = 1;
		int right = primeArray.length-1;
		while(left < right)
		{
			if(primeArray[left] == true)
			{
				while(right > left)
				{
					if(primeArray[right] == true)
					{
						ladderList[left] = right;
						right--;
						break;
					}
					right--;
				}
			}
			//System.out.println(left+ " "+ladderList[left]);
			left++;
		}
		return ladderList;
	}

	private static boolean[] getPrimeArray(int boardLength) {
		boolean[] primeArray = new boolean[boardLength+1];

		primeArray[0] = false;
		primeArray[1] = false;
		for(int num = 2; num <= boardLength; num++)
		{
			primeArray[num] = true;
			int i = 2;
			while(i < num)
			{
				if(num%i == 0)
				{
					primeArray[num] = false;
				}
				i++;
			}
			//System.out.println(num+ " "+primeArray[num]);
		}
		return primeArray;
	}

}
