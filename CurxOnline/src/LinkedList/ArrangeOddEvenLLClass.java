package LinkedList;

import java.util.Scanner;

public class ArrangeOddEvenLLClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		LinkedList ll = new LinkedList();

		for(int i=0; i<size ; i++)
		{
			ll.addLast(scan.nextInt());
		}

		ll.arrangeOddEven();
		ll.display();


	}

}
