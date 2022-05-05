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
	
	public MachineAutomatorViewer() {
		automator_layout = new BorderPane();//Create the border pane
		automator_viewer_label = new Label();//Create the label 
		automator_display = new TextArea();//Create the text area
		button_layout = new HBox();//Create the button layout
		add_button = new Button();//Create the add button
		sub_button = new Button();//Create the sub button
		
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
		automator_display.setPrefSize(200,200);//Set the size of this area
		automator_display.setWrapText(true);
		automator_display.setEditable(false);
		automator_layout.setCenter(automator_display);//Set the display to the middle
		//Buttons
		add_button.setText("+");
		add_button.setOnAction(event -> {
			//maybe add a timer pause here
			System.out.println("Machine Automator Add Button Pushed.");
		});//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");
		sub_button.setOnAction(event -> {
			//maybe add more actions here
			System.out.println("Machine Automator Sub Button Pushed.");
		});//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		automator_layout.setBottom(button_layout);//Set the buttons to be displayed on the bottom of the border pane
	}
}
//Notes:
//Maybe add a timer here?
