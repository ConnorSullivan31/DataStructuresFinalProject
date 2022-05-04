/**
 * 
 */
package app;

//Testing Imports
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
//End Testing Imports

/**
 * @author ConnorSullivan31
 *
 */
public class AppAssembler extends Application {

	Stage window;//Main window for our program
	
	public AppAssembler() {
		// Add setup logic here
	}

	public void RunWindow() {
		// Put main logic here
		launch();
	}

	// This is here just as a test for now

	/**
	 * Should spawn a blank window to verify that JavaFX is working
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;//Create the window
			window.setMinWidth(400);//Set the min width to 400 pixels
			//This is the master layout and how we will arrange everything in our window
			GridPane master_layout = new GridPane();//Create the master layout for our window
			master_layout.setPadding(new Insets(10,10,10,10));//Set spacing between items in layout and the edge of the window
			master_layout.setVgap(16);//Set spacing in between items to 16 pixels
			master_layout.setHgap(16);//Set spacing in between items to 16 pixels
			
			ColumnConstraints column1, column2, column3;//Our 3 display columns
			column1 = new ColumnConstraints();
			column1.setPercentWidth(45);
			column2 = new ColumnConstraints();
			column2.setPercentWidth(45);
			column3 = new ColumnConstraints();
			column3.setPercentWidth(10);
			master_layout.getColumnConstraints().addAll(column1,column2,column3);//Use sizing
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			//This BorderPane will be for our gcode viewer (requirement #1)
			BorderPane gcode_layout = new BorderPane();//Create the layout for the gcode viewer
			GridPane.setConstraints(gcode_layout, 0, 1);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			
			Label gcode_viewer_label = new Label("G-Code Viewer");//Set the title for the gcode viewer in the top of border pane
			gcode_layout.setTop(gcode_viewer_label);//Put the label at the top
			
			TextArea gcode_display = new TextArea();//Set the viewing output area for the gcode
			gcode_display.setPrefSize(200,200);//Set the size of this area
			gcode_display.setWrapText(true);
			gcode_display.setEditable(false);
			gcode_layout.setCenter(gcode_display);//Set the display to the middle
			
			HBox gc_button_layout = new HBox();//Layout for our buttons
			Button gc_add_btn = new Button("+");
			gc_add_btn.setOnAction(event -> System.out.println("Gcode Add Button Pushed."));//Will need to change this eventually to edit data
			gc_add_btn.setPrefSize(64, 16);
			Button gc_sub_btn = new Button("-");
			gc_sub_btn.setOnAction(event -> System.out.println("GCode Sub Button Pushed."));//Will need to change this eventually to edit data
			gc_sub_btn.setPrefSize(64, 16);
			
			gc_button_layout.getChildren().addAll(gc_sub_btn, gc_add_btn);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
			gcode_layout.setBottom(gc_button_layout);//Set the buttons to be displayed on the bottom of the border pane
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			//This BorderPane will be for our machine automator viewer (requirement #2)
			BorderPane automator_layout = new BorderPane();//Create the layout for the machine automator viewer
			GridPane.setConstraints(automator_layout, 1, 1);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			GridPane.setRowSpan(automator_layout, 2);//Span the Automator down to cover the gcode and the load meter row
			
			Label automator_viewer_label = new Label("Automator Tasks");//Set the title for the machine automator in the top of border pane
			automator_layout.setTop(automator_viewer_label);//Put the label at the top
			
			TextArea automator_display = new TextArea();//Set the viewing output area for the machine tasks
			automator_display.setPrefSize(200,200);//Set the size of this area
			automator_display.setWrapText(true);
			automator_display.setEditable(false);
			automator_layout.setCenter(automator_display);//Set the display to the middle
			
			HBox ma_button_layout = new HBox();//Layout for our buttons
			Button ma_add_btn = new Button("+");
			ma_add_btn.setOnAction(event -> System.out.println("Machine Automator Add Button Pushed."));//Will need to change this eventually to edit data
			ma_add_btn.setPrefSize(64, 16);
			Button ma_sub_btn = new Button("-");
			ma_sub_btn.setOnAction(event -> System.out.println("Machine Automator Sub Button Pushed."));//Will need to change this eventually to edit data
			ma_sub_btn.setPrefSize(64, 16);
			
			ma_button_layout.getChildren().addAll(ma_sub_btn, ma_add_btn);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
			automator_layout.setBottom(ma_button_layout);//Set the buttons to be displayed on the bottom of the border pane
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//This BorderPane will be for our machine status banner (requirement #3)
			BorderPane status_banner_layout = new BorderPane();//Create the layout for the machine status banner
			GridPane.setConstraints(status_banner_layout, 0, 0);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			GridPane.setColumnSpan(status_banner_layout, 2);//Make it so that the status banner spans both the gcode column and the automator column
			
			Label status_banner_label = new Label("Machine Status");//Set the title for the machine status banner in the top of border pane
			status_banner_layout.setTop(status_banner_label);//Put the label at the top
			
			TextArea status_banner_display = new TextArea();//Set the viewing output area for the machine status
			status_banner_display.setPrefSize(400,50);//Set the size of this area
			status_banner_display.setWrapText(true);
			status_banner_display.setEditable(false);
			status_banner_layout.setCenter(status_banner_display);//Set the display to the middle
			
			HBox sb_button_layout = new HBox();//Layout for our buttons
			Button sb_add_btn = new Button("+");
			sb_add_btn.setOnAction(event -> System.out.println("Status Banner Add Button Pushed."));//Will need to change this eventually to edit data
			sb_add_btn.setPrefSize(64, 16);
			Button sb_sub_btn = new Button("-");
			sb_sub_btn.setOnAction(event -> System.out.println("Status Banner Sub Button Pushed."));//Will need to change this eventually to edit data
			sb_sub_btn.setPrefSize(64, 16);
			
			sb_button_layout.getChildren().addAll(sb_sub_btn, sb_add_btn);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
			status_banner_layout.setBottom(sb_button_layout);//Set the buttons to be displayed on the bottom of the border pane
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//This BorderPane will be for our machine load meter (requirement #4)
			BorderPane load_meter_layout = new BorderPane();//Create the layout for the machine load meter
			GridPane.setConstraints(load_meter_layout, 0, 2);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			int bar_height = 25;
			double xld = 0.50, yld = 0.25, zld = 0.10, sld = 0.90;//Dummy loads for now
			
			Label load_meter_label = new Label("Load Monitoring (%)");//Set the title for the machine load meter in the top of border pane
			load_meter_layout.setTop(load_meter_label);//Put the label at the top
			
			VBox load_bar_layout = new VBox();//Layout for our buttons
			
			
			HBox x_load_layout = new HBox();
			Label x_load_label = new Label("X: ");
			Label x_load_percent = new Label(" " + xld*100 + "%");
			ProgressBar x_load = new ProgressBar();
			x_load.setPrefHeight(bar_height);//Set the preferred height of the progress bar
			x_load.setMaxSize(Double.MAX_VALUE, bar_height);//Set the progress bar to scale bigger if available
			x_load.setProgress(xld);//Set how full the bar is
			HBox.setHgrow(x_load, Priority.ALWAYS);//Set the layout to always scale the progress bar first
			x_load_layout.getChildren().addAll(x_load_label,x_load,x_load_percent);//Load in order of axis label, progress bar, percent label
			
			HBox y_load_layout = new HBox();
			Label y_load_label = new Label("Y: ");
			Label y_load_percent = new Label(" " + yld*100 + "%");
			ProgressBar y_load = new ProgressBar();
			y_load.setPrefHeight(bar_height);//Set the preferred height of the progress bar
			y_load.setMaxSize(Double.MAX_VALUE, bar_height);//Set the progress bar to scale bigger if available
			y_load.setProgress(yld);//Set how full the bar is
			HBox.setHgrow(y_load, Priority.ALWAYS);//Set the layout to always scale the progress bar first
			y_load_layout.getChildren().addAll(y_load_label,y_load,y_load_percent);//Load in order of axis label, progress bar, percent label
			
			HBox z_load_layout = new HBox();
			Label z_load_label = new Label("Z: ");
			Label z_load_percent = new Label(" " + zld*100 + "%");
			ProgressBar z_load = new ProgressBar();
			z_load.setPrefHeight(bar_height);//Set the preferred height of the progress bar
			z_load.setMaxSize(Double.MAX_VALUE, bar_height);//Set the progress bar to scale bigger if available
			z_load.setProgress(zld);//Set how full the bar is
			HBox.setHgrow(z_load, Priority.ALWAYS);//Set the layout to always scale the progress bar first
			z_load_layout.getChildren().addAll(z_load_label,z_load,z_load_percent);//Load in order of axis label, progress bar, percent label
			
			HBox s_load_layout = new HBox();
			Label s_load_label = new Label("S: ");
			Label s_load_percent = new Label(" " + sld*100 + "%");
			ProgressBar s_load = new ProgressBar();
			s_load.setPrefHeight(bar_height);//Set the preferred height of the progress bar
			s_load.setMaxSize(Double.MAX_VALUE, bar_height);//Set the progress bar to scale bigger if available
			s_load.setProgress(sld);//Set how full the bar is
			HBox.setHgrow(s_load, Priority.ALWAYS);//Set the layout to always scale the progress bar first
			s_load_layout.getChildren().addAll(s_load_label,s_load,s_load_percent);//Load in order of axis label, progress bar, percent label
			
			load_bar_layout.getChildren().addAll(x_load_layout,y_load_layout,z_load_layout,s_load_layout);//Add the two buttons to the horizontal button layout. They will be displayed in the order they are added here.
			load_meter_layout.setBottom(load_bar_layout);//Set the buttons to be displayed on the bottom of the border pane
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Image
			Image logo = new Image("logo.png");//This must be located in /bin as this is where the javafx classpath is located
			ImageView logo_view = new ImageView();
			logo_view.setImage(logo);
			logo_view.setFitWidth(64);//Set the image to fit into 32 pixels
			logo_view.setPreserveRatio(true);//Set so that the image scales correctly
			logo_view.setSmooth(true);//Use better image filtering
	        logo_view.setCache(true);//Cache image to improve performance
			GridPane.setConstraints(logo_view, 2, 0);
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			master_layout.getChildren().addAll(gcode_layout,automator_layout,status_banner_layout,load_meter_layout,logo_view);//Add all of the items to be displayed
			
			Scene main_view = new Scene(master_layout, 800,600);
			
			window.setScene(main_view);
			window.setTitle("CNC Terminal 1.0");
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
