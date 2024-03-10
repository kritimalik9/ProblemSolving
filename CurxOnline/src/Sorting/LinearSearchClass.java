package Sorting;

import java.util.Scanner;

public class LinearSearchClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		System.out.println("Enter the element to be searched for");
		int x = scan.nextInt();
		System.out.println("This element is present at index "+doLinearSearch(arr,x));;

	}
	private static int doLinearSearch(int[] arr, int x) {
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] == x)
			{
				return i;
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
