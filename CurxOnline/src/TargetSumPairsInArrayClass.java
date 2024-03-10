import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TargetSumPairsInArrayClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the array : ");
		int N = scan.nextInt();
		if(N <= 0 || N > 1000)
		{
			System.out.println("Invalid size of input array");
		}
		else
		{
			int[] arr1 = new int[N];
			for(int i=0; i<N; i++)
			{
				System.out.println("Enter element arr["+i+"] = ");
				arr1[i] = scan.nextInt();
			}
			System.out.println("Enter target sum to get have for any of the 2 array elements pair");
			int target = scan.nextInt();
			System.out.println("All array elements pairs making to target sum: ");
			TargetSumPairsInArray(arr1,target);
		}
	}

	private static void TargetSumPairsInArray(int[] arr1, int target) {
		for(int i = 0 ; i < arr1.length-1 ; i++)
		{
			for(int j = (i+1) ; j < arr1.length ; j++ )
			{
				if((arr1[i]+arr1[j]) == target)
				{
					System.out.println(arr1[i]+" and "+arr1[j]);
				}
			}
		}

	}
}
