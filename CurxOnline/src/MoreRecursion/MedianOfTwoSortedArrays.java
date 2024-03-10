package MoreRecursion;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		int sortedArray1[] = new int[size];
		int sortedArray2[] = new int[size];
		for(int i = 0 ; i < sortedArray1.length ; i++)
		{
			sortedArray1[i] = scan.nextInt();
		}
		for(int j = 0 ; j < sortedArray2.length ; j++)
		{
			sortedArray2[j] = scan.nextInt();
		}
		int mergedArray[] = new int[size*2];
		for(int j = 0 ; j < mergedArray.length ; j++)
		{
			mergedArray[j] = 0;
		}
		int median = MedianOfTwoSortedArraysOn(sortedArray1,sortedArray2,size);
		//median2 = MedianOfTwoSortedArraysLogN(sortedArray1,sortedArray2,size);

		System.out.println(median);

	}

	private static int MedianOfTwoSortedArraysOn(int[] sortedArray1, int[] sortedArray2, int size) {
		// TODO Auto-generated method stub

		int i = 0;  
		int j = 0; 
		int count;
		int m1 = -1, m2 = -1;

		/* Since there are 2n elements, median will 
		           be average of elements at index n-1 and 
		           n in the array obtained after merging ar1 
		           and ar2 */
		for (count = 0; count <= size; count++)
		{
			/* Below is to handle case where all 
		              elements of ar1[] are smaller than 
		              smallest(or first) element of ar2[] */
			if (i == size)
			{
				m1 = m2;
				m2 = sortedArray2[0];
				break;
			}

			/* Below is to handle case where all 
		               elements of ar2[] are smaller than 
		               smallest(or first) element of ar1[] */
			else if (j == size)
			{
				m1 = m2;
				m2 = sortedArray1[0];
				break;
			}

			if (sortedArray1[i] < sortedArray2[j])
			{   
				/* Store the prev median */
				m1 = m2;  
				m2 = sortedArray1[i];
				i++;
			}
			else
			{
				/* Store the prev median */
				m1 = m2;  
				m2 = sortedArray2[j];
				j++;
			}
		}

		return (m1 + m2)/2;	
	}

	private static int MedianOfTwoSortedArraysLogN(int[] sortedArray1, int[] sortedArray2,int size) {
		return size;

		/*https://www.geeksforgeeks.org/median-of-two-sorted-arrays*/
	}

	private static int median(int arr[], int n)
	{
		if (n%2 == 0)
			return (arr[n/2] + arr[n/2-1])/2;
		else
			return arr[n/2];
	}
}
