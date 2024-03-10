package DynamicProgramming;

import java.util.Scanner;

public class MinimumEditDistanceDp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String str1 = scan.nextLine();
		String str2 = scan.nextLine();

		int count  = 0;

		long start = System.currentTimeMillis();
		count = MinEditDistanceRecursive(str1,str2);
		long end = System.currentTimeMillis();
		System.out.println("Time taken to execute this normal recursive function MINE " + (end-start)+"ms");
		System.out.println(count);


	}

	private static int MinEditDistanceRecursive(String str1, String str2) {

		if(str1.length() == 0)
		{
			return str2.length();	
		}
		else if(str2.length() == 0)
		{
			return str1.length();
		}
		int minEdits = 0;
		if(str1.charAt(0) == str2.charAt(0))
		{
			minEdits = MinEditDistanceRecursive(str1.substring(1,str1.length()),str2.substring(1,str2.length()));
		}
		else
		{
			//int minEditsAddInStr2 = 1+MinEditDistanceRecursive(str1,""+str1.charAt(0)+str2);
			// Below is better approach of add operation
			int minEditsAddInStr2 = 1+MinEditDistanceRecursive(str1.substring(1, str1.length()),str2);
			// Remove is mine OK
			int minEditsRemoveInStr2 = 1+MinEditDistanceRecursive(str1,str2.substring(1,str2.length()));
			//int minEditsReplaceInStr2 = 1+MinEditDistanceRecursive(str1,""+str1.charAt(0)+str2.substring(1,str2.length()));
			// Below is better approach of replace operation
			int minEditsReplaceInStr2 = 1+MinEditDistanceRecursive(str1.substring(1,str1.length()),str2.substring(1,str2.length()));
			minEdits = Math.min(minEditsAddInStr2, minEditsRemoveInStr2);
			minEdits = Math.min(minEdits, minEditsReplaceInStr2);
		}
		return minEdits;
	}

}
