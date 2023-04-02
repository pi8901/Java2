import java.util.ArrayList;
import java.util.Collections;


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
		//Collections.frequency(list, list);
	}

	int top() {
		
		return 0;
	}
	 
	@Override
	public String toString() {
		return "CharCollection []";
	}
	
	public CharCollection moreThan() {
		
		return null;
	}
	
	public boolean equals() {
		
		return false;
	}
	
	CharCollection except(CharCollection cc) {
		return null;
	}
	
	boolean isSubset(CharCollection cc) {
		return false;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
