/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class MinHeap {

	private DataNode []heap;//Create our heap variable
	private final int MAX_HEAP = 1024;//Set the max size of our heap
	private int size;//Holds the actual size of our heap
	
	/**
	 * Ctor
	 */
	public MinHeap() {
		heap = new DataNode[MAX_HEAP+1];//Create the new memory for our heap -- add one for the zero index that we dont count
		size = 1;//Start out with an empty heap we don't use the first element in the array
	}
	
	public String dump() {//Dump the contents of the heap
		String temp = "";//Used to hold the heaps contents
		for(int i = 1; i < size; i++) {//Start the loop at 1 since we don't utilize index 0 of this array
			temp += heap[i].node_data + "\n";//Add the data and then the newline
		}
		return temp;//Return the created data
	}
	
	public DataNode getMin() throws IndexOutOfBoundsException{
		if(size == 1 && heap[size] == null) {
			throw new IndexOutOfBoundsException("Error: Heap is empty");
		}
		return heap[1];//Return the smallest element
	}
	
	public void addItem(int pri, String data) throws IndexOutOfBoundsException{
		DataNode temp = new DataNode(pri, data);//Used to hold the input data
		if(size == MAX_HEAP + 1) {//if size is somehow greater than max heap throw an exception that we cannot add more data -- note offset of 1 to accommodate the extra space we saved for zero offset
			throw new IndexOutOfBoundsException("Error: Heap is full");//Heap is full
		}
		heap[size++] = temp;//store the data in the next spot and increment size
		percolate(size-1);//Sort out our heap -- subtract 1 since we just incremented for the next spot
	}
	
	public DataNode pullMin() throws IndexOutOfBoundsException{
		DataNode temp;//Used as a placeholder for the min value
		if(size == 1 && heap[size] == null) {
			throw new IndexOutOfBoundsException("Error: Heap is empty");
		}
		
		temp = heap[1];//Save the min value
		heapify(size -1);//Moves last item in heap to top, decrements size, and sorts it down -- use the minus one offset here since size is always 1 ahead for insertion
		
		return temp;//Return the min value
	}
	
	private void percolate(int index) {
		DataNode swap;//Used as a placeholder to swap the two nodes
		if(heap[index].priority < getParentPriority(index)) {
			//Upward traversal
				swap = heap[index];//Save the parent
				heap[index] = getParentNode(index);//Save the left child to the parent
				setParentNode(index, swap);//Load in the saved parent value to the right child
				index = moveUp(index);//Move up the tree
		}else {
			return;//We have the priority in the right order and no longer need to percolate as the current index is 
		}
		
		percolate(index);//Call again -- move father up the heap
	}
	
	private void heapify(int index) {
		DataNode swap;//Used as a placeholder to swap two nodes
		
		if(index == size -1) {
			heap[1] = heap[index];//Move the last item in the heap to the head position
			heap[index] = null;//Clear(delete) the last node in the heap
			size--;//Decrement our size of the heap
		}
		
		if(heap[index].priority > getLeftPriority(index) && getLeftPriority(index) < getRightPriority(index)) {
			//Right traversal
			if(heap[index].priority > getRightPriority(index)) {
				swap = heap[index];//Save the parent
				heap[index] = getRightNode(index);//Save the left child to the parent
				setRightNode(index, swap);//Load in the saved parent value to the right child
				index = moveRight(index);//Move the index to the right child
			}
		}else if(heap[index].priority > getRightPriority(index) && getRightPriority(index) < getLeftPriority(index)) {
			//Left traversal
			if(heap[index].priority > getLeftPriority(index)) {
				swap = heap[index];//Save the parent
				heap[index] = getLeftNode(index);//Save the left child to the parent
				setLeftNode(index, swap);//Load in the saved parent value to the left child
				index = moveLeft(index);//Move the index to the right child
			}
		}else {
			return;//We have the priority in the right order or it is equal to the children below it
		}
				
		heapify(index);//Call again -- move farther down the heap
	}
	
	
	
	
	private int getParentPriority(int index) {
		if(index <= 1) {
			return heap[index].priority;//Return the priority at zero since thats where it would end if there's only one item
		}
		return heap[index/2].priority;//Returns the priority value of the parent
	}
	
	private int getRightPriority(int index) {
		return heap[2*index + 1].priority;//Returns the priority value of the right child
	}
	
	private int getLeftPriority(int index) {
		return heap[2*index].priority;//Returns the priority value of the left child
	}
	
	
	
	
	private DataNode getParentNode(int index) {
		if(index <= 1) {
			return heap[index];//Return the heap at zero since thats where it would end if there's only one item
		}
		return heap[index/2];//Returns the data node of the parent
	}
	
	private DataNode getRightNode(int index) {
		return heap[2*index + 1];//Returns the data node of the right child
	}
	
	private DataNode getLeftNode(int index) {
		return heap[2*index];//Returns the data node of the left child
	}
	
	
	
	
	private void setParentNode(int index, DataNode data) {
		if(index <= 1) {
			heap[index] = data;//Set the priority at zero since thats where it would end if theres only one item
		}else {
		heap[index/2] = data;//Sets the data node of the right child
		}
	}
	
	private void setRightNode(int index, DataNode data) {
		heap[2*index + 1] = data;//Sets the data node of the right child
	}
	
	private void setLeftNode(int index, DataNode data) {
		heap[2*index] = data;//Sets the data node of the left child
	}
	
	
	
	
	private int moveUp(int index) {
		if(index <= 1) {
			return 0;//Return the index zero since thats where it would end if theres only one item
		}
		return (index/2);//Returns the index of the parent position
	}
	
	private int moveRight(int index) {
		return (2*index + 1);//Returns the index of the right child position
	}
	
	private int moveLeft(int index) {
		return (2*index);//Returns the index of the left child position
	}
	
	
	
	
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
