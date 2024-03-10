package Recursion;
import java.util.Scanner;

public class CheckIsArraySortedUsingRecursionClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr  = take1DArrayInput();
		System.out.println("Is this array sorted");
		System.out.println(CheckIsArraySortedUsingRecursion(arr,0));
	}
	private static boolean CheckIsArraySortedUsingRecursion(int[] arr,int index) {
		if(index == arr.length-1)
		{
			return true;
		}
		else
		{
			return ((arr[index] < arr[index+1]) && CheckIsArraySortedUsingRecursion(arr,index+1));
		}
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

