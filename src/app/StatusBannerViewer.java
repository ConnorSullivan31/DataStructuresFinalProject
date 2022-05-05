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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * @author ConnorSullivan31
 *
 */
public class StatusBannerViewer {

	private BorderPane status_banner_layout;//Main layout for this viewer
	private Label status_banner_label;//Holds the title for the machine status banner in the top of the BorderPane
	private TextArea status_banner_display;//Holds the text view for the machine status
	private HBox button_layout;//Layout for our buttons
	private Button add_button, sub_button;//Plus and minus buttons for the banner
	private ModelViewInterconnect system_messages_list;//Access the controller that will get the data from our circularly linked list class
	private Timeline system_message_timer;//Timer that dictates when messages should rotate
	private double timer_interval = 3000;//Set the timer interval to 3 seconds
	
	 StatusBannerViewer() {//Note comments here don't pertain to allocating the memory but what each object will do - kind of
		status_banner_layout = new BorderPane();//Create the layout for the machine status banner
		status_banner_label = new Label();//Set the title for the machine status banner in the top of the BorderPane
		status_banner_display = new TextArea();//Set the viewing output area for the machine status
		button_layout = new HBox();//Set layout for our buttons
		add_button = new Button();//Set the button label for the plus button
		sub_button = new Button();//Set the button label for the minus button
		system_messages_list = new ModelViewInterconnect();//Gets the next system message so we can display it
		system_message_timer = new Timeline(new KeyFrame(Duration.millis(timer_interval), event -> {
			status_banner_display.setText(system_messages_list.ManageSystemBanner());//Display the next message - Called every 3 seconds
		}));//This is our version of a timer
		setupViewer();//Init the values for viewer
	}
	 
	public BorderPane getViewer() {
		return status_banner_layout;//Return the main layout
	}
	
	private void setupViewer() {
		//Label
		status_banner_label.setText("Machine Status");//Set the label text
		status_banner_layout.setTop(status_banner_label);//Put the label at the top
		//Text Area
		status_banner_display.setPrefSize(400,50);//Set the size of this area
		status_banner_display.setWrapText(true);
		status_banner_display.setEditable(false);
		status_banner_layout.setCenter(status_banner_display);//Set the display to the middle
		//Buttons
		add_button.setText("+");//Set the text for the add button
		add_button.setOnAction(event -> {
			//add banner timer pause here
			System.out.println("Status Banner Add Button Pushed.");
		});//Will need to change this eventually to edit data
		add_button.setPrefSize(64, 16);
		
		sub_button.setText("-");//Set the text for the sub button
		sub_button.setOnAction(event -> {
			//Add remove system message here
			System.out.println("Status Banner Sub Button Pushed.");//Debug
		});//Will need to change this eventually to edit data
		sub_button.setPrefSize(64, 16);
		//Button Layout
		button_layout.getChildren().addAll(add_button, sub_button);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
		status_banner_layout.setBottom(button_layout);//Set the buttons to be displayed on the bottom of the border pane
		//Timer
		system_message_timer.setCycleCount(Animation.INDEFINITE);
		system_message_timer.play();//Start Timer
	}
}
//Notes:
//system_message_timer.pause();//Pause Timer  --- Use this for our button