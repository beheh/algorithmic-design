package de.beheh.luebeck.cuckoo;

public class Cuckoo {

	private final Integer[] table1;
	private final Integer[] table2;
	private final int p;

	private int hash1(int x) {
		return (x % p) % table1.length;
	}

	private int hash2(int x) {
		return (1 + (x % (p - 2))) % table2.length;
	}

	public Cuckoo(int m) {
		table1 = new Integer[m];
		table2 = new Integer[m];
		p = Primes.getPrime(m);
	}

	public boolean lookup(int x) {
		// if exists in either table
		return (table1[hash1(x)] != null && table1[hash1(x)] == x) || (table2[hash2(x)] != null && table2[hash2(x)] == x);
	}

	public void insert(int x) {
		int h1 = hash1(x);
		if (table1[h1] == null) { // try first table
			table1[h1] = x;
		} else if (table2[hash2(x)] == null) { // try second table
			table2[hash2(x)] = x;
		} else { // "kick" old value to other table/hash function
			int kick = table1[h1];
			table1[h1] = x;
			// kick previous value to table 2
			Integer next = table2[hash2(kick)];
			table2[hash2(kick)] = kick;
			if (next != null) {
				// kick previous value in table 2 back to table 1
				insert(next);
			}
		}
	}

	public void delete(int x) {
		if (table1[hash1(x)] == x) {
			table1[hash1(x)] = null;
		} else if (table2[hash2(x)] == x) {
			table2[hash2(x)] = null;
		}
	}
}
