package MoreRecursion;

import java.util.Scanner;

public class SnakesAndLaddersClass {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int boardLength = scan.nextInt();
		int numThrows = scan.nextInt();
		int[] throwVal = new int[numThrows];
		for(int i = 0; i< numThrows ; i++)
		{
			throwVal[i] = scan.nextInt();
		}
		boolean[] primeArray = getPrimeArray(boardLength);
		int[] ladderList = makeLadders(primeArray);
		int[] snakeList = makeSnakes(primeArray);
		boolean isTargetDone = AllPathsUsingSnakesAndLadders(ladderList,snakeList,"0 ",ladderList.length-1,0,false,throwVal,0);
		System.out.println(isTargetDone);

	}
	private static boolean AllPathsUsingSnakesAndLadders(int[] ladderList, int[] snakeList, String result,int target,int total,boolean isLadder,int[] throwVal,int throwCount) {

		if(throwCount == throwVal.length)
		{
			if(target == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		boolean isTargetDone = false;
		{
			total = total + throwVal[throwCount];
			if(total >= (ladderList.length-1))
			{
				return true;
			}
			if(ladderList[total] != 0)
			{
				int diff = ladderList[total] - total;
				isTargetDone = AllPathsUsingSnakesAndLadders(ladderList,snakeList,result+total+" ", target-throwVal[throwCount]-diff,total+diff,true,throwVal,throwCount+1);
			}
			else if(snakeList[total] != 0)
			{
				int diff = total-snakeList[total];
				isTargetDone = AllPathsUsingSnakesAndLadders(ladderList,snakeList,result+snakeList[total]+" ", target-throwVal[throwCount]-diff,total-diff,true,throwVal,throwCount+1);
			}
			else
			{
				if(isLadder)
				{
					isTargetDone = AllPathsUsingSnakesAndLadders(ladderList,snakeList,(total == (ladderList.length-1))?result+(total-throwVal[throwCount])+" ":result+total+" ", target-throwVal[throwCount],total,false,throwVal,throwCount+1);
				}
				else
				{
					isTargetDone = AllPathsUsingSnakesAndLadders(ladderList,snakeList,(total == (ladderList.length-1))?result:result+total+" ", target-throwVal[throwCount],total,isLadder,throwVal,throwCount+1);
				}
			}
			total = total - throwVal[throwCount];
		}
		return isTargetDone;
	}

	private static int[] makeSnakes(boolean[] primeArray) {
		int center = primeArray.length/2;
		int[] snakeList = new int[primeArray.length];
		int left = 1;
		int right = primeArray.length-1;
		boolean isFound = true;
		while(left < right)
		{
			if(primeArray[left] == true)
			{
				while(right > left)
				{
					if(primeArray[right] == true)
					{
						if(!isFound)
						{
							snakeList[right] = left;
							isFound = true;
						}
						else
						{
							isFound = false;
						}
						right--;
						break;
					}
					right--;
				}
			}
			//System.out.println(left+ " "+snakeList[left]);
			left++;
		}
		return snakeList;
	}


	private static int[] makeLadders(boolean[] primeArray) {
		int center = primeArray.length/2;
		int[] ladderList = new int[primeArray.length];
		int left = 1;
		int right = primeArray.length-1;
		boolean isFound = false;
		while(left < right)
		{
			if(primeArray[left] == true)
			{
				while(right > left)
				{
					if(primeArray[right] == true)
					{
						if(!isFound)
						{
							ladderList[left] = right;
							isFound = true;
						}
						else
						{
							isFound = false;
						}
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
