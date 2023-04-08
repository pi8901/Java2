import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CharCollection {

	ArrayList<Character> list = new ArrayList<Character>();
	
	
	public CharCollection(char... cc) {
		
		for (int i = 0; i < cc.length; i++) {
			this.list.add(cc[i]);
		}
	}
	
	public CharCollection(String s) {
		for (int i = 0; i < s.length(); i++) {
			this.list.add(s.charAt(i));
		}
	}
	
	int size() {
		return list.size();
	}
	
	 int count(char c) {
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(c)) {
				counter++;
			} 
		}
		return counter;
	}
	
	int different() {
		Set<String> distinctSet = new HashSet(list);
		
		return distinctSet.size();
	}

	char top() {
		
		char top = list.get(0);
		int max = 0;
		int max_o = 0;
		for(int i = 0; i < list.size(); i++)
		{
			char x = list.get(i);
			for(int j = 0; j < list.size(); j++)
			{
				if(x == list.get(j))
				{
					max ++;
				}
			}
			
			if(max > max_o)
			{
				max_o = max;
				top = x;
			}
			max = 0;
		}
		return top;
	}
	 
	@Override
	public String toString() {
		return list.toString();
	}
	
	public CharCollection moreThan(int m) {
		
		String out = "";
		for(int i = 0; i < list.size(); i++)
		{
			if(count(list.get(i)) <= m)
			{
				out = out + list.get(i);
			}
		}
		return new CharCollection(out);
	}
	
	public boolean equals(Object x) {
		
		return (list.containsAll((Collection<?>) x));
	}
	
	CharCollection except(CharCollection cc) {
		
		list.removeAll(cc.list);
		System.out.println(list);
		
		return new CharCollection(list.toString());
	}
	
	boolean isSubset(CharCollection cc) {
		return list.is
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharCollection cc = new CharCollection('m','x','B', 'R', 'A','m', 'K', 'A','m', 'D','m', 'A', 'B','m', 'R', 'A','m');
		CharCollection cd = new CharCollection('B', 'R', 'x');
		System.out.println(cc.isSubset(cd));
	}

}
