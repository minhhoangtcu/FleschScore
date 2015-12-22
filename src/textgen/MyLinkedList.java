package textgen;

import java.util.AbstractList;
import java.util.EmptyStackException;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		if (element != null) {
			LLNode<E> node = new LLNode<E>(element);
			if (isEmpty())
				head = node;
			else {
				tail.next = node;
				node.prev = tail;
			}
			tail = node;
			size++;
			return true;
		} else
			throw new NullPointerException();
	}

	public boolean addFront(E element) {
		if (element != null) {
			LLNode<E> node = new LLNode<E>(element);
			if (isEmpty())
				tail = node;
			else {
				node.next = head;
				head.prev = node;
			}
			head = node;
			size++;
			return true;
		} else
			throw new NullPointerException();
	}

	public boolean addEnd(E element) {
		if (element != null) {
			LLNode<E> node = new LLNode<E>(element);
			if (isEmpty()) {
				head = node;
				tail = node;
			} else {
				node.next = tail;
				node.prev = tail.prev;
				tail.prev.next = node;
				tail.prev = node;
			}
			size++;
			return true;
		} else
			throw new NullPointerException();
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		if (isValidIndex(index)) {
			LLNode<E> node = head;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node.data;
		} else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		if (element == null)
			throw new NullPointerException();
		else if ((index == size) | (index == 0 && isEmpty()))
			add(element); // add to the end of the list
		else if (!isValidIndex(index))
			throw new IndexOutOfBoundsException();
		else if (index == size - 1)
			addEnd(element);
		else if (index == 0)
			addFront(element);
		else {
			LLNode<E> pushing = head;
			for (int i = 0; i < index; i++) {
				pushing = pushing.next;
			}

			LLNode<E> newNode = new LLNode<E>(element);
			newNode.next = pushing;
			newNode.prev = pushing.prev;
			pushing.prev = newNode;
			newNode.prev.next = newNode;
			size++;
		}
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Return the true if the list in empty */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Return true if the provided index is valid index of the list
	 * 
	 * @param index
	 *            The index to check if it is in the bound of the list
	 * @return return true if index is in the bound, false if otherwise
	 */
	public boolean isValidIndex(int index) {
		if (isEmpty())
			return false;
		else
			return (index >= 0 && index <= size - 1);
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (isValidIndex(index)) {
			if (index == size - 1)
				return removeEnd();
			else if (index == 0)
				return removeFront();
			else {
				LLNode<E> node = head;
				for (int i = 0; i < index; i++) {
					node = node.next;
				}
				node.prev.next = node.next;
				node.next.prev = node.prev;
				size--;
				return node.data;
			}
		} else
			throw new IndexOutOfBoundsException();
	}

	public E removeFront() {
		if (!isEmpty()) {
			E output = head.data;
			head.next.prev = null;
			head = head.next;
			size--;
			return output;
		} else
			throw new EmptyStackException();
	}

	public E removeEnd() {
		if (!isEmpty()) {
			E output = tail.data;
			tail.prev.next = null;
			tail = tail.prev;
			size--;
			return output;
		} else
			throw new EmptyStackException();
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (element == null)
			throw new NullPointerException();
		else if (!isValidIndex(index))
			throw new IndexOutOfBoundsException();
		else {
			LLNode<E> node = head;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			node.data = element;
			return node.data;
		}
	}

	public void printList() {
		LLNode<E> runner = head;
		while (runner != null) {
			System.out.print(runner + "\t");
			runner = runner.next;
		}
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public String toString() {
		return data + "";
	}

}
