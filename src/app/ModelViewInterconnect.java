/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class ModelViewInterconnect {
	
	private CircularLL<String> system_messages;//Used to hold messages for the system banner
	private String current_message;
	public ModelViewInterconnect() {
		system_messages = new CircularLL<>();
		system_messages.addItem("POWER = ON");//Eventually replace these with file input
		system_messages.addItem("OIL LEVEL = OK");
		system_messages.addItem("COOLANT LEVEL = HGIH");
	}
	
	public String ManageSystemBanner() {
		current_message = system_messages.getNext();
		//System.out.println(current_message);//Debug
		return current_message;
	}
}
