/**
 * 
 */
package app;

import app.FileIO.AccessMode;

/**
 * @author ConnorSullivan31
 *
 */
public class AutomatorFabric {

		private Stack machine_tasks;//Used to hold the current automation tasks for the machine automator
		private String filename;//Used to hold the filename where we'll store and read data
		
	AutomatorFabric(){
		loadMachineAutomator();//Create the stack and load dummy data
	}
	
		private void loadMachineAutomator() {
			machine_tasks = new Stack();//Create the task stack
			filename = "AutomatorTasks.dat";//Create the filename to read and write to 
			loadData();//Load in the data from the file
		}
		
		/**
		 * Returns all of the data in the automation stack
		 * @return
		 */
		public String importMachineAutomation() {
			String temp;//Used to hold the imported data
			temp = machine_tasks.dumpMemory();//Returns the whole stack -- Top to bottom order
			
			if(machine_tasks.dumpMemory() == "") {
				return ">[EMPTY]";//If there is nothing in the stack return empty as the data
			}
			
			return temp;
		}
		
		
		public boolean isSolelyWhitespace(String data) {
			if(data.matches("[\\s]+")) {
				return true;//Return that the string is only whitespace
			}
			return false;//Return that the data is not just whitespace
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
			machine_tasks.push(">" + temp + "\n");//Add a new task -- re-add the newline that we removed earlier
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
		
		
		private void loadData() {
			String data = "";//Used to hold the data from the file data dump
			String new_item = "";//Used to hold a single data item
			FileIO file = new FileIO(filename,AccessMode.INPUT);//Open the file
			data = file.loadData();//Load in the filedata
			//System.out.println("Data SAtring" + data);//Debug file data
			for(int i = 0; i < data.length(); i++) {
				new_item  += data.charAt(i);//Save the current character to the new item
				if(data.charAt(i) == '\n' && i !=0) {//Don't add a new entry if we start with a newline -- shouldn't happen anyway
					System.out.println("New Item: " + new_item);//Debug
					machine_tasks.push(new_item);//If we reach a newline, we know that we are at a new item due to how we parse and save memory
					new_item = "";//Reset new item to empty
					continue;//Get next charachter
				}
			}
			
		}
		
		public void saveData() {
			FileIO file = new FileIO(filename,AccessMode.OUTPUT);//Open the file
			file.saveData(machine_tasks.exportMemory());//Write the stack data to the file -- bottom to top
		}
		
	
}
