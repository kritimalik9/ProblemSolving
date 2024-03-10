import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TargetSumTripletsInArrayClass {

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
			System.out.println("Enter target sum to get for any of the 3 array elements pair");
			int target = scan.nextInt();
			System.out.println("All array elements pairs making to target sum: ");
			TargetSumTripletsInArray(arr1,target);
		}
	}

	private static void TargetSumTripletsInArray(int[] arr1, int target) {
		ArrayList<Integer> InnerList = new ArrayList<>();
		for(int i = 0 ; i < arr1.length-2 ; i++)
		{
			for(int j = (i+1) ; j < arr1.length-1 ; j++ )
			{
				for(int k = (j+1) ; k < arr1.length ; k++)
				{
					if((arr1[i]+arr1[j]+arr1[k]) == target)
					{
						InnerList.add(arr1[i]);
						InnerList.add(arr1[j]);
						InnerList.add(arr1[k]);
						Collections.sort(InnerList);
						System.out.println(InnerList.get(0)+", "+InnerList.get(1)+" and "+InnerList.get(2));
						InnerList.clear();
					}
				}
			}
		}

	}
}
