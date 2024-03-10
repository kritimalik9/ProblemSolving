import java.util.Scanner;

public class IsSecondArrayMirrorInverseOfFirstArrayClass {

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
			int[] arr1 = new int[N];
			for(int i=0; i<N; i++)
			{
				System.out.println("Enter element arr["+i+"] = ");
				arr1[i] = scan.nextInt();
				if(arr1[i] < 0 || arr1[i] >= N)
				{
					System.out.println("Invalid element input to generate Inverse of this Array!");
					return;
				}
			}
			int[] arr2 = new int[N];
			for(int i=0; i<N; i++)
			{
				System.out.println("Enter element arr["+i+"] = ");
				arr2[i] = scan.nextInt();
				if(arr2[i] < 0 || arr2[i] >= N)
				{
					System.out.println("Invalid element input to generate Inverse of this Array!");
					return;
				}
			}
			System.out.println("Is second array mirror inverse of first array: ");
			int[] inversedArr1 = InverseInputArray(arr1);
			boolean IsMirrorInverse = true;
			for(int i=0; i<N; i++)
			{
				if(inversedArr1[i] != arr2[i])
				{
					IsMirrorInverse = false;
					break;
				}
			}			
			System.out.println(IsMirrorInverse);;
		}
	}

	private static int[] InverseInputArray(int[] arr) {
		int[] inversedArr = new int[arr.length];
		for(int i=0 ; i < arr.length ; i++)
		{
			inversedArr[arr[i]] = i;
		}

		return inversedArr;
	}

}
