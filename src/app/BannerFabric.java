/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class BannerFabric {

	private CircularLL<String> system_messages;//Used to hold messages for the system banner
	
	/**
	 * Ctor
	 */
	public BannerFabric() {
		loadSystemBanner();//Create the linked list and load data
	}
	
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
	
}
