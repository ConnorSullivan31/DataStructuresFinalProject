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
public class StatusBannerViewer {

	private BorderPane main_layout;//Main layout for this viewer
	private Label status_banner_label;//Holds the title for the machine status banner in the top of the BorderPane
	private TextArea status_banner_display;//Holds the text view for the machine status
	private HBox button_layout;//Layout for our buttons
	private Button add_button, sub_button;//Plus and minus buttons for the banner
	private FabricLoader system_messages_list;//Access the controller that will get the data from our circularly linked list class
	private Timeline system_message_timer;//Timer that dictates when messages should rotate
	private double timer_interval = 3000;//Set the timer interval to 3 seconds
	private boolean is_editing;//Used to hold whether we are in edit mode
	
	private Timeline validation_timer;//Used to check if what the user is entering is valid data
	private double validation_interval = 50;//Set so that we check text validation every 50 milliseconds
	
	/**
	 * Ctor
	 */
	 StatusBannerViewer() {//Note comments here don't pertain to allocating the memory but what each object will do - kind of
		main_layout = new BorderPane();//Create the layout for the machine status banner
		status_banner_label = new Label();//Set the title for the machine status banner in the top of the BorderPane
		status_banner_display = new TextArea();//Set the viewing output area for the machine status
		button_layout = new HBox();//Set layout for our buttons
		add_button = new Button();//Set the button label for the plus button
		sub_button = new Button();//Set the button label for the minus button
		system_messages_list = new FabricLoader();//Create the link between model and view
		system_message_timer = new Timeline(new KeyFrame(Duration.millis(timer_interval), event -> {
			status_banner_display.setText(system_messages_list.linkBanner().importSystemBanner());//Display the next message - Called every 3 seconds
		}));//This is our version of a timer
		
		is_editing = false;//Start out not in edit mode
		
		
		//Timer used to indicate to the user is the data that they are entering is valid
		validation_timer = new Timeline(new KeyFrame(Duration.millis(validation_interval), event -> validateInput()));
		
		
		setupViewer();//Init the values for viewer
	}
	
	/**
	 *Returns the main layout object to be implemented into the parent layout 
	 * @return
	 */
	public BorderPane getViewer() {
		return main_layout;//Return the main layout
	}
	
	/**
	 * Set the default values for this viewer's layout
	 */
	private void setupViewer() {
		//Label
		status_banner_label.setText("Machine Status");//Set the label text
		main_layout.setTop(status_banner_label);//Put the label at the top
		//Text Area
		status_banner_display.setText(system_messages_list.linkBanner().importSystemBanner());//Set initial value
		status_banner_display.setPrefSize(400,50);//Set the size of this area
		status_banner_display.setWrapText(true);
		status_banner_display.setEditable(false);
		main_layout.setCenter(status_banner_display);//Set the display to the middle
		//Buttons
		add_button.setText("+");//Set the text for the add button
		add_button.setOnAction(event -> respondToAddButton());//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");//Set the text for the sub button
		sub_button.setOnAction(event -> respondToSubButton());//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		main_layout.setBottom(button_layout);//Set the buttons to be displayed on the bottom of the border pane
		//Timer
		system_message_timer.setCycleCount(Animation.INDEFINITE);
		system_message_timer.play();//Start Timer
		//Validation Timer
		validation_timer.setCycleCount(Animation.INDEFINITE);
		validation_timer.play();//Start Timer
		
	}
	
	/**
	 * Updates the viewer based on the current edit mode when the user pushes the add button
	 */
	private void respondToAddButton() {
		if(is_editing == false) {//If there is room, go ahead with allowing the user to enter input
			system_message_timer.pause();//Pause the timer
			is_editing = true;//Set that the user is now in edit mode
			add_button.setText("Save");
			sub_button.setText("Cancel");
			status_banner_label.setText("Enter Status Message");
			status_banner_display.requestFocus();//Request that we set the focus to this window
			status_banner_display.clear();//Set the text field to empty so the user can enter data -- we could also just use setText("");
			status_banner_display.setPromptText("Enter Message Here. . .");
			status_banner_display.setEditable(true);//Allow the user to type data in the text field
		}else if(is_editing) {
			system_message_timer.play();//Resume timer
			is_editing = false;//Turn of edit mode if the user clicks the save button while in edit mode
			add_button.setText("+");//Reset Buttons
			sub_button.setText("-");//Reset Buttons
			status_banner_label.setText("Machine Status");
			status_banner_display.setEditable(false);//Disable editing of the field
			if(status_banner_display.getText().length() > 0 && system_messages_list.linkBanner().isSolelyWhitespace(status_banner_display.getText()) == false) {//Only add task if it is not empty or just whitespace
				system_messages_list.linkBanner().addMessage(status_banner_display.getText());
				system_messages_list.linkBanner().saveData();//Save the data in the linked list
			}
			status_banner_display.setText(system_messages_list.linkBanner().importSystemBanner());//Load back in the linked list
			status_banner_display.setPromptText("");//Don't set prompt text if there are no items in the linked list
		}
		
	}
	
	/**
	 * Updates the viewer based on the current edit mode when the user pushes the subtract button
	 */
	private void respondToSubButton() {
		//maybe add more actions here
		if(is_editing) {
			system_message_timer.play();//Resume timer
			is_editing = false;//Set that we wanted to cancel editing
			add_button.setText("+");
			sub_button.setText("-");
			status_banner_label.setText("Machine Status");
			status_banner_display.setText(system_messages_list.linkBanner().importSystemBanner());//Update the display of the current stack contents
		}else {
			system_messages_list.linkBanner().removeMessage();//Remove the top task from the list
			system_messages_list.linkBanner().saveData();//Save data without the just removed item
			status_banner_display.setText(system_messages_list.linkBanner().importSystemBanner());//Update the display of the current stack contents
			//Call a save function here
		}
		
	}
	
	/**
	 * Checks if what the user has input is valid -- don't let them save unless it is valid
	 */
	private void validateInput() {
		if(is_editing) {
			if((status_banner_display.getText().length() > 0 && system_messages_list.linkBanner().isSolelyWhitespace(status_banner_display.getText()) == false) == false) {//Only validate if it is not empty or just whitespace
				status_banner_label.setText("Enter Status Message - (Currently Invalid)");//Change the label to indicate that
				add_button.setOpacity(.20);
				add_button.setDisable(true);//Disable the button until valid input
			}else {
				status_banner_label.setText("Enter Status Message - (Valid)");//Change the label to indicate that
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
//Notes:
//system_message_timer.pause();//Pause Timer  --- Use this for our button
