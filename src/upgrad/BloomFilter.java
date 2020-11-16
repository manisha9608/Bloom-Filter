package upgrad;

import java.util.BitSet;

public class BloomFilter {
	BitSet filter;
	
	HashFunctions functions;
	int collisionCount =0;
	int hash1, hash2;
	int size;
	int numOfHashFunctions;
	
	//Constructor
	BloomFilter(int filterSize, int numOfHashFunctions) {
		// create object of hashFunctions class
		functions = new HashFunctions();
		this.size = filterSize;
		this.numOfHashFunctions = numOfHashFunctions;
		// allocate memory for filter
		filter = new BitSet(size);
		filter.clear(); // initiate with 0
		
	}
	
	public boolean isPresent(String st) {
		hash1 = functions.hash1(st);
		hash2 = functions.hash2(st);
		for(int i=1;i<=numOfHashFunctions;i++) {
			if(!filter.get(Math.abs((hash1 + i *hash2) % size))) {
				return false;
			}
		}
		return true;
	}
	
	public void add(String st) {
		// check for collision
		if(isPresent(st)) {
			collisionCount++;
		}
		else {
			hash1 = functions.hash1(st);
			hash2 = functions.hash2(st);
			for(int i=1;i<=numOfHashFunctions;i++) {
				filter.set(Math.abs((hash1 + i *hash2) % size));
			}
		}
		
	}
	
}
