/**
 * 
 */
package app;

import app.FileIO.AccessMode;

/**
 * @author ConnorSullivan31
 *
 */
public class GcodeFabric {

	//GCode Viewer
	private MinHeap gcode_list;//Used to hold the gcode displayed in the gcode viewer
	private String completed_instructions = "";//Holds the gcode that has been pulled from the min heap
	private String heap_filename, completed_filename;//Filename for where we'll write the data for the heap and the completed instructions
	
	GcodeFabric(){
		loadGcodeData();//Generatre the min heap and give it dummy values for now, later change it to file input
	}
	
	
	//Gcode Viewer
	private void loadGcodeData() {
		gcode_list = new MinHeap();//Create the heap for the gcode data
		heap_filename = "GcodeData.dat";//Set the name for our file that holds data pulled from the heap
		completed_filename = "ExecutedGcode.dat";//Set the name for our file that holds data pulled from the heap
		loadData();//Load in the data from the file to the min heap
	}
	
	/**
	 * Display the peeked item from the heap
	 * @return The root node of the heap
	 */
	public String importGcode() {//If we are told ThiS iS NoT HOw iTS SupPoSed tO bE, then just change dump() to get min
		String temp;//Used to hold the data dumped by the heap
		temp = gcode_list.peekMin();//Save the dumped data
		if(temp == "") {//pullMin() and peekMin() return an empty string if the heap is empty -- made it this way as its easier to interface with TextAreas -- no need for exceptions here either
			return ">[EMPTY]";
		}
		
		return temp;//Return the dumped data with an arrow indicator for the top priority item
	}
	
	public boolean isSolelyWhitespace(String data) {
		if(data.matches("[\\s]+")) {
			return true;//Return that the string is only whitespace
		}
		return false;//Return that the data is not just whitespace
	}
	
	public boolean validatePriority(String data) {//Check if the user entered an integer
		String temp;//Will hold our new string that doesnt contain any newlines or spaces
		temp = data.replaceAll("[\\n\\s]", "");//Remove any newlines or spaces from the text box
		if(temp.matches("([0]*[1-9][0-9]?)|([0]*[1][0]{2})")) {//match 1 -100 accounting for leading zeros
			return true;//Return true if we have a semi-valid number, will become fully valid in formulatePriority();
		}
		
		return false;//return false if we didn't match the regex
	}
	
	private int formulatePriority(String data) {
		String temp;//Will hold our new string that doesnt contain any newlines or spaces
		temp = data.replaceAll("[\\n\\s]", "");//Remove any newlines or spaces from the text box
		return Integer.parseInt(temp);//Return the parsed data
	}
	
	public void addGCode(String pri, String data) {
		String temp;//Holds our new string that doesn't contain newlines
		
		temp = data.replaceAll("\\n", " ");//Replace any newlines from the text box with a space
		
		try {
		gcode_list.addItem(formulatePriority(pri), temp + "\n");//add the item to the heap -- re-add the newline that we removed above
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);//Probably don't need this printout -- probably remove later
		}
	}
	
	public void removeGCode() {
		String temp;//Used to hold the result of pull min	
		//Returns an empty string if the size of the heap is zero -- No exceptions are needed because of the return
			temp = gcode_list.pullMin();//Pull the top value off the heap -- gets stored it to temp
			if(temp != "") {
				completed_instructions += ">" + temp;//Add the pulled gcode to completed instructions for later display
			}
	}
	
	public String importCompletedCode() {
		if(completed_instructions == "") {
			return "[EMPTY]";//Indicate empty
		}
		return completed_instructions;//Return the gcode pulled from min heap
	}
	
	public void clearCompletedCode() {
		completed_instructions = "";//Clear the string that holds previous instructions
	}
	
	public boolean isRoomG() {
		if(gcode_list.isFull()) {
			return false;
		}
		return true;//Return true if there is room for a new task
	}
	
	private void loadData() {
		String data = "";//Used to hold the data from the file data dump
		String new_item = "", new_pri = "";//Used to hold a single data item and its priority
		boolean in_priority = false;//Used to tell if we have braces and extract the number
		FileIO heapfile = new FileIO(heap_filename,AccessMode.INPUT);//Open the file
		data = heapfile.loadData();//Load in the filedata
		//System.out.println("Data SAtring" + data);//Debug file data
		for(int i = 0; i < data.length(); i++) {
			
			if(in_priority) {
				if(data.charAt(i) == ']') {
					in_priority = false;//closing bracket -- should have extracted priority at this point
				}else {
				new_pri += data.charAt(i);//Add number in brackets to priority
				}
				continue;//Do not add this data to the new item string
			}//if
			
			if(data.charAt(i) == '[') {//opening bracket = set to get pri mode
				in_priority = true;//Set that we are now getting priority
				continue;//Do not add this to the data string
			}//if
			
			new_item  += data.charAt(i);//Save the current character to the new item
			if(data.charAt(i) == '\n' && i !=0) {//Dont add a initial newline -- shouldnt happen with how we export our data
				//System.out.println("New Item: " + new_item);//Debug
				gcode_list.addItem(Integer.parseInt(new_pri), new_item);//If we reach a newline, we know that we are at a new item due to how we parse and save memory
				new_pri = "";//Reset the priority
				new_item = "";//Reset new item to empty
				continue;//Get next charachter
			}//if
		}//for
		
		//Load in the precompleted gcode back into the completed instuctions string
		FileIO completedfile = new FileIO(completed_filename,AccessMode.INPUT);//Open the file
		completed_instructions = completedfile.loadData();//Save the data from the file into completed_instructions string
		
	}
	
	public void saveData() {
		FileIO heapfile = new FileIO(heap_filename,AccessMode.OUTPUT);//Open the file
		heapfile.saveData(gcode_list.dumpMemory());//Write the circular linked list data to the file
		
		//Save the completed instructions pulled off of the heap
		FileIO completedfile = new FileIO(completed_filename,AccessMode.OUTPUT);//Open the file
		completedfile.saveData(completed_instructions);//Write the circular linked list data to the file
		
		
	}
	
	
}
