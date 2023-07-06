import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.util.*;


public class Obstacle{
	private ArrayList<Circle> obs = new ArrayList<Circle>();
	private double width;
	private double height;
	public Obstacle(double width, double height){
		this.width = width;
		this.height = height;
	}

	/**
	* Create a list of objects, circle obstacles
	* Precondition, only called for every scored apple
	* Positconition, adds a randomly position circle object
	*/
	public ArrayList<Circle> generateObs(Apple apple){
		int x = (int)(Math.random()*(width-50));
		int y = (int)(Math.random()*(height-50));
		obs.add(new Circle(x, y, (int)(width/40), Color.BLACK));
		for(int i = 0; i<obs.size(); i++){
			if (obs.get(obs.size()-1).getBoundsInParent().intersects(obs.get(i).getBoundsInParent())){
				x = (int)(Math.random()*(width-50));
				y = (int)(Math.random()*(height-50));
			}
		}
		
		return obs;
	}

	/**
  * reset function for when the user looses
	*/
	public void reset(){
		for (int i = obs.size()-1; i>0; i--){
			obs.remove(i);
		}	
	}
	
	/**
	* Returns circle obstacle objects
	* Limits access to arraylist
	*/
	public Circle getCircles(int i){
		return obs.get(i);
	}
}