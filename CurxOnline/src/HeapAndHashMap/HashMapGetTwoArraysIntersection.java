package HeapAndHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HashMapGetTwoArraysIntersection {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int arr1Len = scan.nextInt();

		int[] arr1 = new int[arr1Len]; 

		for(int i = 0 ; i < arr1Len; i++)
		{
			arr1[i] = scan.nextInt();
		}

		int arr2Len = scan.nextInt();

		int[] arr2 = new int[arr2Len]; 

		for(int i = 0 ; i < arr2Len; i++)
		{
			arr2[i] = scan.nextInt();
		}

		HashMap<Integer,Integer> map = new HashMap<>();

		for(int i = 0; i< arr1.length; i++)
		{
			if(!map.containsKey(arr1[i]))
			{
				map.put(arr1[i], 1);
			}
			else
			{
				int count = map.get(arr1[i]);
				count++;
				map.put(arr1[i],count);
			}
		}

		for(int i = 0; i< arr2.length; i++)
		{
			if(!map.containsKey(arr2[i]))
			{
				map.put(arr2[i], 1);
			}
			else
			{
				int count = map.get(arr2[i]);
				count--;
				map.put(arr2[i],count);
			}
		}

		Set<Map.Entry<Integer, Integer>> enteries= map.entrySet();
		System.out.println(enteries.toString());

		ArrayList<Integer> out = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry : enteries)
		{
			if(entry.getValue() == 0)
			{
				out.add(entry.getKey());
			}
		}
		System.out.println(out);
	}

}
