package MoreRecursion;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class GetBoardPathClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int targetNumber = scan.nextInt();
		ArrayList<String> out = GetBoardPathDiceLen6Mentor(targetNumber);
		System.out.println(out.size());
		for(int i = 0;i<out.size();i++)
		{
			System.out.print(out.get(i)+" ");
		}
		System.out.println();
		int success_count = 0;
		int sum = 0;
		for(String val : out)
		{
			sum = 0;
			for(int j = 0 ; j < val.length() ; j++)
			{
				sum+=Integer.parseInt(val.substring(j,j+1));
			}
			if(sum != targetNumber)
			{
				System.out.println("wrong val");
			}
			else
			{
				success_count++;
			}
		}
		System.out.println("Target sum is acheived "+success_count+" times "+ sum);

		Set tempSet = new HashSet();
		for (String str : out) 
		{
			if (!tempSet.add(str)) 
			{
				System.out.println("There is a duplicate "+str);
			}
		}

	}

	public static ArrayList<String> GetBoardPathDiceLen6Mentor(int targetNumber) {


		ArrayList<String> out = new ArrayList<>();
		int diceVal=0;
		int leftTarget=0;

		if((targetNumber == 1) || (targetNumber == 2) || (targetNumber == 3) || (targetNumber == 4) || (targetNumber == 5) || (targetNumber == 6))
		{
			out.add(""+targetNumber);

			if(targetNumber > 1)
			{
				for(int i=0 ; i<(targetNumber-1); i++)
				{
					diceVal = i+1;
					leftTarget = targetNumber-diceVal;
					ArrayList<String> lastList = GetBoardPathDiceLen6Mentor(leftTarget);
					//					String temp = ""+diceVal+leftTarget;
					//					out.add(temp);
					for(String val : lastList)
					{
						String temp1 = ""+diceVal+val;
						if(out.contains(temp1))
							System.out.println("repeat");
						out.add(temp1);
					}
				}
			}
			return out;
		}
		for(int i=0 ; i<(targetNumber-1); i++)
		{
			diceVal = i+1;
			if(diceVal <= 6)
			{
				leftTarget = targetNumber-diceVal;
				ArrayList<String> lastList = GetBoardPathDiceLen6Mentor(leftTarget);
				for(String val : lastList)
				{
					String temp1 = ""+diceVal+val;
					if(out.contains(temp1))
						System.out.println("error! repeat");
					out.add(temp1);
				}
			}
		}
		return out;
	}
}
