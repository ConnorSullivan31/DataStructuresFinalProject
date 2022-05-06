/**
 * 
 */
package app;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author ConnorSullivan31
 *
 */
public class MachineAutomatorViewer {

	private BorderPane automator_layout;//Main layout for this viewer
	private Label automator_viewer_label;//Holds the title for the machine automator panel in the top of the border pane
	private TextArea automator_display;//Holds the viewing output area for the machine tasks
	private HBox button_layout;//Holds the layout for out buttons
	private Button add_button, sub_button;//Plus and minus buttons for the machine automator
	private ModelViewInterconnect automation_tasks;//Access the controller that will get the data from our stack class
	private boolean is_editing;//Holds wether the user is entering data into the text area
	
	public MachineAutomatorViewer() {
		automator_layout = new BorderPane();//Create the border pane
		automator_viewer_label = new Label();//Create the label 
		automator_display = new TextArea();//Create the text area
		button_layout = new HBox();//Create the button layout
		add_button = new Button();//Create the add button
		sub_button = new Button();//Create the sub button
		automation_tasks = new ModelViewInterconnect();//Creates the link between model and view
		is_editing = false;//Start that the user is not editing data
		
		setupViewer();//Init values
	}
	
	public BorderPane getViewer() {
		return automator_layout;//Return the main layout
	}
	
	private void setupViewer() {
		//Label
		automator_viewer_label.setText("Automator Tasks");
		automator_layout.setTop(automator_viewer_label);//Put the label at the top
		//TextArea
		automator_display.setText(automation_tasks.getMachineAutomation());//Init the display to the current stack contents
		automator_display.setPrefSize(200,200);//Set the size of this area
		automator_display.setWrapText(true);
		automator_display.setEditable(false);
		automator_layout.setCenter(automator_display);//Set the display to the middle
		//Buttons
		add_button.setText("+");
		add_button.setOnAction(event -> {
			//maybe add a timer pause here
			if(automation_tasks.isRoom() && is_editing == false) {//If there is room, go ahead with allowing the user to enter input
				is_editing = true;//Set that the user is now in edit mode
				add_button.setText("Save");
				sub_button.setText("Cancel");
				automator_display.requestFocus();//Request that we set the focus to this window
				automator_display.clear();//Set the text field to empty so the user can enter data -- we could also just use setText("");
				automator_display.setPromptText("Enter Task Here. . .");
				automator_display.setEditable(true);//Allow the user to type data in the text field
			}else if(is_editing) {
				is_editing = false;//Turn of edit mode if the user clicks the save button while in edit mode
				add_button.setText("+");//Reset Buttons
				sub_button.setText("-");//Reset Buttons
				automator_display.setEditable(false);//Disable editing of the field
				if(automator_display.getText().length() > 0) {//Only add task if it is not empty
					automation_tasks.addTask(automator_display.getText());
				}
				automator_display.setText(automation_tasks.getMachineAutomation());//Load back in the stack
				automator_display.setPromptText("");//Don't set prompt text if there are no items in the stack
			}
			
		});//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");
		sub_button.setOnAction(event -> {
			//maybe add more actions here
			if(is_editing) {
				is_editing = false;//Set that we wanted to cancel editing
				add_button.setText("+");
				sub_button.setText("-");
				automator_display.setText(automation_tasks.getMachineAutomation());//Update the display of the current stack contents
			}else {
				automation_tasks.removeTask();//Remove the top task from the list
				automator_display.setText(automation_tasks.getMachineAutomation());//Update the display of the current stack contents
			}
			
		});//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		automator_layout.setBottom(button_layout);//Set the buttons to be displayed on the bottom of the border pane
	}
	
	public void updateView(String data) {
		automator_display.setText(data);//Update the text area with new data
	}
}
//Notes:
//Maybe add a timer here?
