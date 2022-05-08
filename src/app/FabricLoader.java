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
	
	
	
public BannerFabric linkBanner() {
	return banner_innerconnect;//Return the fabric object for access to its functions
}
	
public AutomatorFabric linkAutomator() {
	return automator_interconnect;//Return the fabric object for access to its functions
}
	
public GcodeFabric linkGcode() {
	return gcode_interconnect;//Return the fabric object for access to its functions
}
	
}
