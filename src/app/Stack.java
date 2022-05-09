/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *Surround operations in this class with a try/catch block
 */
public class Stack {
	private final int MAX_DISP = 8;// how many items we display at max
	private final int MAX_STACK = 1024;//Hardcode the max size of our stack to be 1024 items(item[0] is index padding) -- We could remove this later and init dynamically from the constructor
	private String []stack;//Stack variable
	private int size;//Size of stack;
	
	Stack(){
		stack = new String[MAX_STACK + 1];//Create an array of 9 elements - save the first element as an acess buffer to prevent index out of bounds
		size = 0;//Set the size of our stack to 0 since it is empty to start
	}
	
	/**
	 * Surround this in a try-catch block
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public String pop() throws IndexOutOfBoundsException{
		if(size == 0) {
			throw new IndexOutOfBoundsException("Error: Stack is empty");
		}

		return stack[--size];//Return the item at the current size index, then decrease the size
	}
	
	/**
	 * Peek at the item at the top of the stack
	 * @return
	 */
	public String peek() throws IndexOutOfBoundsException{
		if(size == 0) {
			throw new IndexOutOfBoundsException("Error: Stack is empty");
		}
		return stack[size-1];//Return the item at the top of the stack but don't remove it from the stack
	}
	
	/**
	 * Returns a string containing the whole stack -- exports in upward order, so items can be pushed in
	 * top to bottom and mirrors the stack -- the dumpMemory function is the reverse of this -- it
	 * is used to read from top to bottom -- here we write bottom to top in order to create the stack
	 * @return
	 */
	public String exportMemory() {
		String stack_dump = "";//Will hold the stack dump data to be returned -- init to empty in case our stack is empty at this time

			for(int i = 1; i <= size; i++) {
				stack_dump += stack[i];//No need to Separate with newlines -- entries should all be newline padded
			}
		
		return stack_dump;//Return the generated string of the stack dump
	}
	
	/**
	 * Returns a string containing the whole stack -- top to bottom order to be displayed correctly
	 * @return
	 */
	public String dumpMemory() {
		String stack_dump = "";//Will hold the stack dump data to be returned -- init to empty in case our stack is empty at this time

			for(int i = size, cnt = 0; i > 0 && cnt < (MAX_DISP); i--, cnt++) {
				stack_dump += stack[i];//No need to Separate with newlines -- entries should all be newline padded
			}
		
		return stack_dump;//Return the generated string of the stack dump
	}
	
	/**
	 * Surround this in a try/catch block
	 * @param data
	 * @throws IndexOutOfBoundsException
	 */
	public void push(String data) throws IndexOutOfBoundsException {
		if(size == MAX_STACK-1) {
			throw new IndexOutOfBoundsException("Error: Stack is full -- Cannot add more items -- Please pop the stack first");
		}
		stack[++size] = data;//Set the data to the current size index, then increase the size
	}
	
	/**
	 * Returns if our stack is full or not
	 * @return
	 */
	public boolean isFull() {
		if(size == MAX_STACK-1) {
			return true;//Return that our stack is full
		}
		return false;
	}
	
}
