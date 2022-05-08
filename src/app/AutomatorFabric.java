/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class AutomatorFabric {

		private Stack machine_tasks;//Used to hold the current automation tasks for the machine automator
	
	AutomatorFabric(){
		loadMachineAutomator();//Create the stack and load dummy data
	}
	
		private void loadMachineAutomator() {
			machine_tasks = new Stack();//Create the task stack
			try {//We don't need this here but it is kept as an example when doing any other ops on the stack class anywhere else
			machine_tasks.push(">SHUTDOWN");
			machine_tasks.push(">Calibrate Offset");//Replace these with file input
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
	
}
