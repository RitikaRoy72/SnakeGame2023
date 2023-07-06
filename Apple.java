import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.paint.Color; 
import javafx.scene.shape.*;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Apple extends Circle{

	/**
	* Generates a random position and 
	* constracts an apple represented by a red
	* dot
	*/
	private double width;
	private double  height;
	public Apple(double width, double height) {
		super((int)(Math.random()*150)+25, (int)(Math.random()*150)+25, (int)(width/70), Color.RED);
		this.width = width;
		this.height = height;
		this.setCenterX((int)(Math.random()*(width-30)));
		this.setCenterY((int)(Math.random()*(height-30)));
	}

	public Circle getApple(){
		return this;
	}

	
	/**
	* Called every time the previous apple was eaten
	* constructs a new apple replaces old apple
	* with the newly constructed apple
	*/
	public void newApple(Obstacle obs, Score score){
		this.setCenterX((int)(Math.random()*(width)));
		this.setCenterY((int)(Math.random()*(height)));
		
	}

	

}