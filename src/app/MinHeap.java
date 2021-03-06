/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *Structure of heap imitated from geeks for geeks min heap example
 */
public class MinHeap {

	private DataNode []heap;//Create our heap variable
	private final int MAX_HEAP = 1024, ROOT_INDEX = 1;//Set the max size of our heap and our root index position
	private int size;//Holds the actual size of our heap
	
	/**
	 * Ctor
	 */
	public MinHeap() {
		heap = new DataNode[MAX_HEAP+1];//Create the new memory for our heap -- add one for the zero index that we dont count or access since it is always the max-min
		size = 0;//Start out with an empty heap we don't use the first element in the array -- it is used as zero padding to prevent index out of bounds exceptions -- code would get way more complicated
		heap[0] = new DataNode(Integer.MIN_VALUE, "");//Set the heap top to the minimum integer value possible with mt data -- only really used a base case for when we have one node
	}
	
	/**
	 * Getter for the index of the parent
	 * @param index
	 * @return the index of the parent position
	 */
	private int getParentIndex(int index) {
		return (index/2);//Returns the index of the parent position
	}
	
	/**
	 * getter for the index of the right child
	 * 
	 * @param index
	 * @return the index of the left right position
	 */
	private int getRightChildIndex(int index) {
		return (2*index + 1);//Returns the index of the right child position
	}
	
	/**
	 * getter for the index of the left child
	 * 
	 * @param index
	 * @return the index of the left child position
	 */
	private int getLeftChildIndex(int index) {
		return (2*index);//Returns the index of the left child position
	}
	
	/**
	 * Checks if the current position is at a leaf node
	 * @param index
	 * @return if at leaf node of not
	 */
	private boolean atLeaf(int index) {
		//if our current index position is greater than the index position of its parent and the index position is less than or equal to size, then were are a leaf node
		//[-9999,3,4,7,8,9,10,12];size = 7; -- is 4 leaf? -- no, index = 2 !> 7/2 -> 2 !> 3 -- is 9 leaf? -- yes, index = 5 > 7/2 -> 5 > 3//Example
		
		if(index > (size / 2) && index <= size) {//First condition will only evaluate true if we are at a leaf node, second condition only ensures we don't go out of bounds for the heap
			return true;//We are at a leaf node
		}
		return false;//Not at leaf node
	}
	
	/**
	 * Swaps two nodes based on their respective index
	 * uses a temp var
	 * @param index1
	 * @param index2
	 */
	private void swapNodes(int index1, int index2) {
		DataNode temp;//Used as a node placeholder
		
		temp = heap[index1];//Save first item
		heap[index1] = heap[index2];//Move second item to first
		heap[index2] = temp;//Load saved first item into the second item
	}
	
	/**
	 * Sorts the heap for priority order
	 * @param index
	 */
	private void minHeapify(int index) {//Move a larger item at the root node downwards until it meets minheap requirements
		//If we are going to heapify, we can only do so if we are not already at a node index
		//System.out.println("Heapify" + size);//Debug
		//If we are not at leaf, then proceed with the heapify -- also we wont proceed if we only have the root node
		if(!atLeaf(index) && size > 1) {//This is the base case for our recursion -- should also prevent null ptrs due to bad index -- SHOULD
			//If we are going to heapify, we only need to do so if the current index priority value is greater than either of its children, otherwise we are valid minheap and no need to move
			if(heap[index].priority > heap[getLeftChildIndex(index)].priority || heap[index].priority > heap[getRightChildIndex(index)].priority) {
				//If the left child is less than the right, then we choose to swap the left -- always pick the smaller of the two to move up and traverse that subtree
				if(heap[getLeftChildIndex(index)].priority < heap[getRightChildIndex(index)].priority) {
					swapNodes(index, getLeftChildIndex(index));//Swap the current node with its left child
					minHeapify(getLeftChildIndex(index));//Recurse down the left subtree and make the comparison again
					
					
				}else{//If the right child is less than the left, then we choose to swap the right -- always pick the smaller of the two to move up and traverse that subtree
						swapNodes(index, getRightChildIndex(index));//Swap the current node with its right child
						minHeapify(getRightChildIndex(index));//Recurse down the right subtree and make the comparison again
					}
				}
			}
		}
	
