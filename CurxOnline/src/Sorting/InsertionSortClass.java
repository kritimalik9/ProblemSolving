package Sorting;

import java.util.Scanner;

public class InsertionSortClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		System.out.println("Sorted array becomes");
		doInsertionSort(arr);
		display1DArray(arr);
	}
	private static void display1DArray(int[] arr) {
		for(int i = 0; i < arr.length ; i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	private static void doInsertionSort(int[] arr) {
		for(int i = 1; i < arr.length ; i++)
		{
			for(int j = 0; j < i ; j++)
			{
				if(arr[i] < arr[j])
				{
					swap(arr,i,j);
				}
			}
		}
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
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
