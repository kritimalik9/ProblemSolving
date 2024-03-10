package LinkedList;

import java.util.Scanner;

public class LLMidPointClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		LinkedList list = new LinkedList();

		for(int i=0; i<size ; i++)
		{
			list.addLast(scan.nextInt());
		}

		int mid = list.findMidPoint();

		System.out.println(mid);

	}

}