	/**
	 * Adds item to heap and percolates the item into correct order of priority
	 * @param priority
	 * @param data
	 * @throws IndexOutOfBoundsException
	 */
	public void addItem(int priority, String data) throws IndexOutOfBoundsException{
		if(size >= MAX_HEAP) {//Handle full heap
			throw new IndexOutOfBoundsException("Error: Heap is full");//Indicate heap is full
		}
		
		DataNode new_item = new DataNode(priority,data);//Create our new node with the data
				
		heap[++size] = new_item;//Increase the index/size of our heap and add the new node there
		int temp_index = size;//Copy the current size to be used as an index for percolation as this is where the new element was inserted
		
		//Percolate value up until it is greater than its less than its children but greater than its parent
		while(heap[temp_index].priority < heap[getParentIndex(temp_index)].priority) {
			swapNodes(temp_index, getParentIndex(temp_index));//Swap the child node at temp index with the parent node
			temp_index = getParentIndex(temp_index);//Set our new index to the swapped nodes new position(parent position)
		}
				
				
	}
	
	/**
	 * Returns the minimum value(root)
	 * @return the root
	 */
	public String peekMin() {
		if(size == 0) {
			//System.out.println("peek0");//Debug
			return "";//Return an empty string if heap is empty
		}
		DataNode min_node = heap[ROOT_INDEX];//Used to hold the minimum value of the heap -- found at the root index
		return ("[" + min_node.priority + "] " + min_node.node_data);//return the stored min node -- specifically formatted for this assignment
	}
	
	/**
	 * Returns the minimum value given that the 
	 * size of the heap is not zero
	 * Resorts the heap after deletion
	 * @return the min value in pre-formatted string
	 */
	public String pullMin(){
		if(size == 0) {
			//System.out.println("peek0");//Debug
			return "";//Return an empty string if heap is empty
		}
		//System.out.println("Pull" + size);//Debug
		DataNode min_node = heap[ROOT_INDEX];//Used to hold the minimum value of the heap -- found at the root index
		heap[ROOT_INDEX] = heap[size--];//Set the root indexes new value to last node according to level order, then decrement the size of the heap
		minHeapify(ROOT_INDEX);//Heapify our heap -- move the new item at the root index down to form a valid heap if needed
		return ("[" + min_node.priority + "] " + min_node.node_data);//return the stored min node -- specifically formatted for this assignment
	}
	/**
	 * Tests whether the heap is full or not
	 * @return is full
	 */
	public boolean isFull() {
		if(size >= MAX_HEAP) {
			return true;//Return that the heap is true
		}
		return false;//Return that there is room
	}
	
	/**
	 * Prints out each node
	 * Used to store the heap into a file
	 * @return temp variable which keeps increasing as i++
	 */
	public String dumpMemory() {
		String temp = "";//Used to hold the heaps contents
		for(int i = 1; i <= size; i++) {//Start the loop at 1 since we don't utilize index 0 of this array
			temp += "[" + heap[i].priority + "] " + heap[i].node_data;//Should already have a newline for separation here
		}
		//System.out.println(temp);//Debug
		return temp;//Return the created data
	}
	
	/**
	 * Node class to hold an item in the heap
	 * @author ConnorSullivan31
	 *
	 */
	private class DataNode{
		/**
		 *Ctor 
		 */
		public DataNode(int pri, String data) {
			priority = pri;//Set the nodes priority
			node_data = data;//Set the nodes data
		}
		
		public int priority; 
		public String node_data;
	}
	
}