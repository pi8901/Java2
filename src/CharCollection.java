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
		
		if(this.size() == 0)
		{
			return 0;
		}
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
		
		try
		{
			CharCollection y = (CharCollection) x;
			this.list.sort(null);
			y.list.sort(null);
			if(this.list.equals(y.list))
			{
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	CharCollection except(CharCollection cc) 
	{
		CharCollection copy = this;
		int i = 0;
		while(i < copy.size())
		{
			for (int j = 0; j < cc.size() - 1; j++)
			{
				if(copy.list.get(i) == cc.list.get(j))
				{
					copy.list.remove(i);
				}
			}
		i++;
		}
		
		
		return copy;
	}
	
	boolean isSubset(CharCollection cc) 
	{
		return this.list.toString().contains(cc.list.toString());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharCollection a = new CharCollection('A', 'N', 'A', 'N', 'A', 'S');
		CharCollection cc = new CharCollection("NASE");
		CharCollection cd = new CharCollection('B', 'R', 'x');
		System.out.println(a.except(cc));
	}

}