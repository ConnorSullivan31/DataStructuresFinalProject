/**
 * 
 */
package app;

import java.text.DecimalFormat;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * @author ConnorSullivan31
 *
 */
public class LoadMeterViewer {

	private BorderPane load_meter_layout;//Main layout for this viewer
	private Label load_meter_label;//Holds the title for the load meter panel in the top of the border pane
	private VBox load_bar_layout;//Holds the layout for our load bars
	private LoadMeter x_meter, y_meter, z_meter, s_meter;//Holds our load meter objects

	private Timeline load_timer;//Timer that dictates when messages should rotate
	private double timer_interval = 50;//Set the timer interval to 50 milliseconds
	private Random rand_generator;//Holds a random number for our load meters
	double x_prev = 0.0, x_new = 0.0, y_prev = 0.0, y_new = 0.0, z_prev = 0.0, z_new = 0.0, s_prev = 0.0, s_new = 0.0;//Placeholder values for bar fading
	
	public LoadMeterViewer() {
		load_meter_layout = new BorderPane();//Create the border pane
		load_meter_label = new Label();//Create the label
		load_bar_layout = new VBox();//Create the layout for the load bars
		x_meter = new LoadMeter();//Create the x-axis load meter
		y_meter = new LoadMeter();//Create the y-axis load meter
		z_meter = new LoadMeter();//Create the z-axis load meter
		s_meter = new LoadMeter();//Create the spindle load meter
		
		load_timer = new Timeline(new KeyFrame(Duration.millis(timer_interval), event -> {
			//Add load code here
			randomizeLoadValues();//Generate randomized load values every interval
		}));//This is our version of a timer
		
		rand_generator = new Random();//Create our random number generator
		
		setupViewer();//Init values
	}
	
	public BorderPane getViewer() {
		return load_meter_layout;//Return the main layout
	}
	
	private void setupViewer() {
		//Label
		load_meter_label.setText("Load Monitoring (%)");
		load_meter_layout.setTop(load_meter_label);//Put the label at the top
		//Load Bar Values -- These will be wiped immediately - REMOVE THESE EVENTUALLY
		x_meter.setAxis("X: ");//Label the axis
		x_meter.setLoad(.50);//Set load to 50%
		
		y_meter.setAxis("Y: ");//Label the axis
		y_meter.setLoad(.10);//Set load to 50%
		
		z_meter.setAxis("Z: ");//Label the axis
		z_meter.setLoad(.25);//Set load to 50%
		
		s_meter.setAxis("S: ");//Label the axis
		s_meter.setLoad(.90);//Set load to 50%
		//Load Bar Layout
		load_bar_layout.getChildren().addAll(x_meter.getLoadMeter(),y_meter.getLoadMeter(),z_meter.getLoadMeter(),s_meter.getLoadMeter());//Add the load bars to the layout. They will be displayed in the order they are added here.
		load_meter_layout.setBottom(load_bar_layout);//Set the buttons to be displayed on the bottom of the border pane
		//Randoms -- Add prev values here if we want them to all start from different values besides 0
		x_new = rand_generator.nextDouble();//Load an initial random value
		y_new = rand_generator.nextDouble();//Load an initial random value
		z_new = rand_generator.nextDouble();//Load an initial random value
		s_new = rand_generator.nextDouble();//Load an initial random value
		//Timer
		load_timer.setCycleCount(Animation.INDEFINITE);
		load_timer.play();//Start Timer
	}
	
