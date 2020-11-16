package upgrad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
static BufferedReader br = null;
	
	public static void generateBloomFilter(BloomFilter bf) {
		String fileLoc = "data/voterIds.txt";
		System.out.println("Populating bloom filter using valid voter Ids");
		int numFalsePos = 0;
		try {
			br = new BufferedReader(new FileReader(fileLoc));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			String l = null;
			int counter = 500000; // to use 2/3 to build bloom filter and use 1/3 to test the bloom filter.
			int initial_counter = counter;
			while((l = br.readLine()) != null) {
				if(counter > initial_counter/3) {
					bf.add(l);
				}
				else {
					if(bf.isPresent(l)) {
						numFalsePos++;
					}
					bf.add(l);
				}
				counter--;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Populating bloom filter completed and numFalsePos:"+numFalsePos);
	}
	
	public static void generateStorage(Storage st, BloomFilter bf) {
		String fileLoc = "data/voterId_candid.txt";
		System.out.println("Populating storage using direct addressing");
		try {
			br = new BufferedReader(new FileReader(fileLoc));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String l = br.readLine();
			while((l = br.readLine()) != null) {
				String [] entry = l.split(" ");
				int voterId = Integer.parseInt(entry[0]);
				int candId = Integer.parseInt(entry[1]);
				st.add(voterId, candId, bf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Populating storage using direct addressing completed!!");
	}
	public static void populateHashTable(HashTableStorage1 st, BloomFilter bf) {
		String fileLoc = "data/voterId_candid.txt";
		System.out.println("Populating storage using hash table");
		try {
			br = new BufferedReader(new FileReader(fileLoc));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String l = br.readLine();
			while((l = br.readLine()) != null) {
				String [] entry = l.split(" ");
				int voterId = Integer.parseInt(entry[0]);
				int candId = Integer.parseInt(entry[1]);
				st.add(voterId, candId, bf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Populating storage using hash table completed!!");
	}
	
	public static void main(String[] args) {
		BloomFilter bf = new BloomFilter(4792530, 7);
		System.out.println(bf.filter);
		generateBloomFilter(bf);
		Storage directAddSt = new Storage();
		generateStorage(directAddSt, bf);
		HashTableStorage1 ht = new HashTableStorage1();
		populateHashTable(ht, bf);
		
		System.out.println("Following are the available commands:\n f: Find candidate id corresponding to voterId");
		System.out.println("c: count votes of candidate Id");
		System.out.println("q: quit the program");
		
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		try {
			String s= null;
			for(int i=0;i<1000;i++) {
				s = inp.readLine();
				if(s.equals("f")) {
					System.out.println("Enter voter Id");
					int vId = Integer.parseInt(inp.readLine());
					System.out.println("direct addressing....candidate Id:"+directAddSt.find(vId, bf));
					System.out.println("hash table....candidate Id:"+ht.find(vId, bf));
					
				}
				else if(s.equals("c")) {
					System.out.println("Enter candidate Id");
					int cId = Integer.parseInt(inp.readLine());
					System.out.println("direct addressing....number of votes:"+directAddSt.count(cId));
					System.out.println("hash table....number of votes:"+ht.count(cId));
				}
				else if(s.equals("q")) {
					System.out.println("Quitting");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
