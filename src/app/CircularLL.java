/**
 * 
 */
package app;

import javafx.css.Size;

/**
 * @author ConnorSullivan31
 *
 */

public class CircularLL<T extends Comparable<? super T>> {
	
	private int list_size;// Init list size holder variable
	private Node sentinel_node;// List start and end
	private Node iterative_node_addr;//Used to loop through our circularly linked list
	
	/**
	 * Ctor
	 */
	CircularLL() {
		sentinel_node = new Node(null, null,null);// Init head node
		sentinel_node.next_node_address = sentinel_node;//Link the sentinel node to itself
		sentinel_node.prev_node_address = sentinel_node;//Link the sentinel node to itself
		iterative_node_addr = sentinel_node;
		list_size = 0;// Don't count sentinels, otherwise count would be up by 2
	}

	/**
	 * Return back to the start of the doubly circular linked list
	 */
	public void returnToStart() {
		iterative_node_addr = sentinel_node;//Move back to the start of the list
	}
	

	/**
	 * Removes one specified item from list
	 * 
	 * @param item_data
	 */
	public void deleteCurrentItem(){
		if(list_size > 0) {//Make sure that we never delete the sentinel node
		iterative_node_addr.prev_node_address.next_node_address = iterative_node_addr.next_node_address;//Set the next link of the previous block the next link of the iterator block
		iterative_node_addr.next_node_address.prev_node_address = iterative_node_addr.prev_node_address;//Set the next nodes previous link to point to the node before the iterator block
		}
		//At this point, we should have deleted the current item since it is no longer referenced by either the block before it or the block behind it
		//System.out.println("Delete: " + getCurrent());//Debug
	}


	/**
	 * Adds item immediately after head
	 * 
	 * @param item_data
	 */
	public void addItem(T item_data) {
		Node new_item = new Node(item_data, sentinel_node,sentinel_node.prev_node_address);// Continue chain. New data gets link to sentinal nodes address
		sentinel_node.prev_node_address = new_item;//Set the sentinals back link to the new node
		new_item.prev_node_address.next_node_address = new_item;//Point the node before to the new node
		iterative_node_addr = new_item;//Set our iterative node to hold the value of the newly added item as we will continue messages from here
		
		// System.out.println(new_item.node_data);//Remove eventually
		list_size++;
	}
	
	//Returns the next node with valid data in the circularly linked list
		public T getNext() {
			if(list_size == 0) {
				return null;//Return an empty string if our only node is the sentinal
			}

			iterative_node_addr = iterative_node_addr.next_node_address;//Move to the next node

			if(iterative_node_addr == sentinel_node) {//If we're at the sentinal node, continue on to the next node
				getNext();//Call function again until we get non-null data
			}

			return iterative_node_addr.node_data;//Return the next string data
		}
	
		//Returns the next previous node with valid data in the circularly linked list
		//Probably wont use this, doubly linking is only for the easy delete ability, but we may need to rotate through the list backward at some point, but keep private for now
		public T getPrev() {
			if(list_size == 0) {
				return null;//Return an empty string if our only node is the sentinal
			}

			iterative_node_addr = iterative_node_addr.prev_node_address;//Move to the next node

			if(iterative_node_addr == sentinel_node) {//If we're at the sentinal node, continue on to the next node
				getPrev();//Call function again until we get non-null data
			}

			return iterative_node_addr.node_data;//Return the next string data
		}
		
		//Returns the next node with valid data in the circularly linked list
				public T getCurrent() {
					if(list_size == 0) {
						return null;//Return an empty string if our only node is the sentinal
					}

					if(iterative_node_addr == sentinel_node) {//If we're at the sentinal node, continue on to the next node
						iterative_node_addr = iterative_node_addr.next_node_address;//Move to the next node
					}

					return iterative_node_addr.node_data;//Return the next string data
				}
		
		
	private class Node {

		/**
		 * 
		 * @param data
		 * @param mem_address
		 */
		public Node(T data, Node next_address, Node prev_address) {
			node_data = data;
			next_node_address = next_address;
			prev_node_address = prev_address;
		}

		// Node data
		public T node_data;

		// Next node address
		public Node next_node_address;
		public Node prev_node_address;

	}
}