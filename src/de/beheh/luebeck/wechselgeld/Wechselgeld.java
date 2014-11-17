package de.beheh.luebeck.wechselgeld;

import java.util.Arrays;

/**
 *
 * @author Benedict Etzel
 */
public class Wechselgeld {

	public static void main(String[] args) {
		int[] coins = {2, 2, 3};
		System.out.println(Wechselgeld.count(coins, 5) + " combinations");
	}

	public static int count(int[] coins, int amount) {
		System.out.println("investigating " + amount + " with " + Arrays.toString(coins));
		int k = 0;
		for (int i = 0; i < coins.length; i++) {
			if (coins[i] < amount) {
				k += count(Arrays.copyOfRange(coins, i + 1, coins.length), amount - coins[i]);
			}
			if (coins[i] == amount) {
				k++;
			}
		}
		System.out.println("ending " + amount);
		return k;
	}
}
