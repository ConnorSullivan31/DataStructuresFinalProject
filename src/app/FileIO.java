/**
 * 
 */
package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ConnorSullivan31
 *
 */
public class FileIO {
	
	private File fd;//Used to hold the file descriptor
	private BufferedReader input_stream;//Used to read in data from the file reader which uses the file descriptor
	private BufferedWriter output_stream;//Used to write data from the application to a file using file writer and our file desciptor
	private AccessMode access_mode;//Holds whether we are reading or writing
	
	FileIO(String filename, AccessMode mode){
		fd  = new File(filename);//Create a new file  
		access_mode = mode;//Save whether we are reading or writing
		
			if(access_mode == AccessMode.INPUT) {
				try {
					input_stream = new BufferedReader(new FileReader(fd));//Open the file for reading
				} catch (FileNotFoundException e) {
					System.out.println("Error: Could not open file for reading");//Output failure to open file
					e.printStackTrace();//Print out the stack trace
				}
			}else if (access_mode == AccessMode.OUTPUT) {
				try {
					output_stream = new BufferedWriter(new FileWriter(fd));//Open the file for writing
				} catch (IOException e) {
					System.out.println("Error: Could not open file for writing");//Output failure to open file
					e.printStackTrace();//Print out the stack trace
				}
			}
		
	}
	
	/**
	 * Read in data from open file -- Overwrites previous data in file
	 * Have to call the constructor again to open the file to wite data
	 * @return
	 */
	public String loadData() {
		String data = "";//Used to hold File input
		String temp = "";//Used to hold the current input line
		
		try {
			while((temp = input_stream.readLine()) != null) {//Read file line by line until we are out of lines
				data += (temp + "\n");//Put all of the data into one string and re-add newline that is removed by readline
			}
			input_stream.close();//Close the file
		}catch(IOException e) {
			System.out.println("Error: Could not read file data");
			e.printStackTrace();
		}
		
		return data;//Return the data from the file
	}
	
	/**
	 * Save input string to open file -- Does one write and closes file
	 * Have to call the constructor again to open the file to save data
	 * @param data
	 */
	public void saveData(String data) {
		try {
			output_stream.write(data);//Write the data to the file
			output_stream.close();//Close the file
		} catch (IOException e) {
			System.out.println("Error: Could not write data to file");
			e.printStackTrace();
		}
	}
	
	
	
	enum AccessMode{
		INPUT,OUTPUT;//File access modes
	}
	
}
