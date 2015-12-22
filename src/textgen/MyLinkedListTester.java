/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (EmptyStackException e) { }
		
		try {
			emptyList.remove(-1);
			fail("Check out of bounds");
		}
		catch (EmptyStackException e) { }
		
		try {
			longerList.remove(11);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) { }
		
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		try {
			list1.remove(1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) { }
		
		int c = longerList.remove(5);
		assertEquals("Remove: check c is correct ", 5, c);
		assertEquals("Remove: check element 5 is correct ", (Integer)6, longerList.get(5));
		assertEquals("Remove: check element 4 is correct ", (Integer)4, longerList.get(4));
		assertEquals("Remove: check size is correct ", 9, longerList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		assertTrue(list1.add(10));
		assertTrue(list1.add(0));
		assertTrue(list1.add(50));
		assertTrue(list1.add(20));
		assertTrue(list1.add(10));
		
		try {
			list1.add(null);
		}
		catch (NullPointerException e) { }
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals(shortList.size, 2);
		assertEquals(emptyList.size, 0);
		assertEquals(longerList.size, 10);
		assertEquals(list1.size, 3);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		shortList.add(0, "C");
		shortList.add(1, "D");
		assertEquals("Check short list first", "C", shortList.get(0));
		assertEquals("Check short list second", "D", shortList.get(1));
		
		try {
			emptyList.add(1, 10);
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) { }
		
		try {
			emptyList.add(-1, 10);
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) { }
		
		try {
			emptyList.add(0, null);
			fail("Added null element");
		}
		catch (NullPointerException e) { }
		
		emptyList.add(0, 10);
		assertEquals("Check empty list first", 10, (int)emptyList.get(0));
		
		assertEquals(5, (int) longerList.get(5));
		longerList.add(5, 100);
		assertEquals(100, (int) longerList.get(5));
		assertEquals(5, (int) longerList.get(6));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		shortList.set(0, "C");
		assertEquals("Check short list first", "C", shortList.get(0));
		shortList.set(0, "D");
		assertEquals("Check short list second", "D", shortList.get(0));
		shortList.set(1, "E");
		assertEquals("Check short list third", "E", shortList.get(1));
		
		try {
			shortList.set(10, "B");
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) { }
		
		try {
			emptyList.set(0, 10);
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) { }
	}
	
	/** Test adding and removing nodes on a new list */
	@Test
	public void testAll() {
		MyLinkedList<String> tempList = new MyLinkedList<String>();
		tempList.add("A");
		tempList.add("B");
		tempList.add("C");
		assertEquals(3, tempList.size);
		
		tempList.remove(0);
		assertEquals(2, tempList.size);
		assertEquals("B", tempList.get(0));
		
		tempList.add(0, "A");
		assertEquals("A", tempList.get(0));
		assertEquals(3, tempList.size);
		
		tempList.add(2, "D");
		assertEquals("D", tempList.get(2));
		assertEquals("C", tempList.get(3));
		assertEquals(4, tempList.size);
		
		try {
			tempList.add(-1, "D");
			fail("Out of Bound");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			tempList.add(5, "D");
			fail("Out of Bound");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			tempList.add(1, null);
			fail("Null element added");
		}
		catch (NullPointerException e) {}
		
		try {
			tempList.get(-1);
			fail("Out of bound");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			tempList.get(6);
			fail("Out of bound");
		}
		catch (IndexOutOfBoundsException e) {}
		
		tempList.add(4, "E");
		assertEquals("E", tempList.get(4));
		
		tempList.remove(0);
		tempList.remove(0);
		tempList.remove(0);
		tempList.remove(0);
		tempList.remove(0);
	}
}
