package HeapAndHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HashMapGetMaxFreqCharClass {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String str = scan.nextLine();

		HashMap<Character,Integer> map = new HashMap<>();

		for(int i = 0; i< str.length(); i++)
		{
			if(!map.containsKey(str.charAt(i)))
			{
				map.put(str.charAt(i), 1);
			}
			else
			{
				int count = map.get(str.charAt(i));
				count++;
				map.put(str.charAt(i),count);
			}
		}
		Set<Map.Entry<Character, Integer>> enteries= map.entrySet();
		System.out.println(enteries.toString());
		int max = 0;
		char charWithMaxFreq = '\0';
		for(Map.Entry<Character, Integer> entry : enteries)
		{
			if(max < entry.getValue())
			{
				max = entry.getValue();
				charWithMaxFreq = entry.getKey();
			}
		}
		System.out.println(charWithMaxFreq);
	}

}
