package Recursion;

import java.util.Scanner;

public class BubbleSortViaRecursionClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		System.out.println("Sorted array becomes");
		BubbleSortViaRecursion(arr,0,0);
		display1DArray(arr);
	}
	private static void display1DArray(int[] arr) {
		for(int i = 0; i < arr.length ; i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	private static void BubbleSortViaRecursion(int[] arr,int i,int j) {
		if(i == (arr.length-1))
		{
			return;
		}
		if(j < (arr.length-i-1))
		{
			if(arr[j] > arr[j+1])
			{
				swap(arr,j,j+1);
			}
			j++;
		}
		else
		{
			i++;
			j=0;
		}
		BubbleSortViaRecursion(arr,i,j);
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
