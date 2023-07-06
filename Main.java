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
//Only used for screen sizing
import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
			scene.setFill(Color.GRAY);

			//Constract Game objects
			Score score = new Score();
			Text current = new Text(10, 20, "Current: "+score.toStringScore());
			current.setFill(Color.BLUE);
			
			Text best = new Text(10, 35, "Best: "+score.toStringBest());
			best.setFill(Color.BLUE);

			root.getChildren().add(current);
			root.getChildren().add(best);
			
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
			
			Apple apple = new Apple(width, height);
			root.getChildren().add(apple);

			Snake snake = new Snake(width, height);
			root.getChildren().add(snake);


			Obstacle obs = new Obstacle(width, height);
			
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();

			//One each button press, move object
			//Check for consumed apple or obstacle collison
			//print score
			//Buttons and a, w, s, z, keys are interchageable
			up.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeUp();
					if (snake.isDead(obs, score)){
						Text over = new Text(70, 70, "Game Over");
						over.setFill(Color.WHITE);
						root.getChildren().add(over);
						System.out.println("Game Over");
						try{ Thread.sleep(10000); }
						catch (Exception e){ System.exit(0);}
						System.exit(0);
					}
					if(snake.isCollide(apple)){
						score.increment();
						apple.newApple();
						current.setText("Current: "+score.toStringScore());
						best.setText("Best: "+score.toStringBest());
						
						obs.generateObs(apple);
						root.getChildren().add(obs.getCircles(score.getCurrentScore()-1));
						
						
					} 
					
				}
			});

			down.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeDown();
					if (snake.isDead(obs, score)){
						Text over = new Text(70, 70, "Game Over");
						over.setFill(Color.WHITE);
						root.getChildren().add(over);
						System.out.println("Game Over");
						try{ Thread.sleep(10000); }
						catch (Exception e){ System.exit(0);}
						System.exit(0);
					}
					if(snake.isCollide(apple)){
						score.increment();
						apple.newApple();
						current.setText("Current: "+score.toStringScore());
						best.setText("Best: "+score.toStringBest());
						
						obs.generateObs(apple);
						root.getChildren().add(obs.getCircles(score.getCurrentScore()-1));
					}
				}
			});

			left.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeLeft();
					if (snake.isDead(obs, score)){
						Text over = new Text(70, 70, "Game Over");
						over.setFill(Color.WHITE);
						root.getChildren().add(over);
						System.out.println("Game Over");
						try{ Thread.sleep(10000); }
						catch (Exception e){ System.exit(0);}
						System.exit(0);
					}
					if(snake.isCollide(apple)){
						score.increment();
						apple.newApple();
						current.setText("Current: "+score.toStringScore());
						best.setText("Best: "+score.toStringBest());

						obs.generateObs(apple);
						root.getChildren().add(obs.getCircles(score.getCurrentScore()-1));
					}
				}
			});

			right.setOnAction (new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event){
					snake.moveSnakeRight();
					if (snake.isDead(obs, score)){
						Text over = new Text(70, 70, "Game Over");
						over.setFill(Color.WHITE);
						root.getChildren().add(over);
						System.out.println("Game Over");
						try{ Thread.sleep(10000); }
						catch (Exception e){ System.exit(0);}
						System.exit(0);
					}
					if(snake.isCollide(apple)){
						score.increment();
						apple.newApple();
						current.setText("Current: "+score.toStringScore());
						best.setText("Best: "+score.toStringBest());

						obs.generateObs(apple);
						root.getChildren().add(obs.getCircles(score.getCurrentScore()-1));
					}
				
				}
			});

			scene.setOnKeyPressed((KeyEvent event) -> {
				if (event.getCode().equals(KeyCode.S)){
					snake.moveSnakeRight();
				}else if (event.getCode().equals(KeyCode.A)){
					snake.moveSnakeLeft();
				}else if (event.getCode().equals(KeyCode.W)){
					snake.moveSnakeUp();
				}else if (event.getCode().equals(KeyCode.Z)){
					snake.moveSnakeDown();
				}
				if (snake.isDead(obs, score)){
						Text over = new Text(70, 70, "Game Over");
						over.setFill(Color.WHITE);
						root.getChildren().add(over);
						System.out.println("Game Over");
						try{ Thread.sleep(10000); }
						catch (Exception e){ System.exit(0);}
						System.exit(0);
					}
					if(snake.isCollide(apple)){
						score.increment();
						apple.newApple();
						current.setText("Current: "+score.toStringScore());
						best.setText("Best: "+score.toStringBest());
						
						obs.generateObs(apple);
						root.getChildren().add(obs.getCircles(score.getCurrentScore()-1));
					}
				});
													
			
		} catch (Exception e){
			System.out.println("Error");
		}
  }

	
  public static void main(String[] args) {
    launch(args);
  }
} 
