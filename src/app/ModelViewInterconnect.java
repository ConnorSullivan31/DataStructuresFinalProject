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
	
	/**
	 * Ctor
	 */
	public ModelViewInterconnect() {
		loadSystemBanner();//Create and load memory for the system banner
		loadMachineAutomator();//Create and load memory for the machine automator
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//System Banner
	private void loadSystemBanner() {
		system_messages = new CircularLL<>();
		system_messages.addItemImmediate("1:POWER = ON");//Eventually replace these with file input
		system_messages.addItemImmediate("2:OIL LEVEL = OK");
		system_messages.addItemImmediate("3:COOLANT LEVEL = HGIH");
	}
	
	public String manageSystemBanner() {
		return system_messages.getNext();
	}
	
	public void addMessage(String msg) {
		system_messages.addItemImmediate(msg);//Add a message to the linked list
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
	public String getMachineAutomation() {
		//System.out.println(machine_tasks.dump());//Debug
		return machine_tasks.dump();//Returns the whole stack separated by newlines
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
