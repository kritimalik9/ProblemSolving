package Sorting;

import java.util.Scanner;

public class BinarySearchClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		System.out.println("Enter the element to be searched for");
		int x = scan.nextInt();
		System.out.println("This element is present at index "+doBinarySearch(arr,x));;

	}
	private static int doBinarySearch(int[] arr, int x) {
		int start = 0;
		int end = arr.length - 1;
		while(start <= end)
		{
			int mid = start + (end - start)/2;
			if(arr[mid] == x)
			{
				return mid;
			}
			else if(x > arr[mid])
			{
				start = mid+1;
			}
			else
			{
				end = mid-1;
			}

		}
		return -1;
	}
	private static int[] take1DArrayInput() {
		System.out.println("Enter the array size");
		int[] arr = new int[scan.nextInt()];
		for(int i = 0 ; i < arr.length ; i++)
		{
			System.out.println("Enter element at index "+i);
			arr[i] = scan.nextInt();
		}
		return arr;
	}

}
