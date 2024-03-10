package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
//import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

import MoreRecursion.GetBoardPathClass;

public class GetBoardPathUsingDpClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int targetNumber = scan.nextInt();
		long start = System.currentTimeMillis();
		ArrayList<String> out = null;
		int count=0;
		//count = GetBoardPathDiceLen6MentorNormalRecursion(targetNumber,new HashMap<Integer,Integer>());
		long end = System.currentTimeMillis();
		System.out.println("Time taken to execute this base recursion function " + (end-start)+"ms");
		System.out.println(count);
		//System.out.println(out.size());
		start = System.currentTimeMillis();
		count = GetBoardPathDiceLen6MentorDPRecursive(targetNumber,new HashMap<Integer,Integer>());
		end = System.currentTimeMillis();
		System.out.println("Time taken to execute this dp recursive function " + (end-start)+"ms");
		System.out.println(count);
		start = System.currentTimeMillis();
		count = GetBoardPathDiceLen6MentorDPIterative(targetNumber);
		end = System.currentTimeMillis();
		System.out.println("Time taken to execute this dp iterative function " + (end-start)+"ms");
		System.out.println(count);
	}

	private static int GetBoardPathDiceLen6MentorDPRecursive(int targetNumber, Map<Integer, Integer> map) {


		ArrayList<String> out = new ArrayList<>();
		int diceVal=0;
		int leftTarget=0;
		int count = 0;

		if((targetNumber == 1) || (targetNumber == 2) || (targetNumber == 3) || (targetNumber == 4) || (targetNumber == 5) || (targetNumber == 6))
		{
			//out.add(""+targetNumber);
			count++;

			if(targetNumber > 1)
			{
				for(int i=0 ; i<(targetNumber-1); i++)
				{
					diceVal = i+1;
					leftTarget = targetNumber-diceVal;
					ArrayList<String> lastList = null;
					if(map.containsKey(leftTarget))
					{
						count += map.get(leftTarget);
					}
					else
					{
						count += GetBoardPathDiceLen6MentorDPRecursive(leftTarget,map);
					}
					//					for(String val : lastList)
					//					{
					//						String temp1 = ""+diceVal+val;
					//						if(out.contains(temp1))
					//							System.out.println("repeat");
					//						out.add(temp1);
					//					}
				}
			}
			map.put(targetNumber,count);
			return count;
		}
		for(int i=0 ; i<(targetNumber-1); i++)
		{
			diceVal = i+1;
			if(diceVal <= 6)
			{
				leftTarget = targetNumber-diceVal;
				ArrayList<String> lastList = null;
				if(map.containsKey(leftTarget))
				{
					count += map.get(leftTarget);
				}
				else
				{
					count += GetBoardPathDiceLen6MentorDPRecursive(leftTarget,map);
				}
				//				for(String val : lastList)
				//				{
				//					String temp1 = ""+diceVal+val;
				//					if(out.contains(temp1))
				//						System.out.println("error! repeat");
				//					out.add(temp1);
				//				}
			}
		}
		map.put(targetNumber, count);
		return count;
	}

	private static int GetBoardPathDiceLen6MentorNormalRecursion(int targetNumber, Map<Integer, Integer> map) {


		ArrayList<String> out = new ArrayList<>();
		int diceVal=0;
		int leftTarget=0;
		int count = 0;

		if((targetNumber == 1) || (targetNumber == 2) || (targetNumber == 3) || (targetNumber == 4) || (targetNumber == 5) || (targetNumber == 6))
		{
			//out.add(""+targetNumber);
			count++;

			if(targetNumber > 1)
			{
				for(int i=0 ; i<(targetNumber-1); i++)
				{
					diceVal = i+1;
					leftTarget = targetNumber-diceVal;
					ArrayList<String> lastList = null;
					/*if(map.containsKey(leftTarget))
					{
						count += map.get(leftTarget);
					}
					else*/
					{
						count += GetBoardPathDiceLen6MentorNormalRecursion(leftTarget,map);
					}
					//					for(String val : lastList)
					//					{
					//						String temp1 = ""+diceVal+val;
					//						if(out.contains(temp1))
					//							System.out.println("repeat");
					//						out.add(temp1);
					//					}
				}
			}
			map.put(targetNumber,count);
			return count;
		}
		for(int i=0 ; i<(targetNumber-1); i++)
		{
			diceVal = i+1;
			if(diceVal <= 6)
			{
				leftTarget = targetNumber-diceVal;
				ArrayList<String> lastList = null;
				/*if(map.containsKey(leftTarget))
				{
					count += map.get(leftTarget);
				}
				else*/
				{
					count += GetBoardPathDiceLen6MentorNormalRecursion(leftTarget,map);
				}
			}
		}
		map.put(targetNumber, count);
		return count;
	}

	public static int GetBoardPathDiceLen6MentorDPIterative(int targetNumber)
	{
		int[] storage = new int[targetNumber+1];
		for(int i=0;i<storage.length;i++)
		{
			storage[i] = 0;
		}
		storage[0] = 0;
		storage[1] = 1;

		for(int i = 2; i<= targetNumber; i++)
		{
			int dice=i-1;
			if(i <= 6)
			{
				storage[i] = 1;
			}
			while((dice > 0) && (i-dice <= 6))
			{
				storage[i] += storage[dice];
				dice--;
			}
		}

		return storage[targetNumber];

	}
}
