package Recursion;
import java.util.Scanner;
public class FirstIdxWhereMfoundInArrayClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr  = take1DArrayInput();
		System.out.println("Enter element to be looked out for");
		int element = scan.nextInt();
		System.out.println(FirstIdxWhereMfoundInArray(arr,0,element));
	}
	private static int FirstIdxWhereMfoundInArray(int[] arr,int index,int element) {
		if(index == arr.length)
		{
			return -1;
		}
		else
		{
			int indexFirst;
			if(arr[index] == element)
			{
				indexFirst = index; 
			}
			else
			{
				indexFirst = FirstIdxWhereMfoundInArray(arr,index+1,element);
			}
			return indexFirst;
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

