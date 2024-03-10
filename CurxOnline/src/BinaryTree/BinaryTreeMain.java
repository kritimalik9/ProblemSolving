package BinaryTree;

import java.util.*;
public class BinaryTreeMain {

	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		BinaryTreeMain m = new BinaryTreeMain();
		BinaryTree bt = m.new BinaryTree();
		//		System.out.println(bt.sumOfNodes());
		//		int data1 = scn.nextInt();
		//		int data2 = scn.nextInt();
		//		int lca = bt.LCA(data1,data2);
		//		if(lca != -1)
		//		{
		//			System.out.println(lca);
		//		}
		//
		//		int target = scn.nextInt();
		//		bt.rootToLeafPaths(target);

		//		BinaryTree bt_other = m.new BinaryTree();
		//		boolean isStructurallyIdentical = bt.isStructureIdentical(bt_other);
		//		System.out.println(isStructurallyIdentical);

		//		boolean isBalanced = bt.isBalancedTree();
		//		System.out.println(isBalanced);

		//		bt.getLevelsOfArrayList();

		//		bt.printAllNodesWithNoSibling();


		//		bt.removeLeaves();
		//		bt.display();

		//		bt.levelOrderView();
	}

	private class BinaryTree {
		private class Node {
			int data;
			Node left;
			Node right;
		}

		private Node root;
		private int size;

		public BinaryTree() {
			this.root = this.takeInput(null, false);
		}


		public void levelOrderView() {
			this.levelOrderView(this.root);			
		}


		private void levelOrderView(Node node) {
			Queue<Node> que = new LinkedList<>();
			que.add(node);
			Node separatorNode = new Node();
			separatorNode.data = Integer.MAX_VALUE;
			separatorNode.left = null;
			separatorNode.right = null;
			que.add(separatorNode);
			while(!que.isEmpty())
			{
				while(que.peek() != separatorNode)
				{
					Node curr = que.poll();
					System.out.print(curr.data+" ");
					if(curr.left != null)
					{
						que.add(curr.left);
					}
					if(curr.right != null)
					{
						que.add(curr.right);
					}
				}
				System.out.println();
				separatorNode = que.poll();
				if(!que.isEmpty())
				{
					que.add(separatorNode);
				}
			}

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


		public void removeLeaves() {
			ArrayList<Node> LeftLeafNodeParentList = new ArrayList<>();
			ArrayList<Node> RightLeafNodeParentList = new ArrayList<>();
			this.removeLeaves(LeftLeafNodeParentList,RightLeafNodeParentList,this.root,null);
			Node curr = null;
			for(int i=0; i< LeftLeafNodeParentList.size();i++)
			{
				curr = LeftLeafNodeParentList.get(i);
				curr.left = null;
			}
			for(int i=0; i< RightLeafNodeParentList.size();i++)
			{
				curr = RightLeafNodeParentList.get(i);
				curr.right = null;			
			}
		}

		private void removeLeaves(ArrayList<Node> LeftLeafNodeParentList,ArrayList<Node> RightLeafNodeParentList,Node node, Node parent) {

			if(node != null)
			{
				removeLeaves(LeftLeafNodeParentList,RightLeafNodeParentList,node.left,node);
				removeLeaves(LeftLeafNodeParentList,RightLeafNodeParentList,node.right,node);
				if((node.left == null && node.right == null))
				{
					if(parent.right == node)
					{
						RightLeafNodeParentList.add(parent);					
					}
					else
					{
						LeftLeafNodeParentList.add(parent);
					}

				}
			}
		}

		public void printAllNodesWithNoSibling() {
			this.printAllNodesWithNoSibling(this.root);			
		}

		private void printAllNodesWithNoSibling(Node node) {

			Queue<Node> que = new LinkedList<>();
			que.add(node);
			while(!que.isEmpty())
			{
				Node curr = que.poll();
				if((curr.left == null) && (curr.right != null))
				{
					System.out.print(curr.right.data+" ");
				}
				else if((curr.left != null) && (curr.right == null))
				{
					System.out.print(curr.left.data+" ");
				}
				if(curr.left != null)
				{
					que.add(curr.left);
				}
				if(curr.right != null)
				{
					que.add(curr.right);
				}
			}

		}

		public void getLevelsOfArrayList() {

			ArrayList<Integer> List = new ArrayList<>();
			ArrayList<List> OuterList = new ArrayList<>();

			this.getLevelsOfArrayList(OuterList,this.root);

			System.out.println(OuterList);
		}

		private void getLevelsOfArrayList(ArrayList<List> outerList, Node node) {

			Queue<Node> que1 = new LinkedList<>();
			Queue<Node> que2 = new LinkedList<>();
			que1.add(node);
			while(!que1.isEmpty() || !que2.isEmpty())
			{
				ArrayList<Integer> inner = new ArrayList<>();
				if(!que1.isEmpty())
				{
					while(!que1.isEmpty())
					{
						Node curr = que1.poll();
						inner.add(curr.data);
						if(curr.left != null)
						{
							que2.add(curr.left);
						}
						if(curr.right != null)
						{
							que2.add(curr.right);
						}						
					}
				}
				else
				{
					while(!que2.isEmpty())
					{
						Node curr = que2.poll();
						inner.add(curr.data);
						if(curr.left != null)
						{
							que1.add(curr.left);
						}
						if(curr.right != null)
						{
							que1.add(curr.right);
						}						
					}
				}
				outerList.add(inner);
			}

		}

		public boolean isBalancedTree() {
			int ht_diff = this.isBalancedTree(this.root);
			if(ht_diff == Integer.MAX_VALUE)
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		private int isBalancedTree(Node node) {
			if(node == null)
			{
				return -1;
			}
			int ht_diff = Integer.MAX_VALUE;
			int ht_left = isBalancedTree(node.left);
			if(ht_left != Integer.MAX_VALUE)
			{
				ht_left += 1;
			}
			int ht_right = isBalancedTree(node.right);
			if(ht_right != Integer.MAX_VALUE)
			{
				ht_right += 1;
			}
			if((ht_left!= Integer.MAX_VALUE) && (ht_right != Integer.MAX_VALUE))
			{
				ht_diff = Math.abs(ht_right-ht_left);
				if(ht_diff > 1)
				{
					return Integer.MAX_VALUE;
				}
				else
				{
					return Math.max(ht_left, ht_right);
				}
			}
			return ht_diff;
		}

		public boolean isStructureIdentical(BinaryTree bt_other) {

			return this.isStructureIdentical(this.root,bt_other.root);			
		}

		private boolean isStructureIdentical(Node thisTreeNode, Node otherTreeNode) {
			if((thisTreeNode == null) && (otherTreeNode == null))
			{
				return true;
			}
			else if(((thisTreeNode != null) && (otherTreeNode == null))
					|| ((thisTreeNode == null) && (otherTreeNode != null)))
			{
				return false;
			}
			else
			{
				return (isStructureIdentical(thisTreeNode.left,otherTreeNode.left)
						&& isStructureIdentical(thisTreeNode.right,otherTreeNode.right)
						);
			}
		}

		public void rootToLeafPaths(int target) {
			ArrayList<Integer> list = new ArrayList<>();
			rootToLeafPaths(list,0,this.root,target);
		}

		private void rootToLeafPaths(ArrayList<Integer> list, int sum, Node node,int target) {

			if(node != null)
			{
				sum = sum + node.data;
				list.add(node.data);
				if((node.left == null) && (node.right == null))
				{
					if(target == sum)
					{
						for(Integer val : list)
						{
							System.out.print(val+" ");
						}
						System.out.println();
					}
					list.remove(list.size()-1);
					return;
				}

				if(node.left != null)
				{
					rootToLeafPaths(list,sum,node.left,target);
				}
				if(node.right != null)
				{
					rootToLeafPaths(list,sum,node.right,target);
				}
				list.remove(list.size()-1);

			}
		}

		public Node takeInput(Node parent, boolean ilc) {

			int cdata = scn.nextInt();
			Node child = new Node();
			child.data = cdata;
			this.size++;

			// left
			boolean hlc = scn.nextBoolean();

			if (hlc) {
				child.left = this.takeInput(child, true);
			}

			// right
			boolean hrc = scn.nextBoolean();

			if (hrc) {
				child.right = this.takeInput(child, false);
			}

			// return
			return child;
		}

		public int sumOfNodes() {
			return this.sumOfNodes(this.root) ;
		}

		private int sumOfNodes(Node node) {

			if(node == null)
			{
				return 0;
			}
			int sum = node.data;

			sum += sumOfNodes(node.left);
			sum += sumOfNodes(node.right);
			return sum;
		}

		public int LCA(int data1, int data2)
		{
			return this.LCA(this.root,null,data1,data2);
		}

		boolean found = false;
		private int LCA(Node node, Node parent, int data1, int data2) {
			if(node == null)
			{
				return -1;
			}
			int lca = -1;
			int lca_left  = LCA(node.left,node,data1,data2);
			int lca_right = LCA(node.right,node,data1,data2);

			if((lca_left != -1) && (lca_right != -1) && !found)
			{
				lca = node.data;
				found = true;
				return lca;
			}
			else if(!found)
			{

				if(node.data == data1 || node.data == data2)
				{
					if((lca_left != -1) || (lca_right != -1))
					{
						found = true;
						return node.data;
					}
					else if(parent != null)
					{
						return parent.data;
					}
				}
				else if(parent == null && !found)
				{
					return -1;
				}
				else if(lca_left != -1)
				{
					return lca_left;
				}
				else if(lca_right != -1)
				{
					return lca_right;
				}
			}
			else if(lca_left != -1)
			{
				return lca_left;
			}
			else if(lca_right != -1)
			{
				return lca_right;
			}

			return lca;
		}
	}
}
