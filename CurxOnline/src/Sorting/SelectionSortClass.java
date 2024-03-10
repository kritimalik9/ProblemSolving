package Sorting;

import java.util.Scanner;

public class SelectionSortClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		System.out.println("Sorted array becomes");
		doSelectionSort(arr);
		display1DArray(arr);
	}
	private static void display1DArray(int[] arr) {
		for(int i = 0; i < arr.length ; i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	private static void doSelectionSort(int[] arr) {
		int min_idx = 0;
		for(int i = 0; i < (arr.length-1) ; i++)
		{
			min_idx = i;
			for(int j = i+1; j < arr.length ; j++)
			{
				if(arr[j] < arr[min_idx])
				{
					min_idx = j;
				}
			}
			swap(arr,i,min_idx);
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
