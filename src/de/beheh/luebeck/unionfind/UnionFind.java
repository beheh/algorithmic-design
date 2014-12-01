package de.beheh.luebeck.unionfind;

/**
 *
 * @author Benedict Etzel
 */
public class UnionFind {

	protected final int[] objects;
	protected final int[] p;
	protected final int[] r;

	public UnionFind(int n) {
		// initialise n sets
		objects = new int[n];
		p = new int[n];
		r = new int[n];
		for (int i = 0; i < n; i++) {
			// create object
			objects[i] = i;
			// set as a new root (since parent pointer is self)
			p[i] = i;
			// depth is 0
			r[i] = 0;
		}
	}

	public int find(int x) {
		if(x == p[x]) {
			// parent is self, so we found a root
			return x;
		}
		int i = find(p[x]);
		p[x] = i; // path compression (minimize recursion in future)
		return i;
	}

	public void union(int x, int y) {
		// add x as child to y => add y as parent to x
		x = find(x);
		y = find(y);
		if (r[x] > r[y]) {
			p[y] = x; // x is new root
		} else if (r[x] == r[y]) {
			p[y] = x;
			r[x] = r[x] + 1;
		} else { // r[x] < r[y]
			p[x] = y; // y continues to be root
		}
	}

	public int count() {
		int c = 0;
		// count tree roots (where parent = self)
		for (int i = 0; i < objects.length; i++) {
			if (p[i] == objects[i]) {
				// parent is self, so we found another root
				c++;
			}
		}
		return c;
	}

}
