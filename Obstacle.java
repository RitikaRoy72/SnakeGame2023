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
		int x = (int)(Math.random()*width);
		int y = (int)(Math.random()*height);
		while (true){
			if (Math.abs(x - apple.getCenterX()) < 10 && Math.abs(y - apple.getCenterX()) < 10){
				break;
			} else {
				x = (int)(Math.random()*width);
				y = (int)(Math.random()*height);
			}
		}
		obs.add(new Circle(x, y, 6, Color.BLACK));
		return obs;
	}

	/**
	* Returns circle obstacle objects
	* Limits access to arraylist
	*/
	public Circle getCircles(int i){
		return obs.get(i);
	}
}