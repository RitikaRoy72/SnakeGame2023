# SnakeGame2023
My version of the Snake Game. Created as a final project in 2023 AP Computer Science A.

#Contents
* Purpose
* Set up + Libraries
* Project Map

# Purpose
This project was created as the end of year AP Computer Science A project. Its intent is to display topics such as recursion, inheritance, object oriented programming, GUIs layout, and much more. With a 3 week time limit, this project is not in its fullest development.

# Set up
Original prgram was coded on replit, Main.java is the main execution program. Ensure to have Java17 installed. The following packages must also be up to date on the IDE:
 javafx.application.*
 javafx.stage.*
 javafx.scene.*
 javafx.geometry.*
 javafx.event.*
 java.util.*

# Project Map
Main.java is the main branch. It serves the following uses:
* Launching and setting up GUI window
* Inititializing all game elements
* Appends game elements after event
* Updates game elements in every event
  
Snake.java is the controling game elements
* Responds to user input
* Appends to shape after eating event
* Calls for new apple generation
* Calls for new obstacle creation
* Main.java appends only change to shape
* Main.java removes snake from user sight after collision
* First two blocks of snake never delete

Obstacle.java creates new obstacle
* Note that new obstacles are new obstacle elements in the non-static array
* Old obstacles still present
* Main.java adds latest obstacle

Score.java maintains score
* Used to regulate the addition of elements
* Contains best static componenet
* Contains current non-static componenet

Apple.java extends circle
* On collision or regeneration, new coordinates are assigned to the apple
* Apples are never deleted
* Checks for coordinates of obstacle and snake perior to regeneration
