package upgrad;

// this class implements direct addressing for storage
public class Storage {
	int arr[];
	int size;
	int minId;
	
	Storage() {
		size = 1000000;
		minId = 100000;
		arr = new int[size];
	}
	/**
	 * save voterId and candidateId in array
	 * @param voterId voter Id
	 * @param candidateId candidate Id
	 * @param filter BloomFilter object
	 */
	public void add(int voterId, int candidateId, BloomFilter filter) {
		// first check if voterId is present in bloom filter
		if (filter.isPresent(Integer.toString(voterId))) {
			arr[voterId-minId] = candidateId;
		}
		
	}
	/**
	 * find candidateId corresponding to the given voterId
	 * @param voterId voter Id
	 * @param filter BloomFilter object
	 * @return candidate Id
	 */
	public int find(int voterId, BloomFilter filter) {
		// first check if voterId is present in bloom filter
		if(filter.isPresent(Integer.toString(voterId))) {
			System.out.println("voterId present in Bloom filter");
			return arr[voterId-minId]; // in case of false positive, returns 0
		}
		else {
			// If voterId is not present return 0;
			System.out.println("voterId not present in Bloom filter");
			return 0;
		}
	}
	/**
	 * count votes for given candidate Id
	 * @param candidateId
	 * @return count
	 */
	public int count(int candidateId) {
		System.out.println("Counting votes for candidateId:"+candidateId);
		int c=0;
		for(int i=0; i<size; i++) {
			if(arr[i]== candidateId)
				c++;
		}
		return c;
	}	
}
