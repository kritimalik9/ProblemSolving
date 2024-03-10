package DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

public class BuyingFruitsDp {

	public static final int numFruits = 3; /*Apple, Mango and orange*/
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numTCs = scan.nextInt();
		int[] TC = new int[numTCs];
		long[][][] SellerVsFruitPrice = null;
		SellerVsFruitPrice = new long[TC.length][][];
		for(int i = 0 ; i < TC.length ; i++)
		{
			int numSellers = scan.nextInt();
			SellerVsFruitPrice[i] = new long[numSellers][3];
			for(int seller = 0 ; seller < numSellers ; seller++)
			{
				for(int j = 0; j < numFruits ; j++)
				{
					SellerVsFruitPrice[i][seller][j] = scan.nextLong();
				}
			}
		}
		long minCost = 0;
		for(int i = 0 ; i < TC.length ; i++)
		{
			//int storage[] = new int[n+1];
			//long start = System.currentTimeMillis();
			minCost = BuyingFruitsDpRecursive(SellerVsFruitPrice[i],new HashMap<String,Long>(),0,0,0,"");
			//long end = System.currentTimeMillis();
			//System.out.println("Time taken to execute this dp recursive function MINE " + (end-start)+"ms");
			System.out.println(minCost);
		}
	}

	private static long BuyingFruitsDpRecursive(long[][] sellerVsFruitPrice, 
			HashMap<String, Long> map,int fruitIdx,int sellerNum,int fruitLockIdx,String keyStr)
	{
		if(sellerNum == sellerVsFruitPrice.length)
		{
			return 0;
		}
		long minCost = Integer.MAX_VALUE;
		long cost1 = Integer.MAX_VALUE; int cost2 = Integer.MAX_VALUE;
		for(int i = fruitIdx; i < numFruits ; i++)
		{
			if((fruitLockIdx == i) && (sellerNum > 0))
			{
				continue;
			}
			if(i == 0)
			{
				String mapKeyStr = "**"+(sellerNum+1)+"*"+"MO";
				//String mapKeyStr = ""+sellerNum+"*"+'A'+"**"+(sellerNum+1)+"*"+"MO";

				if(map.containsKey(mapKeyStr))
				{
					cost1 = map.get(mapKeyStr);
				}
				else
				{
					cost1 = BuyingFruitsDpRecursive(sellerVsFruitPrice,map,
							i+1,sellerNum+1,i,mapKeyStr);
				}
			}
			else if(i == 1)
			{
				String mapKeyStr = "**"+(sellerNum+1)+"*"+"AO";
				//String mapKeyStr = ""+sellerNum+"*"+'M'+"**"+(sellerNum+1)+"*"+"AO";

				if(map.containsKey(mapKeyStr))
				{
					cost1 = map.get(mapKeyStr);
				}
				else
				{
					cost1 = BuyingFruitsDpRecursive(sellerVsFruitPrice,map,
							i-1,sellerNum+1,i,mapKeyStr);
				}
			}
			else if(i == 2)
			{
				String mapKeyStr = "**"+(sellerNum+1)+"*"+"AM";
				//String mapKeyStr = ""+sellerNum+"*"+'O'+"**"+(sellerNum+1)+"*"+"AM";

				if(map.containsKey(mapKeyStr))
				{
					cost1 = map.get(mapKeyStr);
				}
				else
				{
					cost1 = BuyingFruitsDpRecursive(sellerVsFruitPrice,map,
							i-2,sellerNum+1,i,mapKeyStr);
				}
			}
			minCost = Math.min(minCost,sellerVsFruitPrice[sellerNum][i]+cost1);
		}

		map.put(keyStr,minCost);

		return minCost;

	}

}
