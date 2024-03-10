package Recursion;
import java.util.Scanner;

public class ReverseAnArrayViaRecursionClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr = take1DArrayInput();
		if(arr != null)
		{
			System.out.println("Reverse array becomes");
			ReverseAnArrayViaRecursion(arr,0);
			display1DArray(arr);
		}
	}
	private static void display1DArray(int[] arr) {
		for(int i = 0; i < arr.length ; i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	private static void ReverseAnArrayViaRecursion(int[] arr,int index) {
		if(index == (arr.length/2))
		{
			return;
		}
		else
		{
			swap(arr,index,arr.length-index-1);
			ReverseAnArrayViaRecursion(arr,index+1);
		}
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private static int[] take1DArrayInput() {
		System.out.println("Enter the array size");
		int N = scan.nextInt();
		if(N > 0)
		{
			int[] arr = new int[N];
			for(int i = 0 ; i < arr.length ; i++)
			{
				System.out.println("Enter element at index "+i);
				arr[i] = scan.nextInt();
				if((arr[i] < -1000000000) || (arr[i] > 1000000000))
				{
					return null;
				}
			}
			return arr;
		}
		else
		{
			return null;	
		}
	}

}
