package Recursion;
import java.util.Scanner;

public class CheckIfPalindromeViaRecursionClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr  = take1DArrayInput();
		System.out.println(CheckIfPalindromeViaRecursion(arr,0));
	}
	private static boolean CheckIfPalindromeViaRecursion(int[] arr,int index) {
		if(index == (arr.length/2))
		{
			return true;
		}
		else
		{
			return ((arr[index] == arr[arr.length-1-index]) && CheckIfPalindromeViaRecursion(arr,index+1));
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


