package Recursion;
import java.util.Scanner;

public class AllIndicesInArrayHavingGivenElementClass {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int[] arrIn  = take1DArrayInput();
		System.out.println("Enter the element to be looked out for in the array");
		int element  = scan.nextInt();
		int[] arrOut = AllIndicesInArrayHavingGivenElement(arrIn,0,0,element);
		display1DArray(arrOut);
	}
	private static int[] AllIndicesInArrayHavingGivenElement(int[] arrIn, int i, int count, int element) {
		if(i == arrIn.length)
		{
			if(count != 0)
			{
				return new int[count];
			}
			else
			{
				return null;
			}
		}
		if(element == arrIn[i])
		{
			count++;
		}
		int[] arrOut = AllIndicesInArrayHavingGivenElement(arrIn,i+1,count,element);
		if((arrOut != null) && (element == arrIn[i]))
		{
			arrOut[count-1] = i;
		}
		return arrOut;
	}
	private static void display1DArray(int[] arr) {
		if(arr != null)
		{
			for(int i = 0; i < arr.length ; i++)
			{
				System.out.print(arr[i]+" ");
			}
		}
		else
		{
			System.out.println("null");
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
