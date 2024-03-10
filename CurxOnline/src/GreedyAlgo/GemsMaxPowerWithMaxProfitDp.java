package GreedyAlgo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GemsMaxPowerWithMaxProfitDp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numSeconds = scan.nextInt();
		int numGems = scan.nextInt();
		int numGoldCoins = scan.nextInt();
		int[] gemVal = new int[numGems];
		int originalGemVal = 0;
		for(int i = 0 ; i < numGems ; i++)
		{
			gemVal[i] = scan.nextInt();
			originalGemVal+=gemVal[i];
		}
		ArrayList<ArrayList<Integer>> CostMatrixIthtimeVsJthGem = new ArrayList<>();

		for(int i = 0 ; i < numSeconds ; i++)
		{
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = 0 ; j < numGems ; j++)
			{
				list.add(scan.nextInt());
			}
			CostMatrixIthtimeVsJthGem.add(list);
		}

		long start = System.currentTimeMillis();
		int maxGemPower = originalGemVal+GemsMaxPowerWithMaxProfit(CostMatrixIthtimeVsJthGem,gemVal,numGoldCoins);
		long end = System.currentTimeMillis();
		//System.out.println("Time taken to execute this dp recursive function MINE " + (end-start)+"ms");
		System.out.println(maxGemPower);

	}

	private static int GemsMaxPowerWithMaxProfit(ArrayList<ArrayList<Integer>> costMatrixIthtimeVsJthGem, int[] gemVal,
			int numGoldCoins) {

		int maxGemPower = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < costMatrixIthtimeVsJthGem.size() ; i++)
		{
			for(int j = 0 ; j < costMatrixIthtimeVsJthGem.get(i).size() ; j++)
			{
				list.add(costMatrixIthtimeVsJthGem.get(i).get(j));
			}
		}

		Collections.sort(list);

		for(int j = 0 ; j < list.size() ; j++)
		{
			if(numGoldCoins - list.get(j) >= 0)
			{
				maxGemPower++;
				numGoldCoins = numGoldCoins - list.get(j);
			}
			else
			{
				break;
			}
		}


		return maxGemPower;
	}


}
