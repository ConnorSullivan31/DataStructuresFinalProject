/**
 * 
 */
package app;

/**
 * @author ConnorSullivan31
 *
 */
public class FabricLoader {
	//System Banner
	private BannerFabric banner_innerconnect;//Used to access and manage data for the system message banner
	//Machine Automator
	private AutomatorFabric automator_interconnect;//Used to access and manage data for the machine task automator
	//Gcode Viewer
	private GcodeFabric gcode_interconnect;//Used to access and manage data for the gcode viewer
	

	/**
	 * Ctor
	 */
	public FabricLoader() {
		banner_innerconnect = new BannerFabric();//Create the System Message Banner interconnect
		automator_interconnect = new AutomatorFabric();//Create the Machine Automator interconnect
		gcode_interconnect = new GcodeFabric();//Create the Gcode Viewer interconnect
	}
	
////////////////////
//WARNING: CURRENTLY THESE ARE ACCESSABLE TO ALL THREE VIEWER CLASSES WHICH COULD RESULT IN A CLASS MISMATCH WHEN CALLING FUNCTIONS
//THIS IS INTEDED SO THERE CAN BE INTERCOMMUNICATION IF NEEDED BUT NOT RECCOMENDED
//IT IS RECCOMMENDED TO DO A SEARCH OF *.link<ViewerType> TO ENSURE THAT THERE IS NOT UNWANTED CROSSOVER
//WARNING: AGAIN, YOU HAVE THE OPTION TO DO THIS, BUT IT MEANS YOU HAVE TO DOUBLE-TRIPLE CHECK THE NAME OF THE LINK METHOD YOU USED WITH THE VIEWER TYPE
///////////////////
	
/**
 * Links the model with its respective view
 * @return
 */
public BannerFabric linkBanner() {
	return banner_innerconnect;//Return the fabric object for access to its functions
}
/**
 * Links the model with its respective view
 * @return
 */
public AutomatorFabric linkAutomator() {
	return automator_interconnect;//Return the fabric object for access to its functions
}
/**
 * Links the model with its respective view
 * @return
 */
public GcodeFabric linkGcode() {
	return gcode_interconnect;//Return the fabric object for access to its functions
}
	
}
