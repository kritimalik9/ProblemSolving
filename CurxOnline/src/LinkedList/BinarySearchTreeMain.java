package LinkedList;

import java.util.*;
public class BinarySearchTreeMain {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;
	private int size;

	public BinarySearchTreeMain() {
		this.root = null;
		this.size = 0;
	}

	public void add(int data) {
		if (this.isEmpty()) {
			this.root = new Node(data, null, null);
			this.size++;
		} else {
			this.add(this.root, data);
		}
	}

	private void add(Node node, int data) {
		if (data > node.data) {
			if (node.right != null) {
				this.add(node.right, data);
			} else {
				this.size++;
				node.right = new Node(data, null, null);
			}
		} else if (data < node.data) {
			if (node.left != null) {
				this.add(node.left, data);
			} else {
				this.size++;
				node.left = new Node(data, null, null);
			}
		} else {
			// nothing to do
		}
	}

	public void remove(int data) {
		this.root = this.remove(this.root, data);
	}

	private Node remove(Node node, int data) {
		if (node == null) {
			return null;
		}

		if (data > node.data) {
			node.right = this.remove(node.right, data);
			return node;
		} else if (data < node.data) {
			node.left = this.remove(node.left, data);
			return node;
		} else {
			// found the element
			if (node.left == null && node.right == null) {
				this.size--;
				return null;
			} else if (node.left == null) {
				this.size--;
				return node.right;
			} else if (node.right == null) {
				this.size--;
				return node.left;
			} else {
				// both children
				int lmax = this.max(node.left);
				node.data = lmax;
				node.left = this.remove(node.left, lmax);

				return node;
			}
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void display() {
		System.out.println(this);
	}

	public int max() {
		return this.max(this.root);
	}

	private int max(Node node) {
		int rv = node.data;

		if (node.right != null) {
			rv = this.max(node.right);
		}

		return rv;
	}

	@Override
	public String toString() {
		return this.toString(this.root);
	}

	private String toString(Node node) {
		if (node == null) {
			return "";
		}

		String retVal = "";

		if (node.left != null) {
			retVal += node.left.data + " => ";
		} else {
			retVal += "END" + " => ";
		}

		retVal += node.data;

		if (node.right != null) {
			retVal += " <= " + node.right.data;
		} else {
			retVal += " <= " + "END";
		}

		retVal += "\n";

		retVal += this.toString(node.left);
		retVal += this.toString(node.right);

		return retVal;
	}
	public int lca(int d1, int d2) {

		return lca(this.root,d1,d2);
	}
	boolean found = false;
	private int lca(Node node, int d1, int d2) {

		if(node == null)
		{
			return -1;
		}

		int lca_left  = -1;
		int lca_right = -1;
		int lca  = -1;

		if(!found)
		{

			if((d1 > node.data) && (d2 > node.data))
			{
				lca_right = lca(node.right,d1,d2);
			}
			else if((d1 < node.data) && (d2 < node.data))
			{
				lca_left = lca(node.left,d1,d2);
			}
			else if(((d1 > node.data) && (d2 < node.data))
					|| ((d1 < node.data) && (d2 > node.data)))
			{
				if((d1 > node.data) && (d2 < node.data))
				{
					lca_left = lca(node.left,-1,d2);
					lca_right = lca(node.right,d1,-1);
				}
				else
				{
					lca_left = lca(node.left,d1,-1);
					lca_right = lca(node.right,-1,d2);
				}
			}
			else if((d1 == node.data) || (d2 == node.data))
			{
				if((d1 == node.data) && (d2 == node.data))
				{
					return d1;
				}
				else if(d1 == node.data)
				{
					if(d2 != -1)
					{
						if(d2 < node.data)
						{
							lca_right = d1;
							lca_left = lca(node.left,-1,d2);
						}
						else
						{
							lca_left = d1;
							lca_right = lca(node.right,-1,d2);
						}
					}
					else
					{
						lca = d1;
						return lca;
					}
				}
				else
				{
					if(d1 != -1)
					{
						if(d1 < node.data)
						{
							lca_right = d2;
							lca_left = lca(node.left,d1,-1);
						}
						else
						{
							lca_left = d2;
							lca_right = lca(node.right,d1,-1);
						}
					}
					else
					{
						lca = d2;
						return lca;
					}
				}
			}
		}

		if(!found && (lca_left != -1) && (lca_right != -1))
		{
			found = true;
			lca =  node.data;
		}
		else
		{
			if(lca_left != -1)
			{
				lca = lca_left;
			}
			else
			{
				lca = lca_right;
			}
		}
		return lca;
	}

	public void replaceWLS() {

		addAllGreaterValuesToThisNode(this.root,false,0);

	}

	private int addAllGreaterValuesToThisNode(Node node,boolean isLeft,int leftSum) {

		int sum = 0;

		int thisNodeData= node.data;

		if(node.right != null)
		{
			sum += addAllGreaterValuesToThisNode(node.right,false,leftSum);
			node.data = sum;
			sum = sum + thisNodeData;
		}
		else
		{
			sum = node.data+leftSum;
			node.data = leftSum;
		}

		if(node.left != null)
		{
			sum = addAllGreaterValuesToThisNode(node.left,true,sum);
		}

		return sum;

	}

	private class DataRange{
		int k1;
		int k2;
		public DataRange(int k1,int k2)
		{
			this.k1 = k1;
			this.k2 = k2;
		}
	}
	public static void main(String[] args) {
		//		BinarySearchTreeMain b1= new BinarySearchTreeMain();
		//		Scanner scn = new Scanner(System.in);
		//		int n= scn.nextInt();
		//		while(n!=0){
		//			int m=scn.nextInt();
		//			b1.add(m);
		//			--n;
		//		}
		//		int d1= scn.nextInt();
		//		int d2= scn.nextInt();
		//		System.out.println(b1.lca(d1, d2));

		//		b1.replaceWLS();
		//		b1.display();
		//		

		//		b1.addDuplicates();
		//		b1.display();

		//		int itemTOdelete = scn.nextInt();
		//		b1.delete(itemTOdelete);
		//		b1.display();

		//		/****************************************************************/
		//		Scanner scan = new Scanner(System.in);
		//		int numTCs = scan.nextInt();
		//		int tc[] = new int[numTCs];
		//		ArrayList<ArrayList<Integer>> AllBst= new ArrayList<>(); 
		//		ArrayList<ArrayList<Integer>> toDeleteList= new ArrayList<>(); 
		//		for(int i = 0 ; i < tc.length ; i++)
		//		{
		//			int bstLen = scan.nextInt();
		//			ArrayList<Integer> BstList = new ArrayList<>();
		//			for(int j = 0; j < bstLen; j++)
		//			{
		//				BstList.add(scan.nextInt());
		//			}
		//			AllBst.add(BstList);
		//			int NumItemsToDelete = scan.nextInt();
		//			ArrayList<Integer> DeleteList = new ArrayList<>();
		//			for(int j = 0; j < NumItemsToDelete; j++)
		//			{
		//				DeleteList.add(scan.nextInt());
		//			}
		//			toDeleteList.add(DeleteList);
		//		}
		//
		//		for(int i = 0 ; i < tc.length ; i++)
		//		{
		//			BinarySearchTreeMain bst = new BinarySearchTreeMain();
		//			ArrayList<Integer> list = AllBst.get(i);
		//			for(Integer val : list)
		//			{
		//				bst.add(val);
		//			}
		//			ArrayList<Integer> delete = toDeleteList.get(i);
		//			for(Integer val : delete)
		//			{
		//				bst.deleteViaInorderPredecessor(val);
		//			}
		//			bst.displayPreOrder();
		//
		//		}
		//		/**********************************************************************/

		//		/*******************************************************************************/
		//		Scanner scan = new Scanner(System.in);
		//		int numTc = scan.nextInt();
		//		int tc[][] = new int[numTc][];
		//		BinarySearchTreeMain[] bst = new BinarySearchTreeMain[numTc];
		//		DataRange[] range = new DataRange[numTc];
		//		for(int i = 0 ; i < numTc ; i++)
		//		{
		//			tc[i] = new int[scan.nextInt()];
		//			for(int j = 0; j < tc[i].length ; j++)
		//			{
		//				tc[i][j] = scan.nextInt();
		//			}
		//			bst[i] = new BinarySearchTreeMain();
		//			range[i] = bst[i].new DataRange(scan.nextInt(),scan.nextInt());
		//		}
		//		for(int i = 0 ; i < numTc ; i++)
		//		{
		//			for(int j = 0; j < tc[i].length ; j++)
		//			{
		//				bst[i].add(tc[i][j]);
		//			}
		//			System.out.print("# Preorder : ");
		//			bst[i].displayPreOrder();
		//			System.out.println();
		//			System.out.print("# Nodes within range are : ");
		//			bst[i].getItemsWithinRangeInclusive(range[i]);
		//			System.out.println();
		//		}
		//
		//		/***********************************************************************************************/

		BinarySearchTreeMain b1= new BinarySearchTreeMain();
		Scanner scn = new Scanner(System.in);
		int n= scn.nextInt();
		while(n!=0){
			int m=scn.nextInt();
			b1.add(m);
			--n;
		}
		int targetNodeData= scn.nextInt();
		int k = scn.nextInt();

		b1.getItemsAtDistanceK(targetNodeData,k);

	}

	private void getItemsAtDistanceK(int targetNodeData, int k) {

		this.getItemsAtDistanceK(this.root,null,targetNodeData, k,0,false);

	}

	private int getItemsAtDistanceK(Node node, Node parent,int targetNodeData, int k,int atEachNextStep,boolean isfound) {

		int atEachStep = 0;
		if (node == null)
		{
			return 0;
		}
		if(isfound && (atEachNextStep == k) && (node != null))
		{
			System.out.print(node.data+" ");
			return 0;
		}

		if(!isfound)
		{
			boolean isleft = false;
			if(node.data > targetNodeData)
			{
				atEachStep = this.getItemsAtDistanceK(node.left,node,targetNodeData,k, atEachNextStep,false);
				isleft = true;
			}
			else if(node.data < targetNodeData)
			{
				atEachStep = this.getItemsAtDistanceK(node.right,node,targetNodeData,k,atEachNextStep,false);
			}
			else
			{
				isfound = true;
			}
			if((atEachStep!= 0) && (atEachStep == k) && (node != null))
			{
				if((atEachStep == k) && (node != null))
				{
					System.out.print(node.data+" ");
					return 0;
				}
			}
			if(atEachStep != 0)
			{
				atEachNextStep = atEachStep;
				if(isleft)
				{
					atEachStep = this.getItemsAtDistanceK(node.right,node,targetNodeData, k,atEachStep+1,true);
				}
				else
				{
					atEachStep = this.getItemsAtDistanceK(node.left,node,targetNodeData,k, atEachStep+1,true);
				}
				return ++atEachNextStep;
			}
		}
		if(isfound == true)
		{
			// go node left child
			// go node right child
			// go node's parent and above
			// go node's parent and then it's children
			// atEachNextStep here works for 0 plus till k is reached through either left or right child of target node
			atEachStep = getItemsAtDistanceK(node.left,node,targetNodeData, k,atEachNextStep+1,true);
			atEachStep = getItemsAtDistanceK(node.right,node,targetNodeData, k,atEachNextStep+1,true);
			return 1;// This ensures that start tracking from this point node's parent and node's parent children till the distance of k
		}
		return 0;
	}

	private void getItemsWithinRangeInclusive(DataRange dataRange) {

		this.getItemsWithinRangeInclusive(this.root,dataRange);

	}

	private void getItemsWithinRangeInclusive(Node node, DataRange dataRange) {

		if(node == null)
		{
			return;
		}
		if(node.data > dataRange.k1)
		{
			getItemsWithinRangeInclusive(node.left,dataRange);
		}

		if((node.data >= dataRange.k1) && (node.data <= dataRange.k2))
		{
			System.out.print(node.data+" ");

			getItemsWithinRangeInclusive(node.right,dataRange);

		}

		//		if (node == null)
		//			return;
		//		if (node.data >= dataRange.k1 && node.data <= dataRange.k2)
		//			System.out.printf("%d ", node.data);
		//		if (node.data > dataRange.k1)
		//			getItemsWithinRangeInclusive(node.left, dataRange);
		//		if (node.data < dataRange.k2)
		//			getItemsWithinRangeInclusive(node.right, dataRange);
	}

	public void addDuplicates() {
		this.addDuplicates(this.root);		
	}

	private void addDuplicates(Node node) {

		if(node != null)
		{
			Node newLeft = new Node(node.data,null,null);
			Node oldLeft = node.left;
			node.left = newLeft;
			newLeft.left = oldLeft;
			addDuplicates(node.left.left);
			addDuplicates(node.right);
		}		
	}

	public void delete(int item)
	{
		this.delete(this.root,item,null);
	}

	public void deleteViaInorderPredecessor(int item)
	{
		this.deleteViaInorderPredecessor(this.root,item,null);
	}


	private void delete(Node node, int item,Node parent) {
		// TODO Auto-generated method stub
		if(node == null)
		{
			return;
		}

		if(item < node.data)
		{
			delete(node.left,item,node);
		}
		else if(item > node.data)
		{
			delete(node.right,item,node);
		}
		else
		{
			if((node.left == null) && (node.right == null))	
			{
				if(parent == null)
				{
					this.root = null;
				}
				else
				{
					if(parent.left == node)
					{
						parent.left = null;
					}
					else
					{
						parent.right = null;
					}
				}
			}
			else if(node.left == null)
			{
				if(parent.left == node)
				{
					parent.left = node.right;
				}
				else
				{
					parent.right = node.right;
				}
			}
			else if(node.right == null)
			{
				if(parent.left == node)
				{
					parent.left = node.left;
				}
				else
				{
					parent.right = node.left;
				}
			}
			else
			{
				// inorder successor
				Node curr = node.right;
				Node curr_parent = node; 
				while(curr.left != null)
				{
					curr_parent = curr;
					curr = curr.left;
				}

				if(curr != node.right)
				{
					// Replace Node To Be Deleted with its Inorder successor
					node.data = curr.data;	

					//remove this inorder successor from the tree
					delete(curr,curr.data, curr_parent);

				}
				else
				{
					curr.left = node.left;
					if(parent != null)
					{
						if(parent.left == node)
						{
							parent.left = node.right;
						}
						else
						{
							parent.right = node.right;
						}
					}
					else
					{
						this.root = curr;
					}
				}
			}
		}
	}

	private void deleteViaInorderPredecessor(Node node, int item,Node parent) {

		// TODO Auto-generated method stub
		if(node == null)
		{
			return;
		}

		if(item < node.data)
		{
			delete(node.left,item,node);
		}
		else if(item > node.data)
		{
			delete(node.right,item,node);
		}
		else
		{
			if((node.left == null) && (node.right == null))	
			{
				if(parent == null)
				{
					this.root = null;
				}
				else
				{
					if(parent.left == node)
					{
						parent.left = null;
					}
					else
					{
						parent.right = null;
					}
				}
			}
			else if(node.left == null)
			{
				if(parent.left == node)
				{
					parent.left = node.right;
				}
				else
				{
					parent.right = node.right;
				}
			}
			else if(node.right == null)
			{
				if(parent.left == node)
				{
					parent.left = node.left;
				}
				else
				{
					parent.right = node.left;
				}
			}
			else
			{
				// inorder predecessor
				Node curr = node.left;
				Node curr_parent = node; 
				while(curr.right != null)
				{
					curr_parent = curr;
					curr = curr.right;
				}

				if(curr != node.right)
				{
					// Replace Node To Be Deleted with its Inorder successor
					node.data = curr.data;	

					//remove this inorder successor from the tree
					delete(curr,curr.data, curr_parent);

				}
				else
				{
					curr.right = node.right;
					if(parent != null)
					{
						if(parent.left == node)
						{
							parent.left = node.right;
						}
						else
						{
							parent.right = node.right;
						}
					}
					else
					{
						this.root = curr;
					}
				}
			}
		}

	}

	public void displayPreOrder() {

		this.displayPreOrder(this.root);		
	}

	private void displayPreOrder(Node node) {

		System.out.print(node.data+" ");
		if(node.left != null)
		{
			displayPreOrder(node.left);
		}
		if(node.right != null)
		{
			displayPreOrder(node.right);
		}
	}
}
