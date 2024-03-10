package LinkedList;

import java.util.Scanner;

public class IsLLPalindrome {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		LinkedList list = new LinkedList();

		for(int i=0; i<size ; i++)
		{
			list.addLast(scan.nextInt());
		}

		boolean isPalindrome = list.isPalindrome();

		System.out.println(isPalindrome);

	}

}
