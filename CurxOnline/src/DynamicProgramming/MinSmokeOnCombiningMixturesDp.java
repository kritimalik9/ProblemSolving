package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MinSmokeOnCombiningMixturesDp {

	private static class MixtureSmoke{
		long smoke;
		int mixture;

		public MixtureSmoke(long smoke,int mixture) {
			this.smoke = smoke;
			this.mixture = mixture;			
		}
	}
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numMixtures = scan.nextInt();
		int Mixture[] = new int[numMixtures];
		for(int i = 0 ; i < Mixture.length ; i++ )
		{
			Mixture[i] = scan.nextInt();
		}
		long minSmoke = MinSmokeOnCombiningMixturesRecursiveDp(Mixture);
		System.out.println(minSmoke);
	}

	private static long MinSmokeOnCombiningMixturesRecursiveDp(int[] mixture) {

		MixtureSmoke[][] T = new MixtureSmoke[mixture.length][mixture.length];

		for(int i=0; i < T.length; i++)
		{
			T[i][i] = new MixtureSmoke(0,mixture[i]);
		}

		for(int i = 0; i < mixture.length-1; i++)
		{
			for(int j = i+1; j < mixture.length; j++)
			{
				int p = j-i-1;
				int q = j;
				T[p][q] = new MixtureSmoke(0,0);
				T[p][q].smoke = Integer.MAX_VALUE;
				for(int gap = p ; gap < q ; gap++)
				{
					int mix1 = (T[p][gap].mixture + T[gap+1][q].mixture)%100;
					long mix1Smoke = T[p][gap].mixture * T[gap+1][q].mixture;

					mix1Smoke += T[p][gap].smoke + T[gap+1][q].smoke;

					if(mix1Smoke < T[p][q].smoke)
					{
						T[p][q].smoke = mix1Smoke;
						T[p][q].mixture = mix1;
					}
				}
			}
		}

		return T[0][mixture.length-1].smoke;
	}
}
//package DynamicProgramming;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Set;
// /*WRONG WAY OF CHOOSING RECURSION FOR THIS EXAMPLE. 
// Need to MIX only consecutive elements and NOT every element with every other element*/
//public class MinSmokeOnCombiningMixturesDp {
//
//	public static void main(String[] args) {
//
//		Scanner scan = new Scanner(System.in);
//		int numMixtures = scan.nextInt();
//		int Mixture[] = new int[numMixtures];
//		for(int i = 0 ; i < Mixture.length ; i++ )
//		{
//			Mixture[i] = scan.nextInt();
//		}
//		Map<Set<Integer>,Integer> MixtureSetVsSmoke = new HashMap<>();
//		ArrayList<Integer> InputList = new ArrayList<Integer>();
//		for(int val : Mixture)
//		{
//			InputList.add(val);
//		}
//		int minSmoke = MinSmokeOnCombiningMixturesRecursiveDp(Mixture,MixtureSetVsSmoke,InputList);
//		System.out.println(minSmoke);
//	}
//
//	private static int  MinSmokeOnCombiningMixturesRecursiveDp(int[] mixture,
//			Map<Set<Integer>, Integer> mixtureSetVsSmoke, ArrayList<Integer> InputList) {
//
//
//		if(InputList.size() == 1)
//		{
//			return 0;
//		}
//
//		Set<Integer> set = new HashSet<>();
//		set.addAll(InputList);
//
//		if(mixtureSetVsSmoke.containsKey(set))
//		{
//			return mixtureSetVsSmoke.get(set);
//		}
//
//		int minSmoke = Integer.MAX_VALUE;
//		int smoke = 0;
//		//		for(int i = 0 ; i < InputList.size()-1 ; i++)
//		//		{
//		//			int mixture1 = InputList.remove(i);
//		//			for(int j = i ; j < InputList.size(); j++)
//		//			{
//		//				int mixture2 = InputList.remove(j);
//		for(int i = 0 ; i < InputList.size()-1 ; i++)
//		{
//			int mixture1 = InputList.remove(i);
//			int mixture2 = InputList.remove(i);
//			int newMixture = (mixture1 + mixture2)%100;
//			InputList.add(newMixture);
//			smoke = MinSmokeOnCombiningMixturesRecursiveDp(mixture,mixtureSetVsSmoke,InputList);
//			smoke += (mixture1*mixture2);
//			minSmoke = Math.min(smoke, minSmoke);
//			InputList.remove(InputList.size()-1);
//			InputList.add(i, mixture2);
//			InputList.add(i, mixture1);
//
//		}
//
//		mixtureSetVsSmoke.put(set,minSmoke);
//
//		return minSmoke;
//
//	}
//
//}
