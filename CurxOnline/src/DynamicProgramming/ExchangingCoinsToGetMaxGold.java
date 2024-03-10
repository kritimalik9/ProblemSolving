package DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

public class ExchangingCoinsToGetMaxGold {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		//int storage[] = new int[n+1];
		long start = System.currentTimeMillis();
		long maxGoldInGrams = ExchangingCoinsToGetMaxGoldRecursiveDp(n,new HashMap<Integer,Long>());
		long end = System.currentTimeMillis();
		//System.out.println("Time taken to execute this dp recursive function MINE " + (end-start)+"ms");
		System.out.println(maxGoldInGrams);

	}

	private static long ExchangingCoinsToGetMaxGoldRecursiveDp(int n, HashMap<Integer, Long> map) {

		if(n <= 10)
		{
			return n;
		}

		if(map.containsKey(n))
		{
			return map.get(n);
		}

		int nBy2 = n / 2;
		int nBy3 = n / 3;
		int nBy4 = n / 4;

		long maxGoldInGramsFrom_nBy2 = ExchangingCoinsToGetMaxGoldRecursiveDp(nBy2,map);
		long maxGoldInGramsFrom_nBy3 = ExchangingCoinsToGetMaxGoldRecursiveDp(nBy3,map);
		long maxGoldInGramsFrom_nBy4 = ExchangingCoinsToGetMaxGoldRecursiveDp(nBy4,map);

		long maxGoldInGrams = Math.max(n,maxGoldInGramsFrom_nBy2+maxGoldInGramsFrom_nBy3+maxGoldInGramsFrom_nBy4);
		map.put(n,maxGoldInGrams);
		return maxGoldInGrams;
	}

}
