import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IntersectionOfTwoArraysClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the array : ");
		int N = scan.nextInt();
		if(N <= 0 || N > 1000)
		{
			System.out.println("Invalid size of input array");
		}
		else
		{
			int[] arr1 = new int[N];
			for(int i=0; i<N; i++)
			{
				//System.out.println("Enter element arr["+i+"] = ");
				arr1[i] = scan.nextInt();
			}
			int[] arr2 = new int[N];
			for(int i=0; i<N; i++)
			{
				//System.out.println("Enter element arr["+i+"] = ");
				arr2[i] = scan.nextInt();
			}
			System.out.println("Intersection of 2 Arrays: ");
			ArrayList<Integer> list  = IntersectionOfTwoArrays(arr1,arr2);
			Collections.sort(list);
			System.out.println(list);
		}
	}

	private static ArrayList<Integer> IntersectionOfTwoArrays(int[] arr1, int[] arr2) {
		ArrayList<Integer> listIndex = new ArrayList<>();
		ArrayList<Integer> listVal   = new ArrayList<>();

		for(int i=0 ; i < arr1.length ; i++ )
		{
			for(int j = 0 ; j< arr2.length ; j++)
			{
				boolean isSkipThisJ = false;
				for(int indexJ : listIndex)
				{
					if(indexJ == j)
					{
						isSkipThisJ = true;
						break;
					}						
				}
				if(!isSkipThisJ)
				{
					if(arr1[i] == arr2[j])
					{
						listVal.add(arr1[i]);
						listIndex.add(j);
						break;
					}
				}
			}
		}
		return listVal;
	}
}
