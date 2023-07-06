import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class Score{

	private int currentScore;
	private static int bestScore;
	public Score(){
		
	}

	/**
	* Increments score for every apple eaten
	*/
	public void increment(){
		currentScore += 1;
		if (currentScore > bestScore) bestScore = currentScore;
		
	}

	/**
 	* resets the score when the user looses
 */
	public void resetScore(){
		currentScore = 0;
	}
	
	/**
	* Methods to return information for display purposes
	*/
	public String toStringScore(){
		return currentScore+"";
	}
	
	public String toStringBest(){
		return bestScore+"";
	}

	public int getCurrentScore(){
		return currentScore;
	}
}