/**
 * 
 */
package app;

//Testing Imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//End Testing Imports


/**
 * @author ConnorSullivan31
 *
 */
public class AppAssembler extends Application{

	public AppAssembler() {
		//Add setup logic here
	}
	
	public void RunWindow() {
		//Put main logic here
				launch();
	}
	
	
	
	//This is here just as a test for now
	
	
	/**
	 * Should spawn a blank window to verify that JavaFX is working
	 */
		@Override
		public void start(Stage primaryStage) {
			try {
				BorderPane root = new BorderPane();
				Scene scene = new Scene(root,400,400);
				
				
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		
		
		
}
