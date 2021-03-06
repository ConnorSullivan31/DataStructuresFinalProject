/**
 * 
 */
package app;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * @author ConnorSullivan31
 *
 */
public class MachineAutomatorViewer {

	private BorderPane main_layout;//Main layout for this viewer
	private Label automator_viewer_label;//Holds the title for the machine automator panel in the top of the border pane
	private TextArea automator_display;//Holds the viewing output area for the machine tasks
	private HBox button_layout;//Holds the layout for out buttons
	private Button add_button, sub_button;//Plus and minus buttons for the machine automator
	private FabricLoader automation_tasks;//Access the controller that will get the data from our stack class
	private boolean is_editing;//Holds wether the user is entering data into the text area
	
	private Timeline validation_timer;//Used to check if what the user is entering is valid data
	private double validation_interval = 50;//Set so that we check text validation every 50 milliseconds
	
	/**
	 * Ctor
	 */
	public MachineAutomatorViewer() {
		main_layout = new BorderPane();//Create the border pane
		automator_viewer_label = new Label();//Create the label 
		automator_display = new TextArea();//Create the text area
		button_layout = new HBox();//Create the button layout
		add_button = new Button();//Create the add button
		sub_button = new Button();//Create the sub button
		automation_tasks = new FabricLoader();//Creates the link between model and view
		is_editing = false;//Start that the user is not editing data
		
		//Timer used to indicate to the user is the data that they are entering is valid
		validation_timer = new Timeline(new KeyFrame(Duration.millis(validation_interval), event -> validateInput()));
		
		setupViewer();//Init values
	}
	
	/**
	 * Returns the object for the main layout to be used in a larger parent layout in the app assembler
	 * @return
	 */
	public BorderPane getViewer() {
		return main_layout;//Return the main layout
	}
	
	/**
	 * Sets the initial values for this viewer's layout
	 */
	private void setupViewer() {
		//Label
		automator_viewer_label.setText("Automator Tasks");
		main_layout.setTop(automator_viewer_label);//Put the label at the top
		//TextArea
		automator_display.setText(automation_tasks.linkAutomator().importMachineAutomation());//Init the display to the current stack contents
		automator_display.setPrefSize(200,200);//Set the size of this area
		automator_display.setWrapText(true);
		automator_display.setEditable(false);
		main_layout.setCenter(automator_display);//Set the display to the middle
		//Buttons
		add_button.setText("+");
		add_button.setOnAction(event -> respondToAddButton());//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");
		sub_button.setOnAction(event -> respondToSubButton());//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		main_layout.setBottom(button_layout);//Set the buttons to be displayed on the bottom of the border pane
		//Validation Timer
		validation_timer.setCycleCount(Animation.INDEFINITE);
		validation_timer.play();//Start Timer
	}
	
	/**
	 * Updates the view accordingly when the add button is pushed
	 */
	private void respondToAddButton() {
		//maybe add a timer pause here
		if(automation_tasks.linkAutomator().isRoom() && is_editing == false) {//If there is room, go ahead with allowing the user to enter input
			is_editing = true;//Set that the user is now in edit mode
			add_button.setText("Save");
			sub_button.setText("Cancel");
			automator_viewer_label.setText("Enter Automation Task");
			automator_display.requestFocus();//Request that we set the focus to this window
			automator_display.clear();//Set the text field to empty so the user can enter data -- we could also just use setText("");
			automator_display.setPromptText("Enter Task Here. . .");
			automator_display.setEditable(true);//Allow the user to type data in the text field
		}else if(is_editing) {
			is_editing = false;//Turn of edit mode if the user clicks the save button while in edit mode
			add_button.setText("+");//Reset Buttons
			sub_button.setText("-");//Reset Buttons
			automator_viewer_label.setText("Automator Tasks");
			automator_display.setEditable(false);//Disable editing of the field
			if(automator_display.getText().length() > 0 && automation_tasks.linkAutomator().isSolelyWhitespace(automator_display.getText()) == false) {//Only add task if it is not empty or just whitespace
				automation_tasks.linkAutomator().addTask(automator_display.getText());
				automation_tasks.linkAutomator().saveData();//Save the data to the file
			}
			automator_display.setText(automation_tasks.linkAutomator().importMachineAutomation());//Load back in the stack
			automator_display.setPromptText("");//Don't set prompt text if there are no items in the stack
		}
		
	}
	
	/**
	 * Updates the view accordingly if the subtract button is pushed
	 */
	private void respondToSubButton() {
		//maybe add more actions here
		if(is_editing) {
			is_editing = false;//Set that we wanted to cancel editing
			add_button.setText("+");
			sub_button.setText("-");
			automator_viewer_label.setText("Automator Tasks");
			automator_display.setText(automation_tasks.linkAutomator().importMachineAutomation());//Update the display of the current stack contents
		}else {
			automation_tasks.linkAutomator().removeTask();//Remove the top task from the list
			automation_tasks.linkAutomator().saveData();//Save the data without the removed item
			automator_display.setText(automation_tasks.linkAutomator().importMachineAutomation());//Update the display of the current stack contents
			//Call a save function here
		}
		
	}
	
	/**
	 * Checks the users input to see if it is valid -- if not, don't let them save the data
	 */
	private void validateInput() {
		if(is_editing) {
			if((automator_display.getText().length() > 0 && automation_tasks.linkAutomator().isSolelyWhitespace(automator_display.getText()) == false) == false) {//Only validate if it is not empty or just whitespace
				automator_viewer_label.setText("Enter Automation Task - (Currently Invalid)");//Change the label to indicate that
				add_button.setOpacity(.20);
				add_button.setDisable(true);//Disable the button until valid input
			}else {
				automator_viewer_label.setText("Enter Automation Task - (Valid)");//Change the label to indicate that
				add_button.setOpacity(1);
				add_button.setDisable(false);//Enable the button
			}
		}else {
			//No need to change the label to back -- add or sub button will do this
			add_button.setOpacity(1);
			add_button.setDisable(false);//Enable the button
		}
	}
	
}
