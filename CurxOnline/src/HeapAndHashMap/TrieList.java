package HeapAndHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrieList {

	private class Node{
		char data;
		HashMap<Character,Node> childList;
		boolean isTerminal;

		Node(Character data){
			this.data = data;
			this.isTerminal = false;
			this.childList = null;
		}		
	}

	TrieList()
	{
		this.root = new Node('\0');
		this.numWords = 0;
	}

	Node root;
	int numWords;
	public void addWordInDictionary(String word)
	{
		Node curr = root;
		for(int i=0 ; i<word.length();)
		{
			char ch = word.charAt(i);
			boolean isFound = false;
			while(curr.childList != null && curr.childList.containsKey(ch))
			{
				curr = curr.childList.get(ch);
				i++;
				isFound = true;
			}
			if(isFound)
			{
				continue;
			}
			if(!isFound)
			{
				Node newTrieNode = null;
				if(curr.childList == null)
				{
					curr.childList = new HashMap<>();
				}
				newTrieNode = new Node(ch);
				curr.childList.put(ch,newTrieNode);
				curr = newTrieNode;
			}
			i++;
		}
		curr.isTerminal = true;
		this.numWords++;
	}

	public void display(Node node,String osf)
	{
		if(node.isTerminal)
		{
			System.out.println(osf.substring(1)+node.data);
		}
		if((node == this.root) && (node.childList == null))
		{
			return;
		}
		if(node.childList == null)
		{
			return;		
		}
		Set<Map.Entry<Character,Node>> KeyValSet = node.childList.entrySet();
		for(Map.Entry<Character,Node> entry : KeyValSet)
		{
			this.display(entry.getValue(), osf+node.data);
		}
	}

	public boolean searchWord(Node node,String inputWord)
	{
		if(node.isTerminal && (inputWord.length() == 0))
		{
			return true;
		}
		if((node.childList != null) && (node.childList.containsKey(inputWord.charAt(0))))
		{
			return searchWord(node.childList.get(inputWord.charAt(0)), inputWord.substring(1));
		}
		else
		{
			return false;
		}
	}

	public static void main(String args[])
	{

		TrieList trie = new TrieList();

		//		trie.addWordInDictionary("arts");
		//		trie.addWordInDictionary("art");
		//		trie.addWordInDictionary("bug");
		//		trie.addWordInDictionary("boy");
		//		trie.addWordInDictionary("sea");
		//		trie.addWordInDictionary("seen");
		//		trie.addWordInDictionary("see");
		//		trie.addWordInDictionary("amit");
		//
		//		//trie.display(trie.root, "");
		//
		//		//System.out.println(trie.searchWord(trie.root, "amil"));
		//
		//		//trie.removeWord(trie.root, "see", null);
		//		//trie.display(trie.root, "");
		//		trie.removeWord(trie.root, "arts", null);
		//		trie.removeWord(trie.root, "art", null);
		//		trie.display(trie.root, "");
		//		System.out.println(trie.numWords);
		//		trie.removeWord(trie.root, "a", null);
		//		trie.display(trie.root, "");
		//		System.out.println(trie.numWords);

		/************************************************************************************/

		//		trie.addWordInDictionary("she");
		//		trie.addWordInDictionary("sells");
		//		trie.addWordInDictionary("sea");
		//		trie.addWordInDictionary("shore");
		//		trie.addWordInDictionary("the");
		//		trie.addWordInDictionary("by");
		//		trie.addWordInDictionary("sheer");
		//
		//		trie.display(trie.root, "");
		//		System.out.println(trie.numWords);
		//		trie.removeWord(trie.root, "she", null);
		//		trie.display(trie.root, "");
		//		System.out.println(trie.numWords);

		/***********************************************************************************/

		//		trie.addWordInDictionary("their");
		//		trie.addWordInDictionary("there");
		//		trie.addWordInDictionary("answer");
		//		trie.addWordInDictionary("any");
		//		trie.addWordInDictionary("bye");
		//
		//		trie.display(trie.root, "");
		//		System.out.println(trie.numWords);
		//		trie.removeWord(trie.root, "there", null);
		//		trie.display(trie.root, "");
		//		System.out.println(trie.numWords);

		/**********************************************************************************/

		trie.addWordInDictionary("abc");
		trie.addWordInDictionary("abgl");
		trie.addWordInDictionary("cdf");
		trie.addWordInDictionary("abcd");
		trie.addWordInDictionary("lmn");

		trie.display(trie.root, "");
		System.out.println(trie.numWords);
		trie.removeWord(trie.root, "abc", null);
		trie.display(trie.root, "");
		System.out.println(trie.numWords);
		trie.removeWord(trie.root, "abgl", null);
		trie.display(trie.root, "");
		System.out.println(trie.numWords);
		trie.removeWord(trie.root, "abcd", null);
		trie.display(trie.root, "");
		System.out.println(trie.numWords);
		



	}

	public void removeWord(Node node,String inputWord,Node parent)
	{
		if(node.isTerminal && (inputWord.length() == 0))
		{
			if(node.childList != null)
			{
				node.isTerminal = false;
			}
			else
			{
				parent.childList.remove(node.data);
			}
			this.numWords--;
			return;
		}
		if(inputWord.length() == 0)
		{
			return;
		}

		if((node.childList != null) && (node.childList.containsKey(inputWord.charAt(0))))
		{
			Node childNode = node.childList.get(inputWord.charAt(0));
			removeWord(childNode, inputWord.substring(1),node);
			if(node.childList.isEmpty())
			{
				node.childList = null;
				if(!node.isTerminal)
				{
					parent.childList.remove(node.data);
				}
			}

		}
	}
}

