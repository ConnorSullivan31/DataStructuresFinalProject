/**
 * 
 */
package app;

import java.io.File;

/**
 * @author ConnorSullivan31
 *
 */
public class FileIO {
	
	private File fd;//Used to hold the file descriptor
	
	FileIO(String filename){
		fd  = new File(filename);//Create a new file  
		//
	}
}
