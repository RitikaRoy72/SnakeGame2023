import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.event.Event;
import java.util.*;


public class Snake extends Rectangle{

	/** 
	* constracts a snake object
	* represented as a green rectangle (square demensions)
	*
	*/
	//private Rectangle snake;
	private double height;
	private double width;
	public Snake(double width, double height){
		super(100, 100, 15, 15);
		this.setFill(Color.GREEN);
					//Only used for screen sizing, rest of the program is javafx
		this.width = width;
		this.height = height;
		this.setX((int)(width/2));
		this.setY((int)(height/2));
		
	}

	/**
  * Getter to control access
	*/
	public Rectangle getSnake(){
		return this;
	}
	

	/**
	*Translates snake on screen as operated
	*/
	public void moveSnakeUp(){
		this.setY(this.getY()-5);
		if (this.getY()> height) this.setY(0);
	}

	public void moveSnakeDown(){
		this.setY(this.getY()+5);
		if (this.getY()<0) this.setY(height);
	}

	public void moveSnakeLeft(){
		this.setX(this.getX()-5);
		if(this.getX()>width) this.setX(0);
	}

	public void moveSnakeRight(){
		this.setX(this.getX()+5);
		if(this.getX()<0) this.setX(width);
	}
		 
	

	/** 
	* Tracks snake and determines when snake collides
	* Will end game on collision
	*/
	public boolean isDead(Obstacle obs, Score score){
		for (int i = 0; i < score.getCurrentScore(); i++){
			if (Math.abs(obs.getCircles(i).getCenterX() - this.getX()) < 8 && Math.abs(obs.getCircles(i).getCenterY() - this.getY()) < 8)
				return true;
		}
		return false;
	}

	/** 
	* Tracks the snake for eating apple
	* Increments score when eaten
	*/
	public boolean isCollide(Apple apple){
		if (Math.abs(apple.getCenterX() - this.getX()) < 10 && Math.abs(apple.getCenterY() - this.getY()) < 10) {
			return true;
		}
		else return false;
	}

	
}