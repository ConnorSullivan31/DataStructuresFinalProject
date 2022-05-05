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
public class GcodeViewer {

	private BorderPane gcode_layout;//Main layout for this viewer
	private Label gcode_viewer_label;//Holds the title for the gcode viewer panel in the top of the border pane
	private TextArea gcode_display;//Holds the viewing ouptut area for the gcode
	private HBox button_layout;//Holds the layout for our buttons
	private Button add_button, sub_button;//Plus and minus buttons for the gcode viewer
	
	public GcodeViewer() {
		gcode_layout = new BorderPane();//Create border pane
		gcode_viewer_label = new Label();//Create the label
		gcode_display = new TextArea();//Create the text area
		button_layout = new HBox();//Create the button layout
		add_button = new Button();//Create the add button
		sub_button = new Button();//Create the sub button
		
		setupViewer();//Init values
	}
	
	public BorderPane getViewer() {
		return gcode_layout;//Return the main layout
	}
	
	private void setupViewer() {
		//Label
		gcode_viewer_label.setText("G-Code Viewer");
		gcode_layout.setTop(gcode_viewer_label);//Put the label at the top
		//TextArea
		gcode_display.setPrefSize(200,200);//Set the size of this area
		gcode_display.setWrapText(true);
		gcode_display.setEditable(false);
		gcode_layout.setCenter(gcode_display);//Set the display to the middle
		//Buttons
		add_button.setText("+");
		add_button.setOnAction(event -> {
			//maybe add timer pause here if we need a timer for this class
			System.out.println("Gcode Add Button Pushed.");
		});//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");
		sub_button.setOnAction(event -> System.out.println("GCode Sub Button Pushed."));//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		gcode_layout.setBottom(button_layout);//Set the buttons to be displayed on the bottom of the border pane
	}
}

//Note:
//Maybe add a timer to this class. I'm not totally sure yet