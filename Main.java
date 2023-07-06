import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.input.*;
import javafx.scene.paint.*;
//Only used for screen sizing
import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;


public class Main extends Application 
{ 
  public void start(Stage primaryStage){
		//Opens with instructions (as to not crowd game screen)
		System.out.println("\n\nWelcome to a rendition of the Snake Game.\nYou play as the snake, the green box.\nYou goal is to eat the maximum amount of apples.\nEvery time you eat an apple, a small black dot, an obstacle, will appear. If you get in range of 10 pixels of the plack dot, your game ends.\nUse onscreen buttons or a, w, s, z keys to control, respectively");
		try{
		
			//Only used for screen sizing, rest of the program is javafx
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

			//Set game parameters
	    primaryStage.setTitle("Snake Game");
			Group root = new Group();
	    Scene scene = new Scene(root, width, height);
			
			scene.setFill(new RadialGradient(0, 0, (int)height/2, (int)width/2, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.web("#fcc200"))));

			//Construct Game objects
			Score score = new Score();
			Text current = new Text(10, 20, "Current: "+score.toStringScore());
			current.setFill(Color.BLUE);

			//Score
			Text best = new Text(10, 35, "Best: "+score.toStringBest());
			best.setFill(Color.BLUE);
			root.getChildren().add(current);
			root.getChildren().add(best);

			//Onscreen buttons
			Button up = new Button("U");
			up.setLayoutX(width-57);
			up.setLayoutY(height-70);
			root.getChildren().add(up);

			Button down = new Button("D");
			down.setLayoutX(width-57);
			down.setLayoutY(height-50);
			root.getChildren().add(down);

			Button left = new Button("L");
			left.setLayoutX(width-80);
			left.setLayoutY(height-60);
			root.getChildren().add(left);

			Button right = new Button("R");
			right.setLayoutX(width-30);
			right.setLayoutY(height-60);
			root.getChildren().add(right);

			//Recent for lost snake or apple
			Button reCenter = new Button("Recenter");
			reCenter.setLayoutX(0);
			reCenter.setLayoutY(height-60);
			root.getChildren().add(reCenter);

			//Generate game elements
			Apple apple = new Apple(width, height);
			root.getChildren().add(apple);

			Snake snake = new Snake(width, height, apple);
			for (int i = 0; i < score.getCurrentScore()+2; i++){
				root.getChildren().add(snake.getSnake(i));
			}
		
			Obstacle obs = new Obstacle(width, height);
			
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();

			//One each button press, move object
			//Check for consumed apple or obstacle collison
			//print score
			//Buttons and a, w, s, z, keys are interchageable

			reCenter.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event){
					snake.reCenter();
					apple.newApple(obs, score);
				}
			});
			
			up.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeUp();
					afterPress(apple, snake, obs, score, root, current, best, (int)width, (int)height);
				} 
					
			});

			down.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeDown();
					afterPress(apple, snake, obs, score, root, current, best, (int)width, (int)height);
				} 
		});
				
			left.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeLeft();
					afterPress(apple, snake, obs, score, root, current, best, (int)width, (int)height);
				}
		});
		

			right.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeRight();
					afterPress(apple, snake, obs, score, root, current, best, (int)width, (int)height);
				}
			});
				

			scene.setOnKeyPressed((KeyEvent event) -> {
				if (event.getCode().equals(KeyCode.S)){
					scene.setFill(snake.moveSnakeRight());
				}else if (event.getCode().equals(KeyCode.A)){
					scene.setFill(snake.moveSnakeLeft());
				}else if (event.getCode().equals(KeyCode.W)){
					scene.setFill(snake.moveSnakeUp());
				}else if (event.getCode().equals(KeyCode.Z)){
					scene.setFill(snake.moveSnakeDown());
				}
				afterPress(apple, snake, obs, score, root, current, best, (int)width, (int)height);
					
				});
													
			
		} catch (Exception e){
			System.out.println("Error");
		}
  }

	public static void afterPress(Apple apple, Snake snake, Obstacle obs, Score score, Group root, Text current, Text best, int width, int height){
		try{
			//Check if the snake is dead 
			if (snake.isDead(obs, score) || snake.selfCollide()) {
	
				//Align the positioning of game over controls
				VBox vbox = new VBox();
				
				
				//Display the game over message
				Text over = new Text((int)(width/2), (int)(width/10), "Game Over");
				over.setFill(Color.BLACK);
				over.setStrokeWidth(4);
				vbox.getChildren().add(over);
				
	
				//Prompt the user to restart and reset game elements
				Button restart = new Button("Restart");
				vbox.getChildren().add(restart);
	
				for (int i = score.getCurrentScore()+1; i>=0; i--){
					root.getChildren().remove(snake.getSnake(i));
				}
				snake.resetSnake();
				
				restart.setOnAction (new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event){
						root.getChildren().add(snake.getSnake(0));
						root.getChildren().add(snake.getSnake(1));
						
						apple.newApple(obs, score);
						for (int i = score.getCurrentScore()-1; i>=0; i--){
							root.getChildren().remove(obs.getCircles(i));
						}
						obs.reset();
						
						score.resetScore();
						current.setText("Current: "+score.toStringScore());
	
						root.getChildren().remove(restart);
						root.getChildren().remove(over);
						root.getChildren().remove(vbox);
					}
				});
				root.getChildren().add(vbox);
				vbox.setAlignment(Pos.CENTER);
			}
			
			if(snake.isCollide(apple)){
				score.increment();
				
				current.setText("Current: "+score.toStringScore());
				best.setText("Best: "+score.toStringBest());
				obs.generateObs(apple);
				apple.newApple(obs, score);
				root.getChildren().add(obs.getCircles(score.getCurrentScore()-1));
				root.getChildren().add(snake.getLastBlock());
	
			} 
		} catch (Exception e){}
	}
	
  public static void main(String[] args) {
    launch(args);
  }
} 
