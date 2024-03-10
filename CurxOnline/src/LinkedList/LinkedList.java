package LinkedList;

import java.util.*;
class LinkedList {
	private class Node {
		int data;
		Node next;

		Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public LinkedList(Node head, Node tail, int size) {
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	// O(1)
	public int size() {
		return this.size;
	}

	// O(1)
	public boolean isEmpty() {
		return this.size() == 0;
	}

	// O(1)
	public int getFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty.");
		}

		return this.head.data;
	}

	// O(1)
	public int getLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty.");
		}

		return this.tail.data;
	}

	// O(N)
	public int getAt(int idx) throws Exception {
		Node temp = this.getNodeAt(idx);
		return temp.data;
	}

	// O(N)
	private Node getNodeAt(int idx) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size()) {
			throw new Exception("Invalid arguments");
		}

		Node retVal = this.head;
		for (int i = 0; i < idx; i++) {
			retVal = retVal.next;
		}

		return retVal;
	}

	// O(1)
	public void addFirst(int data) {
		Node node = new Node(data, this.head);

		if (this.size() == 0) {
			this.head = node;
			this.tail = node;
		} else {
			this.head = node;
		}

		this.size++;
	}

	// O(1)
	public void addLast(int data) {
		Node node = new Node(data, null);

		if (this.size() == 0) {
			this.head = node;
			this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;
		}

		this.size++;
	}

	// O(n)
	public void addAt(int idx, int data) throws Exception {
		if (idx < 0 || idx > this.size()) {
			throw new Exception("Invalid arguments");
		}

		if (idx == 0) {
			this.addFirst(data);
		} else if (idx == this.size()) {
			this.addLast(data);
		} else {
			Node nm1 = this.getNodeAt(idx - 1);
			Node n = nm1.next;

			Node node = new Node(data, n);
			nm1.next = node;

			this.size++;
		}
	}

	// O(1)
	public int removeFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}

		int retVal = this.head.data;

		if (this.size() == 1) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = this.head.next;
		}

		this.size--;
		return retVal;
	}

	// O(n)
	public int removeLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}

		int retVal = this.tail.data;

		if (this.size() == 1) {
			this.head = null;
			this.tail = null;
		} else {
			Node sm2 = this.getNodeAt(this.size() - 2);
			sm2.next = null;
			this.tail = sm2;
		}

		this.size--;
		return retVal;
	}

	// O(n)
	public int removeAt(int idx) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size()) {
			throw new Exception("Invalid arguments");
		}

		if (idx == 0) {
			return this.removeFirst();
		} else if (idx == this.size() - 1) {
			return this.removeLast();
		} else {
			Node nm1 = this.getNodeAt(idx - 1);
			Node n = nm1.next;
			Node np1 = n.next;

			nm1.next = np1;
			this.size--;

			return n.data;
		}
	}

	// O(n)
	public void display() {
		Node node = this.head;

		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}

		//System.out.println("END");
	}
	public void swapNodes(int num1, int num2) {
	}

	public void EliminateDuplicatesInSortedList()
	{
		this.EliminateDuplicatesInSortedList(this.head);
	}

	private void EliminateDuplicatesInSortedList(Node head) {

		Node prev = null;
		Node curr = head;

		while(curr != null)
		{
			if((head != curr) && (curr.data == prev.data))
			{
				prev.next = curr.next;
				this.size--;
			}
			else
			{
				prev = curr;
			}
			curr = curr.next;
		}

		this.tail = prev;

	}

	public static void main(String[] args) throws Exception {

		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();


		LinkedList list = new LinkedList();

		for (int i = 0; i < N; i++) {
			list.addLast(scn.nextInt());
		}

		int N1 = scn.nextInt();
		int N2 = scn.nextInt();
		list.swapNodes(N1, N2);
		list.display();
	}

	public void mergeTwoSortedList(LinkedList list2) throws Exception {
		if(!list2.isEmpty())
		{
			Node temp = this.head;
			Node prev = null;

			int indexList2 = 0;

			while((temp != null) && (indexList2 != list2.size))
			{
				int list2data = list2.getAt(indexList2);
				if(list2data < temp.data )
				{
					Node node = new Node(list2data, temp);
					if(prev == null)
					{
						this.head = node;
					}
					else
					{
						prev.next = node;
					}
					prev = node;
					indexList2++;
					this.size++;
				}
				else
				{
					prev = temp;
					temp = temp.next;	
				}
			}
			while (indexList2 != list2.size)
			{
				int list2data = list2.getAt(indexList2);
				this.addLast(list2data);
				indexList2++;			
			}

		}
	}

	public void arrangeOddEven() {
		this.arrangeOddEven(this.head);		
	}

	private void arrangeOddEven(Node startNode) {
		Node odd = null;
		Node even = null;
		Node evenStart = null;
		Node oddStart = null;

		Node curr = startNode;

		while(curr != null)
		{
			if((curr.data % 2) == 1)
			{
				if(odd == null)
				{
					oddStart = curr;
				}
				odd = curr;
				if(even != null)
				{
					even.next = odd.next;
				}
			}
			else
			{
				if(even == null)
				{
					evenStart = curr;
				}
				even = curr;
				if(odd != null)
				{
					odd.next = even.next;
				}	
			}
			curr = curr.next;
		}
		if(odd != null)
		{
			this.head = oddStart;
			odd.next = evenStart;
		}
		if(even != null)
		{
			this.tail = even;
		}
	}

	public void kReverseList(int k) {

		this.kReverseList(k,this.head);

	}

	private void kReverseList(int k, Node startNode) {

		Node subStartNode = null;
		Node curr = startNode;
		Node next = null;
		Node prev = null;
		Node prevListEnd = null; 

		int numSubLists = this.size/k;
		int mid = (k-1)/2;

		for(int i = 0 ; i < numSubLists ; i++)
		{
			prev = null;
			subStartNode = curr;
			for(int j = 0 ; j < k ; j++)
			{
				next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			subStartNode.next = curr;
			if(i == 0)
			{
				this.head = prev;
			}
			else
			{
				prevListEnd.next = prev;
			}
			prevListEnd = subStartNode;
		}
		if(subStartNode != null)
		{
			this.tail = subStartNode;
		}
	}

	public int findMidPoint() {

		return this.findMidPoint(this.head);

	}

	private int findMidPoint(Node node) {

		Node slow = node;
		Node fast = node;

		while((fast.next != null) && (fast.next.next != null))
		{
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow.data;
	}

	public boolean isPalindrome() {
		return this.isPalindrome(this.head);
	}

	private boolean isPalindrome(Node node) {
		Node slow = node;
		Node fast = node;

		Stack<Integer> stack= new Stack<>(); 
		while((fast.next != null) && (fast.next.next != null))
		{
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		if(this.size%2 == 0)
		{
			stack.push(slow.data);
		}

		slow = slow.next;

		boolean isLLPalindrome = true;
		while(slow != null)
		{
			int item = stack.pop();
			if(slow.data != item)
			{
				isLLPalindrome = false;
				break;
			}
			//node = node.next;
			slow = slow.next;
		}

		return isLLPalindrome;

	}

	public void reverseDataLL() {

		this.reverseDataLL(this.head);	
	}

	private void reverseDataLL(Node node) {

		Node slow = node;
		Node fast = node;

		Stack<Node> stack= new Stack<>(); 
		while((fast.next != null) && (fast.next.next != null))
		{
			stack.push(slow);
			slow = slow.next;
			fast = fast.next.next;
		}
		if(this.size%2 == 0)
		{
			stack.push(slow);
		}

		slow = slow.next;

		while(slow != null)
		{
			Node item = stack.pop();
			int temp = item.data;
			item.data = slow.data;
			slow.data = temp;
			//node = node.next;
			slow = slow.next;
		}
	}

	public void reversePtrLL() {

		this.reversePtrLL(this.head);		
	}

	private void reversePtrLL(Node head) {

		Node prev = null;
		Node curr = head;
		Node next = null;

		this.tail = curr;

		while(curr != null)
		{
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		this.head = prev;
	}

	public void kAppendList(int k) {
		kAppendList(k,this.head);

	}

	private void kAppendList(int k, Node node) {

		Node startOld = node;
		Node endOld = this.tail;
		Node curr = node;

		int SizeMinusK = this.size - k;

		if(SizeMinusK >= 0)
		{
			for(int i = 0 ; i < SizeMinusK-1 ; i++)
			{
				curr = curr.next;
			}

			if(SizeMinusK != 0)
			{
				if(curr.next != null)
				{	
					this.head = curr.next;
					endOld.next = startOld;
				}
				this.tail = curr;
				curr.next = null;
			}
		}
	}
}

