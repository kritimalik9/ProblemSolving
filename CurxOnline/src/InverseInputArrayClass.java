import java.util.Scanner;

public class InverseInputArrayClass {

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
				if(arr[i] < 0 || arr[i] >= N)
				{
					System.out.println("Invalid element input to generate Inverse of this Array!");
					return;
				}
			}
			System.out.println("Inverse of this array : ");
			int[] inversedArr = InverseInputArray(arr);
			for(int i=0; i<N; i++)
			{
				System.out.println(inversedArr[i]);
			}			
		}
	}

	private static int[] InverseInputArray(int[] arr) {

		int tempVal = 0;
		int[] inversedArr = new int[arr.length];
		for(int i=0 ; i < arr.length ; i++)
		{
			inversedArr[arr[i]] = i;
		}

		return inversedArr;
	}

}
