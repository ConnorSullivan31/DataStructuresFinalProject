/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *Surround operations in this class with a try/catch block
 */
public class Stack {
	private final int MAX_STACK = 8;//Hardcode the max size of our stack to be 8 items -- We could remove this later and init dynamically from the constructor
	private String []stack;//Stack variable
	private int size;//Size of stack;
	private boolean is_full, is_empty;//Holds if our stack is full or not and if it is empty or not
	
	Stack(){
		stack = new String[8];//Create an array of 8 elements
		size = 0;//Set the size of our stack to 0 since it is empty to start
		is_full = false;//Start knowing that our stack is not full
		is_empty = true;//Start knowing that our stack is empty
	}
	
	/**
	 * Surround this in a try-catch block
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public String pop() throws IndexOutOfBoundsException{
		if(size < 0) {
			size = 0;
			is_empty = true;//Set that the stack is empty
		}
		if(is_empty) {
			throw new IndexOutOfBoundsException();
		}
		is_full = false;//Set that the stack if no longer full
		return stack[size--];//Return the item at the current size index, then decrease the size
	}
	
	/**
	 * Peek at the item at the top of the stack
	 * @return
	 */
	public String peek() {
		if(is_empty == false) {
		return stack[size];//Return the item at the top of the stack but don't remove it from the stack
		}
		return "";//Return an empty string if our stack is empty
	}
	
	/**
	 * Returns a string containing the whole stack
	 * @return
	 */
	public String dump() {
		String stack_dump = "";//Will hold the stack dump data to be returned -- init to empty in case our stack is empty at this time
		if(is_full) {//Start at normal size index
			for(int i = size; i >= 0; i--) {
				stack_dump += stack[i] + "\n";//Separate with newlines
			}
		}else {//Start at size -1 since after each push we increase the size before we can add new data
			for(int i = size-1; i >= 0; i--) {
				stack_dump += stack[i] + "\n";//Separate with newlines
			}
		}
		
		return stack_dump;//Return the generated string of the stack dump
	}
	
	/**
	 * Surround this in a try/catch block
	 * @param data
	 * @throws IndexOutOfBoundsException
	 */
	public void push(String data) throws IndexOutOfBoundsException {
		if(is_full) {
			throw new IndexOutOfBoundsException("Error: Stack is full -- Cannot add more items -- Please pop the stack first");
		}
		is_empty = false;//Set that the stack is no longer empty
		stack[size++] = data;//Set the data to the current size index, then increase the size
		if(size == MAX_STACK) {
			size--;//Move back to the last item in the index
			is_full = true;//Set that our stack is full
		}
	}
	
	/**
	 * Returns if our stack is full or not
	 * @return
	 */
	public boolean isFull() {
		return is_full;//Returns the variable that holds if the stack is full or not
	}
	
}
