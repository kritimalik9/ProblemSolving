package MoreRecursion;

import java.util.Scanner;

public class TowerOfHanoiClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numDiscs = scan.nextInt();
		String src = "T1";//'A';
		String dst = "T2";//'C';
		String buf = "T3";//'B';
		System.out.println(TowerOfHanoi(src,dst,buf,numDiscs));
	}

	private static int TowerOfHanoi(String src, String dst, String buf, int numDiscs) {

		if(numDiscs == 0)
		{
			return 0;
		}
		int steps = TowerOfHanoi(src,buf,dst,numDiscs-1);
		System.out.println("Move "+numDiscs+"th disc from "+src+" to "+dst);
		steps++;
		steps += TowerOfHanoi(buf,dst,src,numDiscs-1);

		return steps;

	}

}
