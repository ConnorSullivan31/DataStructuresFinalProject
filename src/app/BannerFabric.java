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
	
	private void loadSystemBanner() {
		system_messages = new CircularLL<>();
		filename = "StatsBanner.dat";//Hardcode the name of the file to save the data to
		loadData();//Load in the data to the linked list from the file
		
		/*system_messages.addItemImmediate("1:POWER = ON");//Eventually replace these with file input
		system_messages.addItemImmediate("2:OIL LEVEL = OK");
		system_messages.addItemImmediate("3:COOLANT LEVEL = HGIH");*/
	}
	
	public String importSystemBanner() {
		String temp;//Used to hold the next status message
		temp = system_messages.getNext();//Get the next message
		
		if(temp == null) {//Check if the list is empty -- We have to check for null here because of how we implemented our linked list, we can check for an empty string "" on the other two structure that have a dump feature
			return "[EMPTY]";//Return that our message list is empty
		}
		
		return temp;//Return the next message in the list
	}
	
	public boolean isSolelyWhitespace(String data) {
		if(data.matches("[\\s]+")) {
			return true;//Return that the string is only whitespace
		}
		return false;//Return that the data is not just whitespace
	}
	
	public void addMessage(String msg) {
		String temp;//Holds our new string that doesn't contain newlines
		temp = msg;/*.replaceAll("\\n", " ");*///Replace any newlines from the text box with a space
		
		system_messages.addItemImmediate(temp);//Add a message to the linked list
	}
	
	public void removeMessage() {
		try {
			system_messages.deleteCurrentItem();//Remove the current item
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);//Probably remove this later but keep for now
		}
	}
	
	private void loadData() {
		String data = "";//Used to hold the data from the file data dump
		String new_item = "";//Used to hold a single data item
		FileIO file = new FileIO(filename,AccessMode.INPUT);//Open the file
		data = file.loadData();//Load in the filedata
		System.out.println("Data SAtring" + data);//Debug file data
		for(int i = 0; i < data.length(); i++) {
			if(data.charAt(i) == '\n' && i !=0 && i != (data.length()-1)) {//Only add the entry if we have a newline in the middle of the string
				System.out.println("New Item: " + new_item);
				system_messages.addItemImmediate(new_item);//If we reach a newline, we know that we are at a new item due to how we parse and save memory
				new_item = "";//Reset new item to empty
				continue;//Get next charachter
			}
			new_item  += data.charAt(i);//Save the current character to the new item
		}
		
	}
	
	public void saveData() {
		FileIO file = new FileIO(filename,AccessMode.OUTPUT);//Open the file
		file.saveData(system_messages.dumpMemory());//Write the circular linked list data to the file
	}
	
	/*public boolean isEmpty() {
		return system_messages.isEmpty();
	}*/
	
}
