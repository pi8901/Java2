import java.util.ArrayList;


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
		return 0;
	}
	
	int count(char c) {
		
		return 0;
	}
	
	int different() {
		
		return 0;
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
