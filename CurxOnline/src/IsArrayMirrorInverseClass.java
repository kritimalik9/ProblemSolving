import java.util.Scanner;

public class IsArrayMirrorInverseClass {

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
			System.out.println("Is this array mirror inverse : ");
			int[] inversedArr = InverseInputArray(arr);
			boolean IsMirrorInverse = true;
			for(int i=0; i<N; i++)
			{
				if(inversedArr[i] != arr[i])
				{
					IsMirrorInverse = false;
					break;
				}
			}			
			System.out.println(IsMirrorInverse);;
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
