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
import javafx.scene.layout.VBox;

/**
 * @author ConnorSullivan31
 *
 */
public class GcodeViewer {

	private BorderPane main_layout;//Main layout for this viewer
	private Label viewer_label;//Holds the title for the gcode viewer panel in the top of the border pane
	private VBox gcode_view_layout;///Holds the gcode view and the priority entry box
	private TextArea gcode_display;//Holds the viewing ouptut area for the gcode
	private Label gcode_viewer_label;//Holds the label for the gcode in edit mode
	private TextArea priority_display;//Appears when the user adds a new entry so they can enter priority
	private TextArea completed_display;//Holds all of the gcode that has been pulled off of the min heap
	private Label completed_label;//Holds the title for the completed gcode display
	private HBox button_layout;//Holds the layout for our buttons
	private Button add_button, sub_button;//Plus and minus buttons for the gcode viewer
	private ModelViewInterconnect gcode_data;//Used to interface with the min heap priority queue for the gcode
	private boolean is_editing;//Used to hold whether the user is editing data or not
	
	public GcodeViewer() {
		main_layout = new BorderPane();//Create border pane
		viewer_label = new Label();//Create the label
		gcode_view_layout = new VBox();//Create the layout for the priority display and the gcode view
		gcode_display = new TextArea();//Create the text area
		gcode_viewer_label = new Label();//Create the label
		priority_display = new TextArea();//Create the text area
		button_layout = new HBox();//Create the button layout
		add_button = new Button();//Create the add button
		sub_button = new Button();//Create the sub button
		completed_display = new TextArea();//Create the text area
		completed_label = new Label();//Create the label
		
		gcode_data = new ModelViewInterconnect();//Create the controller interconnect
		is_editing = false;//Start out with the user not editing data
		
		setupViewer();//Init values
	}
	
	public BorderPane getViewer() {
		return main_layout;//Return the main layout
	}
	
	private void setupViewer() {
		//Label -- Gcode label in view mode, priority label in edit mode
		viewer_label.setText("G-Code Viewer");
		main_layout.setTop(viewer_label);//Put the label at the top
		//TextArea -- Priority
		priority_display.setPrefSize(200,25);//Set the size of this area
		priority_display.setWrapText(true);
		priority_display.setEditable(false);
		//Label -- Gcode label in edit mode
		gcode_viewer_label.setText("Enter G-Code");
		//TextArea -- Gcode data
		gcode_display.setText(gcode_data.importGcodeList());
		gcode_display.setPrefSize(200,50);//Set the size of this area
		gcode_display.setWrapText(true);
		gcode_display.setEditable(false);
		//Layout -- Holds the priority text field, the gcode edit label, and the gcode field
		gcode_view_layout.getChildren().add(gcode_display);//Add the gcode display to the vbox
		main_layout.setCenter(gcode_view_layout);//Set the vbox for our view to the middle
		//Buttons
		add_button.setText("+");
		add_button.setOnAction(event -> respondToAddButton());//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");
		sub_button.setOnAction(event -> respondToSubButton());//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		gcode_view_layout.getChildren().add(button_layout);//Set the buttons to be displayed on the bottom of the vbox
		//Label
		completed_label.setText("Completed G-Code");
		gcode_view_layout.getChildren().add(completed_label);//Add the label to the display under the buttons
		//Text Area -- Completed gcode display
		completed_display.setText(gcode_data.importCompletedCode());
		completed_display.setPrefSize(200,150);//Set the size of this area
		completed_display.setWrapText(true);
		completed_display.setEditable(false);
		main_layout.setBottom(completed_display);//Set the buttons to be displayed on the bottom of the border pane
	}
	
	private void respondToAddButton() {
		//maybe add a timer pause here
		if(gcode_data.isRoomG() && is_editing == false) {//If there is room, go ahead with allowing the user to enter input
			is_editing = true;//Set that the user is now in edit mode
			add_button.setText("Save");
			sub_button.setText("Cancel");
			viewer_label.setText("Set G-Code Priority");//Change the top label to label the priority text field
			gcode_view_layout.getChildren().clear();//Clear the vbox for a redraw
			gcode_view_layout.getChildren().addAll(priority_display,gcode_viewer_label,gcode_display,button_layout,completed_label);//Add the priority text field, the gcode label, the gcode display, the button layout, and the completed label to the vbox layout in that order
			
			priority_display.requestFocus();//Request that we set the focus to this window
			priority_display.clear();//Set the text field to empty so the user can enter data -- we could also just use setText("");
			priority_display.setPromptText("Enter G-Code Priority Here. . .");
			priority_display.setEditable(true);//Allow the user to type data in the text field
			
			gcode_display.clear();//Set the text field to empty so the user can enter data -- we could also just use setText("");
			gcode_display.setPromptText("Enter G-Code Here. . .");
			gcode_display.setEditable(true);//Allow the user to type data in the text field
		}else if(is_editing) {
			is_editing = false;//Turn of edit mode if the user clicks the save button while in edit mode
			add_button.setText("+");//Reset Buttons
			sub_button.setText("-");//Reset Buttons
			viewer_label.setText("G-Code Viewer");//Restore the main label
			gcode_view_layout.getChildren().clear();//Clear the vbox for a redraw
			gcode_view_layout.getChildren().addAll(gcode_display,button_layout, completed_label);//Add the gcode display, button layout, and completed label back to the vbox layout
			gcode_display.setEditable(false);//Disable editing of the field
			if(gcode_display.getText().length() > 0 && priority_display.getText().length() > 0) {//Only add if priority and gcode are both filled out
				if(gcode_data.validatePriority(priority_display.getText())) {//If the priority string matches the regex conditons for 1-100, then go ahead and add
				gcode_data.addGCode(priority_display.getText(),gcode_display.getText());//Get the text from the priority field, get the text from the gcode field
				//Call a save function here
				}
			}
			gcode_display.setText(gcode_data.importGcodeList());//Load back in the heap
			gcode_display.setPromptText("");//Don't set prompt text if there are no items in the heap
		}
		
	}
	
	private void respondToSubButton() {
		//maybe add more actions here
		if(is_editing) {
			is_editing = false;//Set that we wanted to cancel editing
			add_button.setText("+");
			sub_button.setText("-");
			viewer_label.setText("G-Code Viewer");//Restore the main label
			gcode_view_layout.getChildren().clear();//Clear the vbox for a redraw
			gcode_view_layout.getChildren().addAll(gcode_display,button_layout, completed_label);//Add the gcode display, button layout, and completed label back to the vbox layout
			gcode_display.setText(gcode_data.importGcodeList());//Update the display of the current heap contents
		}else {
			//System.out.println("Button pushed");//Debug
			gcode_data.removeGCode();//Remove the top task from the list
			gcode_display.setText(gcode_data.importGcodeList());//Update the display of the current heap contents
			completed_display.setText(gcode_data.importCompletedCode());//Update the display of the already pulled heap contents
			//Call a save function here
		}
	}	
}

//Note:
//Maybe add a timer to this class. I'm not totally sure yet

//main_layout.getChildren().remove(gcode_display);//This is an example of how to remove the main gcode text box