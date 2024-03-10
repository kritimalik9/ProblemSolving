package BinaryTree;

import java.util.*;
public class ConstructTreeUsingPreOrderAndInorderClass {

	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		ConstructTreeUsingPreOrderAndInorderClass m = new ConstructTreeUsingPreOrderAndInorderClass();
		int[] pre = takeInput();
		int[] in = takeInput();
		BinaryTree bt = m.new BinaryTree(pre, in);
		bt.display();
	}

	public static int[] takeInput() {
		int n = scn.nextInt();

		int[] rv = new int[n];
		for (int i = 0; i < rv.length; i++) {
			rv[i] = scn.nextInt();
		}

		return rv;
	}

	private class BinaryTree {
		private class Node {
			int data;
			Node left;
			Node right;
		}

		private Node root;
		private int size;

		public BinaryTree(int[] pre, int[] in) {
			this.root = this.construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
		}

		private Node construct(int[] pre, int plo, int phi, int[] in, int ilo, int ihi) {

			Stack<Node> stack = new Stack<>();
			Set<Node> set = new HashSet<>();
			int inorderIdx = 0;
			for(int preorderIdx = 0; preorderIdx < pre.length;)
			{
				do
				{
					Node newNode = new Node();
					newNode.data = pre[preorderIdx];
					newNode.left = null;
					newNode.right = null;
					if(this.root == null)
					{
						this.root = newNode;
					}
					else
					{
						if(set.contains(stack.peek()))
						{
							Node tempRoot = stack.pop();
							set.remove(tempRoot);
							tempRoot.right = newNode;
						}
						else
						{
							Node tempRoot = stack.peek();
							tempRoot.left = newNode;
						}
					}
					stack.push(newNode);
				}while((pre[preorderIdx++] != in[inorderIdx]) && (preorderIdx < pre.length));

				Node temp = null;
				while(!stack.isEmpty() && (inorderIdx < in.length) && (stack.peek().data == in[inorderIdx]))
				{
					temp = stack.pop();
					inorderIdx++;
				}

				if(temp != null)
				{
					stack.push(temp);
					set.add(temp);
				}

			}

			return this.root;

		}

		public void display() {
			this.display(this.root);
		}

		private void display(Node node) {
			if (node == null) {
				return;
			}

			String str = "";

			if (node.left != null) {
				str += node.left.data;
			} else {
				str += "END";
			}

			str += " => " + node.data + " <= ";

			if (node.right != null) {
				str += node.right.data;
			} else {
				str += "END";
			}

			System.out.println(str);

			this.display(node.left);
			this.display(node.right);
		}

	}

}
