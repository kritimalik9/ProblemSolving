package Recursion;
import java.util.Scanner;

public class SelectionSortViaRecursionClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		//System.out.println("Sorted array becomes");
		SelectionSortViaRecursion(arr,0,1,0);
		display1DArray(arr);
	}
	private static void display1DArray(int[] arr) {
		for(int i = 0; i < arr.length ; i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	private static void SelectionSortViaRecursion(int[] arr,int i,int j,int minIdx) {
		if(i == (arr.length-1))
		{
			return;
		}
		if(j < arr.length)
		{
			if(arr[j] < arr[minIdx])
			{
				minIdx = j;
			}
			j++;
		}
		else
		{
			swap(arr,i,minIdx);
			i++;
			j=i+1;
			minIdx = i;
		}
		SelectionSortViaRecursion(arr,i,j,minIdx);
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private static int[] take1DArrayInput() {
		//System.out.println("Enter the array size");
		int[] arr = new int[scan.nextInt()];
		for(int i = 0 ; i < arr.length ; i++)
		{
			//System.out.println("Enter element at index "+i);
			arr[i] = scan.nextInt();
		}
		return arr;
	}

}
