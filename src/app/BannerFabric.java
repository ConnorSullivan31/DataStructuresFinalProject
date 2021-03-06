/**
 * 
 */
package app;

import app.FileIO.AccessMode;

/**
 * @author ConnorSullivan31
 *
 */
public class BannerFabric {

	private CircularLL<String> system_messages;//Used to hold messages for the system banner
	private String filename;//Used to hold the name of the file to store the linked list
	
	/**
	 * Ctor
	 */
	public BannerFabric() {
		loadSystemBanner();//Create the linked list and load data
	}
	/**
	 * Loads messages from the doubly circular linked list
	 */
	private void loadSystemBanner() {
		system_messages = new CircularLL<>();
		filename = "StatsBanner.dat";//Hardcode the name of the file to save the data to
		loadData();//Load in the data to the linked list from the file
	}
	
	/**
	 * Used to pass data to the viewer -- viewer may look like it starts at second item, but
	 * that is because the timer starts before the gui pops up
	 * @return
	 */
	public String importSystemBanner() {
		String temp;//Used to hold the next status message
		temp = system_messages.getNext();//Get the next message
		
		if(temp == null) {//Check if the list is empty -- We have to check for null here because of how we implemented our linked list, we can check for an empty string "" on the other two structure that have a dump feature
			return "[EMPTY]";//Return that our message list is empty
		}
		
		return temp;//Return the next message in the list
	}
	
	/**
	 * Method to determine whether the data contains whitespace or not -- used for input validation
	 * @param data
	 * @return
	 */
	public boolean isSolelyWhitespace(String data) {
		if(data.matches("[\\s]+")) {
			return true;//Return that the string is only whitespace
		}
		return false;//Return that the data is not just whitespace
	}
	
	/**
	 * Adds a new data item to the doubly circular linked list
	 * @param msg
	 */
	public void addMessage(String msg) {
		String temp;//Holds our new string that doesn't contain newlines
		temp = msg.replaceAll("\\n", " ");///Replace any newlines from the text box with a space
		
		system_messages.addItemImmediate(temp + "\n");//Add a message to the linked list -- re-add that newline that we removed earlier
	}
	
	/**
	 * Remove the currently indexed item for the DC linked list
	 */
	public void removeMessage() {
		try {
			system_messages.deleteCurrentItem();//Remove the current item
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);//Probably remove this later but keep for now
		}
	}
	
	/**
	 * Loads data from the corresponding .dat file into the DC Linked List
	 */
	private void loadData() {
		String data = "";//Used to hold the data from the file data dump
		String new_item = "";//Used to hold a single data item
		FileIO file = new FileIO(filename,AccessMode.INPUT);//Open the file
		data = file.loadData();//Load in the filedata
		//System.out.println("Data SAtring" + data);//Debug file data
		for(int i = 0; i < data.length(); i++) {
			new_item  += data.charAt(i);//Save the current character to the new item
			if(data.charAt(i) == '\n' && i !=0) {//Don't add entry if the first item is a newline -- should never happen
				//System.out.println("New Item: " + new_item);//Debug
				system_messages.addItemImmediate(new_item);//If we reach a newline, we know that we are at a new item due to how we parse and save memory
				new_item = "";//Reset new item to empty
				continue;//Get next charachter
			}
		}
		
	}
	
	/**
	 * Saves the contents currently in the DC LL to its respective .dat file
	 */
	public void saveData() {
		FileIO file = new FileIO(filename,AccessMode.OUTPUT);//Open the file
		file.saveData(system_messages.dumpMemory());//Write the circular linked list data to the file
	}
	
}
