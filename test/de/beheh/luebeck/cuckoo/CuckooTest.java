/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beheh.luebeck.cuckoo;

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
public class CuckooTest {

	public CuckooTest() {
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
	 * Test of lookup method, of class Cuckoo.
	 */
	@Test
	public void testLookup() {
		System.out.println("lookup");
		Cuckoo instance = new Cuckoo(5);
		instance.insert(1);
		assertTrue(instance.lookup(1));
		assertFalse(instance.lookup(0));
	}

	/**
	 * Test of insert method, of class Cuckoo.
	 */
	@Test
	public void testInsert() {
		System.out.println("insert");
		Cuckoo instance = new Cuckoo(50);
		for (int i = 1; i <= 50; i++) {
			instance.insert(i);
		}
		for (int i = 1; i <= 50; i++) {
			assertTrue(instance.lookup(i));
		}
	}

	/**
	 * Test of delete method, of class Cuckoo.
	 */
	@Test
	public void testDelete() {
		System.out.println("delete");
		Cuckoo instance = new Cuckoo(2);
		instance.insert(1);
		instance.delete(1);
		assertFalse(instance.lookup(1));
	}
	
	@Test
	public void testLectureExample() {
		System.out.println("insert");
		Cuckoo instance = new Cuckoo(3);
		instance.insert(3);
		instance.insert(5);
		instance.insert(2);
		instance.insert(7);
		instance.insert(10);
	}


}
