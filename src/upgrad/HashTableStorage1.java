package upgrad;

import java.util.Hashtable;
import java.util.Set;

public class HashTableStorage1 {
	Hashtable<Integer, Integer> ht;
	
	HashTableStorage1() {
		ht = new Hashtable<Integer, Integer>();
	}
	/**
	 * save voterId and candidateId in hash table
	 * @param voterId voter Id
	 * @param candidateId candidate Id
	 * @param filter BloomFilter object
	 */
	public void add(int voterId, int candidateId, BloomFilter filter) {
		// first check if voterId is present in bloom filter
		if (filter.isPresent(Integer.toString(voterId))) {
			ht.put(voterId,  candidateId);
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
			return ht.get(voterId); // in case of false positive, returns 0
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
		Set<Integer> keys = ht.keySet();
        for(Integer key: keys){
            int id = ht.get(key);
            if(id == candidateId) {
            	c++;
            }
        }
        return c;
	}	
}
