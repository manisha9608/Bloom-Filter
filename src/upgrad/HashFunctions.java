package upgrad;

public class HashFunctions {
	//typical hash function
	public int hash1(String x) {
		int prime = 31;
		String s = "upgrad";
		int a = 1;
		int hash = x.hashCode();
		for(int i = 0; i < s.length(); i++) {
			a = (prime * a) + s.charAt(i) + hash;
		}
		return a;
	}
	
	public int hash2(String x) {
		int prime = 17;
		int a = 1;
		String s = "education";
		int hash = x.hashCode();
		for(int i = 0; i < s.length(); i++) {
			a = (prime * a) + s.charAt(i) + hash;
		}
		return a;
	}
}
