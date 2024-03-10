package LinkedList;

import java.util.ArrayList;
import java.util.Scanner;

public class MergeTwoSortedLinkedListsClass {

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(System.in);
		int numTC = scan.nextInt();
		ArrayList<LinkedList> TClist = new ArrayList<>();
		for(int i = 0 ; i< numTC ; i++)
		{
			int size1 = scan.nextInt();

			LinkedList list1 = new LinkedList();

			for(int j=0; j<size1 ; j++)
			{
				list1.addLast(scan.nextInt());
			}
			TClist.add(list1);
			int size2 = scan.nextInt();

			LinkedList list2 = new LinkedList();

			for(int j=0; j<size2 ; j++)
			{
				list2.addLast(scan.nextInt());
			}
			TClist.add(list2);
		}

		for(int i = 0 ; i< numTC*2 ; i+=2)
		{
			LinkedList list1 = TClist.get(i);
			LinkedList list2 = TClist.get(i+1);

			list1.mergeTwoSortedList(list2);
			list1.display();
			System.out.println();
		}


	}

}
