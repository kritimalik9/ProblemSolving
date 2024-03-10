package LinkedList;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteNodeFromBst {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numTCs = scan.nextInt();
		int tc[] = new int[numTCs];
		ArrayList<ArrayList<Integer>> AllBst= new ArrayList<>(); 
		ArrayList<ArrayList<Integer>> toDeleteList= new ArrayList<>(); 
		for(int i = 0 ; i < tc.length ; i++)
		{
			int bstLen = scan.nextInt();
			ArrayList<Integer> BstList = new ArrayList<>();
			for(int j = 0; j < bstLen; j++)
			{
				BstList.add(scan.nextInt());
			}
			AllBst.add(BstList);
			int NumItemsToDelete = scan.nextInt();
			ArrayList<Integer> DeleteList = new ArrayList<>();
			for(int j = 0; j < NumItemsToDelete; j++)
			{
				DeleteList.add(scan.nextInt());
			}
			toDeleteList.add(DeleteList);
		}

		for(int i = 0 ; i < tc.length ; i++)
		{
			BinarySearchTreeMain bst = new BinarySearchTreeMain();
			ArrayList<Integer> list = AllBst.get(i);
			for(Integer val : list)
			{
				bst.add(val);
			}
			ArrayList<Integer> delete = toDeleteList.get(i);
			for(Integer val : delete)
			{
				bst.deleteViaInorderPredecessor(val);
			}
			bst.displayPreOrder();

		}

	}

}
