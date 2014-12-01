package de.beheh.luebeck.unionfind;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author benedict
 */
public class UnionFindTest {

	public UnionFindTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of find method, of class UnionFind.
	 */
	@Test
	public void testFind() {
		System.out.println("find");
		int n = 0;
		UnionFind instance = null;
		instance = new UnionFind(1);
		assertEquals(0, instance.find(0));
		instance = new UnionFind(3);
		assertEquals(1, instance.find(1));
		assertEquals(2, instance.find(2));
	}

	/**
	 * Test of union method, of class UnionFind.
	 */
	@Test
	public void testUnion() {
		System.out.println("union");
		UnionFind instance = null;
		instance = new UnionFind(2);
		instance.union(0, 1);
		assertEquals(1, instance.count());
		instance = new UnionFind(6);
		instance.union(1, 0);
		instance.union(2, 1);
		instance.union(4, 3);
		instance.union(5, 3);
		instance.union(3, 1);
		assertEquals(1, instance.count());
	}

	/**
	 * Test of count method, of class UnionFind.
	 */
	@Test
	public void testCount() {
		System.out.println("count");
		UnionFind instance = null;
		instance = new UnionFind(1);
		assertEquals(1, instance.count());
		instance = new UnionFind(3);
		assertEquals(3, instance.count());
	}
}
