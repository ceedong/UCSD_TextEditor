package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;// Implement this method
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("The object cannot store null elements!");
		}
		
		LLNode<E> newNode = new LLNode<E>(element);// TODO: Implement this method
		LLNode<E> prevNode = tail.prev;
		prevNode.next = newNode;
		newNode.next = tail;
		tail.prev = newNode;
		newNode.prev = prevNode;
		size += 1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index <0 || index > size-1) {
			throw new IndexOutOfBoundsException("The index is out of bounds!");
		}
		LLNode<E> curNode = new LLNode<E>(null);
		curNode = head;
		for(int i= -1; i < index; i++) {
			curNode = curNode.next;
		}
		// TODO: Implement this method.
		return curNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		
		if(element == null) {
			throw new NullPointerException("The elements cannot be null!");
		}
		
		if(index <0 || (index > size -1 && index != 0)) {
			throw new IndexOutOfBoundsException("The index is out of bounds!");
		}
		// TODO: Implement this method
		LLNode<E> curNode = new LLNode<E>(null);
		curNode = head;
		for (int i = -1; i< index; i++) {
			//System.out.println(curNode.data);
			curNode = curNode.next;
			
		}
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> prevNode = new LLNode<E>(null);
		prevNode = curNode.prev;
	
		prevNode.next = newNode;
		newNode.next = curNode;
		curNode.prev = newNode;
		newNode.prev = prevNode;
		size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index <0 || index > size -1) {
			throw new IndexOutOfBoundsException("The index is out of bounds!");
		}
		LLNode<E> curNode = new LLNode<E>(null);
		curNode = head;
		for (int i= -1; i < index; i++) {
			curNode = curNode.next;
		}
		LLNode<E> prevNode = new LLNode<E>(null);
		LLNode<E> nextNode = new LLNode<E>(null);
		prevNode = curNode.prev;
		nextNode = curNode.next;
		nextNode.prev = prevNode;
		prevNode.next = nextNode;
		size -= 1;
		// TODO: Implement this method
		return curNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null) {
			throw new NullPointerException("The elements cannot be null!");
		}
		
		if(index <0 || index > size -1) {
			throw new IndexOutOfBoundsException("The index is out of bounds!");
		}
		
		LLNode<E> curNode = new LLNode<E>(null);
		for (int i = -1; i < index; i++) {
			curNode = curNode.next;
		}
		E dataToReturn = curNode.data;
		curNode.data = element;
		// TODO: Implement this method
		return dataToReturn;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
