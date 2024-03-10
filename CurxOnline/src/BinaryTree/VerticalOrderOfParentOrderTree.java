package BinaryTree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class VerticalOrderOfParentOrderTree {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int numTc = scan.nextInt();
		int treeArr[][] = new int[numTc][];
		for(int i = 0 ; i < numTc ; i++)
		{
			treeArr[i] = new int[scan.nextInt()];
			for(int j = 0; j < treeArr[i].length; j++)
			{
				treeArr[i][j] = scan.nextInt(); 
			}
		}
		Tree tree[] = new Tree[numTc];
		for(int i = 0 ; i < numTc ; i++)
		{
			tree[i] = constructTree(treeArr[i]);
			tree[i].VerticalSumMain();
		}

	}

	private static Tree constructTree(int[] treeArr) {

		Tree tree = null;
		TreeNode[] newnode = new TreeNode[treeArr.length];

		for(int j = 0; j < treeArr.length; j++)
		{
			newnode[j] = new TreeNode(j);

		}

		for(int j = 0; j < treeArr.length; j++)
		{
			if(treeArr[j] == -1)
			{
				tree = new Tree(newnode[j]);
			}
			else
			{
				if(newnode[treeArr[j]].left() == null)
				{
					newnode[treeArr[j]].setLeft(newnode[j]);
				}
				else
				{
					newnode[treeArr[j]].setRight(newnode[j]);
				}
			}
		}


		// TODO Auto-generated method stub
		return tree;
	}

}
//Class for a tree node
class TreeNode {

	// data members
	private int key;
	private TreeNode left;
	private TreeNode right;

	// Accessor methods
	public int key()        { return key; }
	public TreeNode left()  { return left; }
	public TreeNode right() { return right; }

	// Constructor
	public TreeNode(int key)
	{ this.key = key; left = null; right = null; }

	// Methods to set left and right subtrees
	public void setLeft(TreeNode left)   { this.left = left; }
	public void setRight(TreeNode right) { this.right = right; }
}

//Class for a Binary Tree
class Tree {

	private TreeNode root;

	// Constructors
	public Tree() { root = null; }
	public Tree(TreeNode n) { root = n; }

	// Method to be called by the consumer classes 
	// like Main class
	public void VerticalSumMain() { VerticalSum(root); }

	// A wrapper over VerticalSumUtil()
	private void VerticalSum(TreeNode root) {

		treeWidth width = new treeWidth();
		// base case
		if (root == null) { return; }

		// Creates an empty hashMap hM
		HashMap<Integer,List<Integer>> hM =
				new HashMap<Integer, List<Integer>>();

		// Calls the VerticalSumUtil() to store the 
		// vertical sum values in hM
		VerticalSumUtil(root, 0, hM);

		VerticalSumUtilWidthXTreesize(root, 0, hM,width);
		for(int line = width.min ; line <= width.max ;line++)
		{
			//O(TreeSize*TreeWidth
			VerticalSumUtilWidthXPrintAllNodesAtEachDistance(root,line,0,width);
			System.out.println();
		}

		//		// O(n) approach answer via VerticalSumUtil fn
		//		System.out.println(width.min +" "+width.max);
		//		//Prints the values stored by VerticalSumUtil()
		//		if (hM != null) {
		//			Set<Map.Entry<Integer, List<Integer>>> set = hM.entrySet();
		//			for(Map.Entry<Integer, List<Integer>> val : set)
		//			{
		//				System.out.println(val.getValue());
		//			}
		//		}
		//
		//		if (hM != null) {
		//			Set<Integer> set = hM.keySet();
		//			for(Integer val : set)
		//			{
		//				System.out.println(hM.get(val));
		//			}
		//		}
	}

	private void VerticalSumUtilWidthXPrintAllNodesAtEachDistance(TreeNode root, int line, int hD, treeWidth width) {

		// base case
		if (root == null) {  return; }
		if(line == hD)
		{
			System.out.print(root.key() + " ");
		}

		// Store the values in hM for left subtree
		VerticalSumUtilWidthXPrintAllNodesAtEachDistance(root.left(), line ,hD - 1, width);

		// Store the values in hM for right subtree
		VerticalSumUtilWidthXPrintAllNodesAtEachDistance(root.right(), line,hD + 1, width);

	}
	private void VerticalSumUtilWidthXTreesize(TreeNode root, int hD, HashMap<Integer, List<Integer>> hM,
			treeWidth width) {

		// base case
		if (root == null) {  return; }

		// Store the values in hM for left subtree
		VerticalSumUtilWidthXTreesize(root.left(), hD - 1, hM, width);

		if(hD < width.min)
		{
			width.min = hD;
		}

		// Store the values in hM for right subtree
		VerticalSumUtilWidthXTreesize(root.right(), hD + 1, hM, width);

		if(hD > width.max)
		{
			width.max = hD;
		}



	}

	private class treeWidth{
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
	}

	// Traverses the tree in Inoorder form and builds
	// a hashMap hM that contains the vertical sum
	private void VerticalSumUtil(TreeNode root, int hD,
			HashMap<Integer, List<Integer>> hM) {

		// base case
		if (root == null) {  return; }

		// Store the values in hM for left subtree
		VerticalSumUtil(root.left(), hD - 1, hM);

		ArrayList<Integer> list = (ArrayList<Integer>) hM.get(hD);
		if(hM.get(hD) == null)
		{
			list = new ArrayList<>();
			list.add(root.key());
			hM.put(hD,list);
		}
		else
		{
			list.add(root.key());
		}

		// Store the values in hM for right subtree
		VerticalSumUtil(root.right(), hD + 1, hM);
	}
}
