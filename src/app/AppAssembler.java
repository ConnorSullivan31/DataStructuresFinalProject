/**
 * 
 */
package app;


//Testing Imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author ConnorSullivan31
 *
 */
public class AppAssembler extends Application {

	private Stage window;//Main window for our program
	
	private StatusBannerViewer status_banner_viewer;//Object what will generate the layout for the status bar
	private GcodeViewer gcode_viewer;//Object that will generate the layout for the gcode viewer
	private MachineAutomatorViewer automator_viewer;//Object that will generate the layout for the machine automator
	private LoadMeterViewer load_meter_viewer;//Object that will generate the layout for the machine automator
	
	public AppAssembler() {
		//Nothing to really do here

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
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Creating these layouts has to be done in start because that is the main thread run by the gui.
			//If we wanted to do something else with them outside of the start method we would have to use the runLater() functionality so that the task gets
			//put into a queue that the gui thread will get to and manage as often as it can.
			status_banner_viewer = new StatusBannerViewer();//Create the status bar viewer
			gcode_viewer = new GcodeViewer();//Create the gcode viewer
			automator_viewer = new MachineAutomatorViewer();//Create the machine automator viewer
			load_meter_viewer = new LoadMeterViewer();//Create the load meter viewer
			/////End layout object mem alloction
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			window = primaryStage;//Create the window
			window.setMinWidth(400);//Set the min width to 400 pixels
			//This is the master layout and how we will arrange everything in our window
			GridPane master_layout = new GridPane();//Create the master layout for our window
			master_layout.setPadding(new Insets(10,10,10,10));//Set spacing between items in layout and the edge of the window
			master_layout.setVgap(16);//Set spacing in between items to 16 pixels
			master_layout.setHgap(16);//Set spacing in between items to 16 pixels
			
			ColumnConstraints column1, column2;//Our 3 display columns
			column1 = new ColumnConstraints();
			column1.setPercentWidth(50);
			column2 = new ColumnConstraints();
			column2.setPercentWidth(50);
			master_layout.getColumnConstraints().addAll(column1,column2);//Use sizing
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Gcode viewer
			
			GridPane.setConstraints(gcode_viewer.getViewer(), 1, 1);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			GridPane.setRowSpan(gcode_viewer.getViewer(), 2);//Span the gcode down to cover the gcode and the load meter row
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			//This BorderPane will be for our machine automator viewer (requirement #2)
			
			GridPane.setConstraints(automator_viewer.getViewer(), 0, 1);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Status banner viewer
			
			GridPane.setConstraints(status_banner_viewer.getViewer(), 0, 0);//Tell the master layout where out sublayout/viewer should sit in the window is arranged in an array format
			GridPane.setColumnSpan(status_banner_viewer.getViewer(), 2);//Make it so that the status banner spans both the gcode column and the automator column

			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//This BorderPane will be for our machine load meter (requirement #4)
			

			GridPane.setConstraints(load_meter_viewer.getViewer(), 0, 2);//Tell the master layout where out sublayout should sit in the window is arranged in an array format
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			master_layout.getChildren().addAll(gcode_viewer.getViewer(),automator_viewer.getViewer(), status_banner_viewer.getViewer(), load_meter_viewer.getViewer());//Add all of the items to be displayed
			
			Scene main_view = new Scene(master_layout, 800,600);
			
			window.setScene(main_view);
			window.setTitle("CNC Terminal 1.0");
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
