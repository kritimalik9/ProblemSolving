package HeapAndHashMap;

/*********Q:GetNumWithHighestFrequency********/
import java.util.*;

public class GetNumWithHighestFrequency{

	public static Integer highestFreq(Integer[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int numWithMaxCount = 0;
		int maxCount = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length ; i++)
		{
			int count = 0;
			Integer keyVal = map.get((int)arr[i]);
			if(keyVal == null)
			{
				map.put(arr[i],1);
				count = 1;
			}
			else
			{
				count = keyVal+1;
				map.put(arr[i],keyVal+1);
			}
			if(maxCount < count)
			{
				maxCount = count;
				numWithMaxCount = arr[i];
			}
		}
		return numWithMaxCount;
	}
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int str= s.nextInt();
		Integer[] num = new Integer[str];

		for(int i=0; i<str ; i++){
			num[i]= s.nextInt();

		}
		System.out.println(highestFreq(num));

	}
}