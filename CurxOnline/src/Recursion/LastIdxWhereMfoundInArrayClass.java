package Recursion;
import java.util.Scanner;
public class LastIdxWhereMfoundInArrayClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arr  = take1DArrayInput();
		System.out.println("Enter element to be looked out for");
		int element = scan.nextInt();
		System.out.println(LastIdxWhereMfoundInArray(arr,0,element));
	}
	private static int LastIdxWhereMfoundInArray(int[] arr,int index,int element) {
		if(index == arr.length)
		{
			return -1;
		}
		else
		{
			int indexLast = LastIdxWhereMfoundInArray(arr,index+1,element);
			if((indexLast == -1)
					&& (arr[index] == element))
			{
				indexLast = index; 
			}
			return indexLast;
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

