package MoreRecursion;

import java.util.ArrayList;
import java.util.Scanner;

public class SplitArrayInTwoWithEqualSumClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int[] arr = new int[num];
		int sum = 0;

		ArrayList<Integer> exclude_indice_list = new ArrayList<Integer>();
		ArrayList<Integer> include_indice_list = new ArrayList<Integer>();

		for(int i = 0; i < arr.length ; i++)
		{
			arr[i] = scan.nextInt();
			sum+=arr[i];
			exclude_indice_list.add(i);

		}
		int count = SplitArrayInTwoWithEqualSumCount(arr,0,sum,0,exclude_indice_list,include_indice_list);
		System.out.println(count);
		SplitArrayInTwoWithEqualSum(arr,0,sum,0,exclude_indice_list,include_indice_list);


	}

	private static int SplitArrayInTwoWithEqualSum(int[] arr, int i,int sum,int halfSum, ArrayList<Integer> exclude_indice_list, ArrayList<Integer> include_indice_list) {
		int count = 0;
		if(i == arr.length)
		{
			if((halfSum*2) == sum)
			{
				for(Integer val : include_indice_list)
				{
					System.out.print(arr[val]+" ");					
				}
				System.out.print("and ");
				for(Integer val : exclude_indice_list)
				{
					if(val != 88888)
					{
						System.out.print(arr[val]+" ");
					}
				}
				System.out.println();
				return 1;
			}
			else 
			{
				return 0;
			}
		}
		exclude_indice_list.set(i, 88888);
		include_indice_list.add(i);
		halfSum += arr[i];
		count += SplitArrayInTwoWithEqualSum(arr,i+1,sum,halfSum,exclude_indice_list,include_indice_list);
		include_indice_list.remove(include_indice_list.size()-1);
		exclude_indice_list.set(i,i);
		halfSum -= arr[i];

		count += SplitArrayInTwoWithEqualSum(arr,i+1,sum,halfSum,exclude_indice_list, include_indice_list);

		return count;

	}

	private static int SplitArrayInTwoWithEqualSumCount(int[] arr, int i,int sum,int halfSum, ArrayList<Integer> exclude_indice_list, ArrayList<Integer> include_indice_list) {
		int count = 0;
		if(i == arr.length)
		{
			if((halfSum*2) == sum)
			{
				//				for(Integer val : include_indice_list)
				//				{
				//					System.out.print(arr[val]+" ");					
				//				}
				//				System.out.print("and ");
				//				for(Integer val : exclude_indice_list)
				//				{
				//					if(val != 88888)
				//					{
				//						System.out.print(arr[val]+" ");
				//					}
				//				}
				//				System.out.println();
				return 1;
			}
			else 
			{
				return 0;
			}
		}
		exclude_indice_list.set(i, 88888);
		include_indice_list.add(i);
		halfSum += arr[i];
		count += SplitArrayInTwoWithEqualSumCount(arr,i+1,sum,halfSum,exclude_indice_list,include_indice_list);
		include_indice_list.remove(include_indice_list.size()-1);
		exclude_indice_list.set(i,i);
		halfSum -= arr[i];

		count += SplitArrayInTwoWithEqualSumCount(arr,i+1,sum,halfSum,exclude_indice_list, include_indice_list);

		return count;

	}
}
