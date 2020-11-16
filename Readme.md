1. Change the file path in MainClass.java
2. Number of false positive is 2785 ~0.0056
3. This application implements both hash table and direct addressing in the same project
4. Results from both the storages are displayed simultaneously.


 Direct Addressing: Time Complexity:
 	ADD: 
 		Time taken to filter out voterId using Bloom Filter = O(1)
 		Time to add in array = O(1)
 		Worst Case COmplexity = O(1)
	FIND:
		Time taken to filter out voterId using Bloom Filter = O(1)
		Time to retrieve entry for voterId = O(1)
		Worst Case Complexity = O(1)
	COUNT:
		Time taken to count votes for given candidate Id = O(n), where n= No. of entries in array


Hash Table: Time Complexity
	ADD:
		Time taken to filter out voterId using Bloom Filter = O(1)
 		Time to add in hash table = O(1)
 		Worst Case COmplexity = O(1)
	FIND:
		Time taken to filter out voterId using Bloom Filter = O(1)
		Time to retrieve entry for voterId from hash table = O(n) (Worst case while collisions happen)
		Worst Case Complexity = O(n)
	COUNT:
		Time taken to count votes for given candidate Id = O(n), where n= No. of entries in array
		
 