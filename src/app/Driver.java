/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test 123");
			
		/*CircularLL<String> system_messages = new CircularLL<>();
		system_messages.addItem("POWER = ON");//Eventually replace these with file input
		system_messages.addItem("OIL LEVEL = OK");
		system_messages.addItem("COOLANT LEVEL = HGIH");
		
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());
		system_messages.deleteCurrentItem();
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());
		System.out.println(system_messages.getNext());*/
		
		
		AppAssembler app = new AppAssembler();//Create our app
		app.RunWindow();//Run our app
		
	}

}
