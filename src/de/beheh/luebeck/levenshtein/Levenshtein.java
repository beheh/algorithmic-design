package de.beheh.luebeck.levenshtein;

/**
 * Levenshtein für Algorithmendesign
 *
 * @author Benedict Etzel
 */
public class Levenshtein {

	private static final int c1 = 1;
	private static final int c2 = 2;

	public static void main(String[] args) {
		String s = "Tier";
		String t = "Tor";
		int[][] d = new int[s.length() + 1][t.length() + 1];

		d[0][0] = 0; // keine Kosten bei lambda=lambda
		// triviale Bildung der Worte aus leeren Wörtern per insert
		for (int i = 1; i <= s.length(); i++) {
			d[i][0] = c1;
		}
		for (int i = 1; i <= t.length(); i++) {
			d[0][i] = c1;
		}

		// für jeden Buchstaben des Strings s
		for (int i = 1; i <= s.length(); i++) {
			// berechne Kosten zusammen mit Buchstaben des Strings t
			for (int j = 1; j <= t.length(); j++) {

				// insert-Kosten: Erzeugung des bisherigen Worts + ein insert (c1)
				int insert = d[i][j - 1] + c1;
				// insert-Kosten: Erzeugung des bisherigen Worts + ein delete (c1)
				int delete = d[i - 1][j] + c1;

				// wähle die geringsten Kosten:
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					// entweder insert-/delete-Kosten oder keine weiteren Kosten bei Gleichheit
					d[i][j] = Math.min(Math.min(insert, delete), d[i - 1][j - 1]);
				} else {
					// entweder insert-/delete-Kosten oder Ersetzungskosten (c2)
					d[i][j] = Math.min(Math.min(insert, delete), d[i - 1][j - 1] + c2);
				}
			}
		}

		// Ergebnis steht nach abgeschlossenen Vergleichen am Ende (jeder mit jedem verglichen)
		System.out.println(d[s.length()][t.length()]);

		for (int[] d1 : d) {
			System.out.print("[");
			for (int i = 0; i < d1.length; i++) {
				if (i != 0) {
					System.out.print(", ");
				}
				System.out.print(d1[i]);
			}
			System.out.println("]");
		}
	}
}
