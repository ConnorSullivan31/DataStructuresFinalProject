/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class ModelViewInterconnect {
	//System Banner
	private CircularLL<String> system_messages;//Used to hold messages for the system banner
	//Machine Automator
	private Stack machine_tasks;//Used to hold the current automation tasks for the machine automator
	//GCode Viewer
	private MinHeap gcode_list;//Used to hold the gcode displayed in the gcode viewer
	
	/**
	 * Ctor
	 */
	public ModelViewInterconnect() {
		loadSystemBanner();//Create and load memory for the system banner
		loadMachineAutomator();//Create and load memory for the machine automator
		loadGcodeData();//Create and load memory for the gcode viewer
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//System Banner
	private void loadSystemBanner() {
		system_messages = new CircularLL<>();
		system_messages.addItemImmediate("1:POWER = ON");//Eventually replace these with file input
		system_messages.addItemImmediate("2:OIL LEVEL = OK");
		system_messages.addItemImmediate("3:COOLANT LEVEL = HGIH");
	}
	
	public String importSystemBanner() {
		String temp;//Used to hold the next status message
		temp = system_messages.getNext();//Get the next message
		
		if(temp == null) {//Check if the list is empty -- We have to check for null here because of how we implemented our linked list, we can check for an empty string "" on the other two structure that have a dump feature
			return "[EMPTY]";//Return that our message list is empty
		}
		
		return temp;//Return the next message in the list
	}
	
	public void addMessage(String msg) {
		String temp;//Holds our new string that doesn't contain newlines
		temp = msg.replaceAll("\\n", " ");//Replace any newlines from the text box with a space
		
		system_messages.addItemImmediate(temp);//Add a message to the linked list
	}
	
	public void removeMessage() {
		try {
			system_messages.deleteCurrentItem();//Remove the current item
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);//Probably remove this later but keep for now
		}
	}
	
	/*public boolean isEmpty() {
		return system_messages.isEmpty();
	}*/
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Machine Automator
	private void loadMachineAutomator() {
		machine_tasks = new Stack();//Create the task stack
		try {//We don't need this here but it is kept as an example when doing any other ops on the stack class anywhere else
		machine_tasks.push(">SHUTDOWN");
		machine_tasks.push(">Calibrate Offset");
		machine_tasks.push(">Pause(300pcs)");
		}catch(IllegalArgumentException e) {
			System.out.println("Error: " + e);//Probably remove this later but keep for now
		}
	}
	
	/**
	 * Returns all of the data in the automation stack
	 * @return
	 */
	public String importMachineAutomation() {
		String temp;//Used to hold the imported data
		temp = machine_tasks.dump();//Returns the whole stack separated by newlines
		
		if(machine_tasks.dump() == "") {
			return ">[EMPTY]";//If there is nothing in the stack return empty as the data
		}
		
		return temp;
	}
	
	/**
	 * Pops the item on the stack will be linked to the sub button in viewer
	 */
	public void removeTask() {
		try {
		machine_tasks.pop();//Remove the item on the top of the stack
		}catch(IndexOutOfBoundsException e) {
			//System.out.println(e);//Debug
		}
	}
	
	/**
	 * Pushed the new task to the stack if it is not full
	 * @param new_task
	 * @return
	 */
	public boolean addTask(String new_task) {
		String temp;//Holds our new string that doesn't contain newlines
		temp = new_task.replaceAll("\\n", " ");//Replace any newlines from the text box with a space
		
		try {
		machine_tasks.push(">" + temp);//Add a new task
		}catch(IndexOutOfBoundsException e) {
			return false;//Return false to the button that we cannot add more data
		}
		return true;//Return true if there is room for a new task
	}
	
	/**
	 * Returns true if there is room on the stack - never actually used this functionality
	 * @return
	 */
	public boolean isRoom() {
		if(machine_tasks.isFull()) {
			return false;
		}
		return true;//Return true if there is room for a new task
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Gcode Viewer
	private void loadGcodeData() {
		gcode_list = new MinHeap();//Create the heap for the gcode data
		gcode_list.addItem(1,"Top Priority");//Dummy values
		gcode_list.addItem(2,"Med Priority");
		gcode_list.addItem(3,"Low Priority");
	}
	
	public String importGcodeList() {//If we are told ThiS iS NoT HOw iTS SupPoSed tO bE, then just change dump() to get min
		String temp;//Used to hold the data dumped by the heap
		temp = gcode_list.dump();//Save the dumped data
		if(temp == "") {
			return ">[EMPTY]";
		}
		
		return ">" + temp;//Return the dumped data with an arrow indicator for the top priority item
	}
	
	public boolean validatePriority(String data) {//Check if the user entered an integer
		
		if(data.matches("[1]?[0-9]?[0-9]")) {
			return true;//Return true if we have a semi-valid number, will become fully valid in formulatePriority();
		}
		
		return false;//return false if we didn't match the regex
	}
	
	private int formulatePriority(String data) {
		return Integer.parseInt(data);//Return the parsed data
	}
	
	public void addGCode(String pri, String data) {
		String temp;//Holds our new string that doesn't contain newlines
		temp = data.replaceAll("\\n", " ");//Replace any newlines from the text box with a space
		
		try {
		gcode_list.addItem(formulatePriority(pri), temp);//add the 
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);//Probably don't need this printout -- probably remove later
		}
	}
	
	public void removeGCode() {
		try {
			gcode_list.pullMin();//We could return the return of this but we'll simply ignore the output for now
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);//Probably don't need this printout -- probably remove later
		}
	}
	
	public boolean isRoomG() {
		if(machine_tasks.isFull()) {
			return false;
		}
		return true;//Return true if there is room for a new task
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
