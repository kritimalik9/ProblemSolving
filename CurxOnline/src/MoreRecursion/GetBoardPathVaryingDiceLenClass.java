package MoreRecursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
//import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class GetBoardPathVaryingDiceLenClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int targetNumber = scan.nextInt();
		int diceLen = scan.nextInt();
		ArrayList<String> out = GetBoardPathVaryingDiceLen(targetNumber,diceLen);
		System.out.println(out.size());
		Collections.sort(out);
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
		//System.out.println("Target sum is acheived "+success_count+" times "+ sum);

		Set tempSet = new HashSet();
		for (String str : out) 
		{
			if (!tempSet.add(str)) 
			{
				System.out.println("There is a duplicate "+str);
			}
		}

	}

	private static ArrayList<String> GetBoardPathVaryingDiceLen(int targetNumber, int diceLen) {


		ArrayList<String> out = new ArrayList<>();
		int diceVal=0;
		int leftTarget=0;

		if(targetNumber <= diceLen )
		{
			out.add(""+targetNumber);

			if(targetNumber > 1)
			{
				for(int i=0 ; i<(targetNumber-1); i++)
				{
					diceVal = i+1;
					leftTarget = targetNumber-diceVal;
					ArrayList<String> lastList = GetBoardPathVaryingDiceLen(leftTarget,diceLen);
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
			if(diceVal <= diceLen)
			{
				leftTarget = targetNumber-diceVal;
				ArrayList<String> lastList = GetBoardPathVaryingDiceLen(leftTarget,diceLen);
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
