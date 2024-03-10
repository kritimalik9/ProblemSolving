import java.util.Scanner;

public class ReverseInputArrayClass {

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
			System.out.println("Reverse of this array : ");
			ReverseInputArray(arr);
			for(int i=0; i<N; i++)
			{
				System.out.println(arr[i]);
			}			
		}
	}

	private static void ReverseInputArray(int[] arr) {

		int tempVal = 0;
		for(int i=0 ; i < arr.length/2 ; i++)
		{
			tempVal = arr[i];
			arr[i]  = arr[arr.length-1-i];
			arr[arr.length-1-i] = tempVal;
		}

		return;
	}

}
