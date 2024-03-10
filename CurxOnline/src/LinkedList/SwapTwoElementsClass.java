package LinkedList;

import java.util.LinkedList;
import java.util.Scanner;

public class SwapTwoElementsClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		LinkedList<Integer> list = new LinkedList<>();

		for(int i=0; i<size ; i++)
		{
			list.add(scan.nextInt());
		}

		int data1 = scan.nextInt();
		int data2 = scan.nextInt();

		int nodeIdx1 = -1;
		int nodeIdx2 = -1;

		for(int i=0; i<list.size() ; i++)
		{
			if(list.get(i) == data1)
			{
				nodeIdx1 = i;
			}
			if(list.get(i) == data2)
			{
				nodeIdx2 = i;
			}
		}

		if((nodeIdx1 != -1) &&  (nodeIdx2 != -1))
		{
			int temp = list.get(nodeIdx1);
			int temp1 = list.get(nodeIdx2);
			list.set(nodeIdx1, temp1);
			list.set(nodeIdx2, temp);
		}

		for(int val : list)
		{
			System.out.print(val+" ");
		}
	}

}
