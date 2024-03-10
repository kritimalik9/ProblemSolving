package BinaryTree;

import java.util.*;
public class ConstructTreeUsingPostOrderAndInorderClass {

	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		ConstructTreeUsingPostOrderAndInorderClass m = new ConstructTreeUsingPostOrderAndInorderClass();
		int[] post = takeInput();
		int[] in = takeInput();
		BinaryTree bt = m.new BinaryTree(post, in);
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

		public BinaryTree(int[] post, int[] in) {
			this.root = this.construct(post, 0, post.length - 1, in, 0, in.length - 1);
		}

		private Node construct(int[] post, int plo, int phi, int[] in, int ilo, int ihi) {

			Stack<Node> stack = new Stack<>();
			Set<Node> set = new HashSet<>();
			int inorderIdx = in.length-1;
			for(int postorderIdx = post.length-1; postorderIdx >= 0;)
			{
				do
				{
					Node newNode = new Node();
					newNode.data = post[postorderIdx];
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
							tempRoot.left = newNode;
						}
						else
						{
							Node tempRoot = stack.peek();
							tempRoot.right = newNode;
						}
					}
					stack.push(newNode);
				}while((post[postorderIdx--] != in[inorderIdx]) && (postorderIdx >= 0));

				Node temp = null;
				while(!stack.isEmpty() && (inorderIdx >= 0) && (stack.peek().data == in[inorderIdx]))
				{
					temp = stack.pop();
					inorderIdx--;
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
