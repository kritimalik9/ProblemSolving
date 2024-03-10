import java.util.Scanner;

public class ComputeMaxValueInArrayClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the array : ");
		int N = scan.nextInt();
		if(N < 0)
		{
			System.out.println("Invalid size of input array");
		}
		else if(N == 0)
		{
			System.out.println("Empty array");
		}
		else
		{
			int[] arr = new int[N];
			for(int i=0; i<N; i++)
			{
				System.out.println("Enter element arr["+i+"] = ");
				arr[i] = scan.nextInt();
			}
			System.out.println(ComputeMaxValueInArray(arr));
		}
	}

	private static int ComputeMaxValueInArray(int[] arr) {
		int maxVal = arr[0];
		for(int i=1 ; i<arr.length; i++)
		{
			if(maxVal < arr[i])
			{
				maxVal = arr[i];
			}
		}
		return maxVal;
	}

}
