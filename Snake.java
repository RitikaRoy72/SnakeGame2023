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
import javafx.scene.paint.*;
import java.lang.Math;
import java.io.*;

import javax.sound.sampled.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;



public class Snake extends Rectangle{

	/** 
	* constracts a snake object
	* represented as a green rectangle (square demensions)
	*
	*/
	//private Rectangle snake;
	private double height;
	private double width;
	private int blockSize;
	private Apple apple;
	private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
	public Snake(double width, double height, Apple apple){
		blockSize = (int)(height/20);
		blocks.add(new Rectangle((int)(width/2), (int)(height/2), (int)(height/20), (int)(height/20)));
		blocks.get(0).setFill(Color.CYAN);
		blocks.add(new Rectangle((int)(width/2)-blockSize, (int)(height/2), (int)(height/20), (int)(height/20)));
		blocks.get(1).setFill(Color.GREEN);
					//Only used for screen sizing, rest of the program is javafxs
		this.width = width;
		this.height = height;
		this.apple = apple;
		
	}

	/**
  * Getters to control access
	*/
	public Rectangle getSnake(int i){
		return blocks.get(i);
	}

	public Rectangle getLastBlock(){
		return blocks.get(blocks.size()-1);
	}

	/**
  * recenter if the user looses the snake
	*/
	public void reCenter(){
		blocks.get(0).setX(width/2);
		blocks.get(0).setY(height/2);
		for (int i = 1; i < blocks.size();i++){
			blocks.get(i).setX(blocks.get(i-1).getX()+blockSize);
			blocks.get(i).setY(height/2);
		}
	}

	/**
  * After user looses, reset the snake to its original size
	*/
	public void resetSnake(){
		for (int i = blocks.size()-1; i>1; i--){
			blocks.remove(i);
		}
		blocks.get(0).setX(width/2);
		blocks.get(0).setY(height/2);
		blocks.get(1).setX((width/2)-blockSize);
		blocks.get(1).setY(height/2);
	}
	
	/**
	*Translates snake on screen as operated
  * Radial gradient is added for visual appeal and to help
	* the user track the snake when search for an offscreen apple
	*/
	public RadialGradient moveSnakeUp(){
		if (blocks.get(0).getY()<=blocks.get(1).getY()) {
			for (int i = blocks.size()-1; i > 0; i--){
				blocks.get(i).setX(blocks.get(i-1).getX());
				blocks.get(i).setY(blocks.get(i-1).getY());
			}
		}
		blocks.get(0).setY(blocks.get(0).getY() - blockSize);
		if (blocks.get(0).getY()<0) this.reCenter();
		
		return new RadialGradient(0, -Math.tan(this.getY()), blocks.get(0).getX(), blocks.get(0).getY(), blockSize*10, false, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(blockSize*blockSize*blockSize, Color.web("#fcc200")));
		
	}
		

	public RadialGradient moveSnakeDown(){
		if (blocks.get(0).getY()>=blocks.get(1).getY()) {
			for (int i = blocks.size()-1; i > 0; i--){
				blocks.get(i).setX(blocks.get(i-1).getX());
				blocks.get(i).setY(blocks.get(i-1).getY());
			}
		}
		blocks.get(0).setY(blocks.get(0).getY() + blockSize);
		if (blocks.get(0).getY()>height) this.reCenter();
		
		return new RadialGradient(0, Math.tan(this.getY()), blocks.get(0).getX(), blocks.get(0).getY(), blockSize*10, false, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(blockSize*blockSize*blockSize, Color.web("#fcc200")));
	}

	
	public RadialGradient moveSnakeLeft(){
		if (blocks.get(0).getX()<=blocks.get(1).getX()){
			for (int i = blocks.size()-1; i > 0; i--){
				blocks.get(i).setX(blocks.get(i-1).getX());
				blocks.get(i).setY(blocks.get(i-1).getY());
			}
		}
		blocks.get(0).setX(blocks.get(0).getX() - blockSize);
		if (blocks.get(0).getX() <0) this.reCenter();
		
		return new RadialGradient(0, -Math.tan(this.getX()), blocks.get(0).getX(), blocks.get(0).getY(), blockSize*10, false, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(blockSize*blockSize*blockSize, Color.web("#fcc200")));
		}

	
	public RadialGradient moveSnakeRight(){
		if (blocks.get(0).getX()>=blocks.get(1).getX()){
			for (int i = blocks.size()-1; i > 0; i--){
				blocks.get(i).setX(blocks.get(i-1).getX());
				blocks.get(i).setY(blocks.get(i-1).getY());
			}
		}
		blocks.get(0).setX(blocks.get(0).getX() + blockSize);
		if (blocks.get(0).getX()>width) this.reCenter();
		
		return new RadialGradient(0, Math.tan(this.getX()), blocks.get(0).getX(), blocks.get(0).getY(), blockSize*10, false, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(blockSize*blockSize*blockSize, Color.web("#fcc200")));
	}
		 
	

	/** 
	* Tracks snake and determines when snake collides
	* Will end game on collision
	*/
	public boolean isDead(Obstacle obs, Score score){
		for (int i = 0; i < score.getCurrentScore(); i++){
			//USE THE BELOW METHOD!!!!
			if (blocks.get(0).getBoundsInParent().intersects(obs.getCircles(i).getBoundsInParent())) {
				this.playGameOver();
				return true;
			}
		}
		return false;
	}

	/** 
	* Tracks the snake for eating apple
	* Increments score when eaten
	*/
	public boolean isCollide(Apple apple){
		if (blocks.get(0).getBoundsInParent().intersects(apple.getBoundsInParent())) {
		blocks.add(new Rectangle(blocks.get(blocks.size()-1).getX()+blockSize, blocks.get(blocks.size()-1).getY(), blockSize, blockSize));
			blocks.get(blocks.size()-1).setFill(Color.GREEN);
			this.playEat();
			return true;
		}
		else return false;
	}

	/**
	* Tracks if the user has collided with themselves
	* Returns true if the collision has occured, false otherwise
	*/
	public boolean selfCollide(){
	if (blocks.size()<3) return false;
	for (int i = 3; i < blocks.size(); i++){
			if ((blocks.get(0).getX() == blocks.get(i).getX() && blocks.get(0).getY() == blocks.get(i).getY())){
				this.playGameOver();
				return true;
			}
		}
		return false;
	}

	/**
	* Audio tracks for eating and game over
	* Do not work in repl - untested
	*/
	public void playGameOver(){
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("gameover.wav").getAbsoluteFile());

    Clip clip = AudioSystem.getClip();
          
    clip.open(audioInputStream);
		clip.start();
		} catch (Exception e){}
	}

	public void playEat(){
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("eating.wav").getAbsoluteFile());

    Clip clip = AudioSystem.getClip();
          
    clip.open(audioInputStream);
		clip.start();
		} catch (Exception e){}
	}
}