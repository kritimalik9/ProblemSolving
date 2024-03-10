package BinaryTree;

import java.util.Scanner;

public class Heap {

	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		Heap minheap = new Heap();

		/*
		 5
		 10 20 30 40 5
		 */
		minheap.display();
		
		minheap.addNodeInHeap(3);
		minheap.display();
		
		System.out.println(minheap.deleteNodeInHeap());
		minheap.display();

		System.out.println(minheap.deleteNodeInHeap());
		minheap.display();
		
		System.out.println(minheap.deleteNodeInHeap());
		minheap.display();


	}

	public void display() {
		display(this.heaplist);

	}

	private void display(int[] arr) {

		for(int i = 0; i< this.size;i++)
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println("END");

	}

	public static final int DEFAULT_CAPACITY = 5;

	public Heap()
	{
		this(DEFAULT_CAPACITY);
	}



	public Heap(int size) {
		this.heaplist = new int[size];
		this.size = 0;
		constructHeap(this.heaplist);
	}

	private void constructHeap(int[] arr) {

		int numElements = scan.nextInt();
		for(int i = 0; i < numElements; i++)
		{
			int data = scan.nextInt();
			addNodeInHeap(data);
		}

	}

	int[] heaplist;
	int size;

	public void addNodeInHeap(int data)
	{
		this.heaplist[this.size] = data;
		this.size++;
		this.addNodeInHeap(this.size-1,this.heaplist);
		if(this.size == this.heaplist.length)
		{
			int[] oldlist = this.heaplist;
			this.heaplist = new int[this.heaplist.length*2];
			for(int i = 0 ; i < oldlist.length ; i++)
			{
				this.heaplist[i] = oldlist[i];
			}
		}
	}

	private void addNodeInHeap(int idx,int[] arr) {

		if(idx == 0)
		{
			return;
		}

		int parent = (idx-1)/2;

		if(this.heaplist[idx] < this.heaplist[parent])
		{
			int temp = this.heaplist[idx];
			this.heaplist[idx] = this.heaplist[parent];
			this.heaplist[parent] = temp;
			addNodeInHeap(parent,arr);
		}

	}
	public int deleteNodeInHeap()
	{
		if(this.size > 0)
		{
			int root = this.heaplist[0];
			this.heaplist[0] = this.heaplist[size-1];
			this.heaplist[size-1] = 0;
			this.size--;
			deleteNodeInHeap(0);
			return root;
		}
		else
		{
			return -1;
		}
	}

	private void deleteNodeInHeap(int idx) {
		// TODO Auto-generated method stub
		int left = 2*idx+1;
		int right = 2*idx+2;

		int smallest = idx;

		if((left < this.size)
				&& (this.heaplist[left] < this.heaplist[smallest]))
		{
			smallest = left;
		}

		if((right < this.size)
				&& (this.heaplist[right] < this.heaplist[smallest]))
		{
			smallest = right;
		}

		if(smallest != idx)
		{
            int temp = this.heaplist[idx];
            this.heaplist[idx] = this.heaplist[smallest];
            this.heaplist[smallest] = temp;
            deleteNodeInHeap(smallest);
		}
	}
}
