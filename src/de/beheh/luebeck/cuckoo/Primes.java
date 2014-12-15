package de.beheh.luebeck.cuckoo;

import java.math.*;

/**
 * @author Sebastian Berndt <berndt@tcs.uni-luebeck.de>
 */

public class Primes {

	public static int getPrime(int m) {
		int x = 2 * m + 2;
		AKS aks = new AKS();
		while (!aks.isPrime(BigInteger.valueOf(x))) {
			x++;
		}
		return x;
	}

	public Primes() {
	}

	public static void main(String[] args) {
		System.out.println(Primes.getPrime(42));

	}

}
