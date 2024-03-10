package BinaryTree;

import java.util.Scanner;

public class FindSumOfBalancedBinaryTree {

	private class Node{
		int data;
		Node left;
		Node right;

		public Node(int data)
		{
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	private class BinaryTree{
		Node root;

		public void add(int[] arr){
			int left = 0;
			int right = arr.length-1;
			this.root = this.add(this.root,arr,left,right);
		}

		private Node add(Node node, int[] arr,int left,int right) {

			if(left > right)
			{
				return null;

			}
			int mid = left + (right-left)/2;

			node = new Node(arr[mid]);

			node.left = add(node.left,arr,left,mid-1);

			node.right = add(node.right,arr,mid+1,right);
			return node;

		}

		public void display()
		{
			this.display(this.root);
		}

		private void display(Node node) {

			if(node == null)
			{
				return;
			}
			System.out.println(node.data);
			display(node.left);
			display(node.right);

		}
		public int findSumOfAllNodes()
		{
			return this.findSumOfAllNodes(this.root);
		}

		private int findSumOfAllNodes(Node node) {

			if(node == null)
			{
				return 0;
			}
			return node.data + findSumOfAllNodes(node.left) + findSumOfAllNodes(node.right);

		}
	}
	public static void main(String[] args) {

		FindSumOfBalancedBinaryTree findSum = new FindSumOfBalancedBinaryTree();
		BinaryTree bt = findSum.new BinaryTree();
		Scanner scan = new Scanner(System.in);
		int arr[] = new int[scan.nextInt()];
		for(int i = 0 ; i < arr.length ; i++)
		{
			arr[i] = scan.nextInt();
		}
		bt.add(arr);
		int sum = bt.findSumOfAllNodes();
		System.out.println(sum);
		//bt.display();

	}

}