	private void randomizeLoadValues() {

		//Random X fading
		if(x_prev < x_new) {
			x_prev += .01;//Increment the old value
			x_meter.setLoad(x_prev);//Update the load meter
			if(x_prev >= x_new) {
				x_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}else if(x_prev > x_new) {
			x_prev -= .01;//Decrement the old value
			x_meter.setLoad(x_prev);//Update the load meter
			if(x_prev <= x_new) {
				x_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}
		
		//Random Y fading
		if(y_prev < y_new) {
			y_prev += .01;//Increment the old value
			y_meter.setLoad(y_prev);//Update the load meter
			if(y_prev >= y_new) {
				y_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}else if(y_prev > y_new) {
			y_prev -= .01;//Decrement the old value
			y_meter.setLoad(y_prev);//Update the load meter
			if(y_prev <= y_new) {
				y_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}
		
		//Random Z fading
		if(z_prev < z_new) {
			z_prev += .01;//Increment the old value
			z_meter.setLoad(z_prev);//Update the load meter
			if(z_prev >= z_new) {
				z_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}else if(z_prev > z_new) {
			z_prev -= .01;//Decrement the old value
			z_meter.setLoad(z_prev);//Update the load meter
			if(z_prev <= z_new) {
				z_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}
		//Random S fading
		if(s_prev < s_new) {
			s_prev += .01;//Increment the old value
			s_meter.setLoad(s_prev);//Update the load meter
			if(s_prev >= s_new) {
				s_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}else if(s_prev > s_new) {
			s_prev -= .01;//Decrement the old value
			s_meter.setLoad(s_prev);//Update the load meter
			if(s_prev <= s_new) {
				s_new = rand_generator.nextDouble();//Get a new random value to fade to
			}
		}
		
	}//func
	
////////////////////////////////////////////
//This class got big -- Maybe offload it to its own class in the future
	private class LoadMeter {
		private HBox load_layout;//Main layout for a load meter
		private Label load_axis_label;//Holds the axis label for the load meter
		private ProgressBar load_bar;//Holds the acutal load bar visual
		private Label load_percent_label;//Holds the percent label at the end of the load bar
		private int bar_height = 25;//Defines the height of the load bars
		private double load_val = 0;//Holds value of the load bar - init to zero for now in case we forget to set the value later
		private String axis_label = "N/A: ";//Holds the name for the load meter  - init to n/a in case we forget to set the value later
		private DecimalFormat load_val_format;//Clips our load vals to two decimal places
		
		public LoadMeter() {
			load_layout = new HBox();//Create the layout
			load_axis_label = new Label();//Create the label
			load_bar = new ProgressBar();//Create the progress bar
			load_percent_label = new Label();//Create the load percent label
			load_val_format = new DecimalFormat();//Create decimal format -- Could setup here, if so, use "00.00"
			setupLoadMeter();//Init values
		}
		
		public HBox getLoadMeter() {
			return load_layout;//Return the main layout
		}
		
		private void setupLoadMeter() {
			//Label
			load_axis_label.setText(axis_label);//Set the axis text to the value passed in through setAxis()
			//Load Bar
			load_bar.setPrefHeight(bar_height);//Set the preferred height of the progress bar
			load_bar.setMaxSize(Double.MAX_VALUE, bar_height);//Set the progress bar to scale bigger if available
			load_bar.setProgress(load_val);//Set how full the bar is
			//Percent Label
			load_percent_label.setText(" " + load_val*100 + "%");//Set the percent label at the end of the load bar
			//Load Meter Layout
			HBox.setHgrow(load_bar, Priority.ALWAYS);//Set the layout to always scale the progress bar first
			load_layout.getChildren().addAll(load_axis_label,load_bar,load_percent_label);//Load in order of axis label, progress bar, percent label
			//Load Val format
			load_val_format.applyLocalizedPattern("00.00");//Set the pattern for the load percentages
		}
		
		public void setAxis(String axis) {
			axis_label = axis;//Set the input text for the load meters axis label
			load_axis_label.setText(axis);//Update the actual axis label
		}
		
		public void setLoad(double load) {
			load_val = load;//Set the load value of the progress bar
			load_bar.setProgress(load_val);//Update the actual load bar
			load_percent_label.setText(" " + load_val_format.format(load_val*100) + "%");//Update the percent label at the end of the load bar
		}
	}
}
//Notes:

//Another way of doing a pattern
//load_val_format.setMaximumFractionDigits(2);//Set our load percentages to never show more than two digits after the decimal
//load_val_format.setMinimumFractionDigits(2);//Set our load percentages to never show less that two digits after the decimal
//load_val_format.setMinimumIntegerDigits(2);//Set our load percentages to never show less than two digits before the decimal
