# LPOO1617_T6G4

##### Members:

- Daniel Ribeiro de Pinho (201505302)
up201505302@fe.up.pt

- Xavier Reis Fontes (201503145)
up201503145@fe.up.pt

# Setup
## Project installation

## App installation
Given that this is an Android app, the user has to download the .apk file to the device and open it in the device using a file explorer. Additionally, the user needs to allow installation from "Unknown Sources", a setting that can be found in Settings > Security.

# Development documentation
## UML Diagrams

## Design patterns used
This project includes in its codebase several design patterns:
* Singleton
* Factory
* State
* MVC

## Difficulties and lessons

## Team effort

# User manual

## The main menu
Upon launching the game the player is greeted with the following screen, showing the game title and four buttons:

![alt text](Manual_MainMenu.png "Main Menu")

From left to right: Play game, Settings, Hall of Fame (highscores), and Credits

## The settings menu
![alt text](Manual_Settings.png "Settings Menu")

In this menu, the player can find sound and music toggles, along with a slider for the accelerometer sensibility for the game background, since it moves according to the device's orientation.

## The Hall of Fame
![alt text](Manual_Highscores.png "Highscores")

In this menu, the player can see the top five scores stored in the device, along with a button to go back to the main menu.

## The credits
![alt text](Manual_Credits.png "Credits")

The credits just show the developers' names along with the professors'. The music credits are also shown.

## In the game
Cumulus is a simple endless runner; The player plays as Birb, a bird who somehow got on top of the clouds while being unable to fly. The player must keep Birb on top of the clouds as long as they can, with the bird's tiny wings only letting it double jump.

![alt text](Manual_Ingame.png "Ingame")

The score is calculated taking into account the time the player has managed to spend above the clouds, as well as Birb's velocity. The faster the player goes, the more the score increases. The player character can walk on the clouds, and can only jump twice before having to land on another platform again.

If the player falls below the clouds or falls behind (disappears to the left of the screen), they lose.

![alt text](Manual_GameOver.png "The game over screen")

The user can from this screen return to the main menu and try the game again.

--------------old stuff below here---------------

# Architecture Design:

## Package and class diagram (UML), documenting (describing) each class' responsibility
![alt text](ClassDiagram.png "Classes")

**Game**: The class that handles the game's basic gameplay loop (receiving input, checking for loss, etc.)

The Entity/Character/Pickup/Hero/Enemy classes are split into three classes each (based on the MVC method). Generically, they are described as such:

    - Body (the "Controller" part of the MVC, it handles operations between the Model and the View).

    - Model (the Model includes the data for the class and includes methods to manipulate said data).

    - View (the View handles the reception of data to be manipulated through the Body).

**Entity**: The generic game element that is not fixed in the world. It can be split into two types: Characters and Powerups.

**Character**: An entity that represents a game character (whether it's a playable character or an enemy).

**Hero**: The player character, which is controlled by the user.

**Enemy**: An enemy. These characters can cause the game to end prematurely.

**Pickup**: Pickups are entities that bestow the player character additional attributes.

**Platform**: The entities the player character stands on. They are solid entities.

## Design of behavioural aspects
![alt text](Behavioural_Aspects-menu.png "Menu")
![alt text](Behavioural_Aspects-game.png "Game")
## Design Patterns to use
1. Singleton - for the main game class.
2. Decorator - for the powerups.
3. Observer - for the controllers.
4. State - for the different states of the gameplay.
5. Abstract Factory - to get the clouds.
6. Strategy - not yet determined.
# GUI Design
## Identification of the main functionalities
1. The game will be playable in Single Player or against another player.
2. The game keeps scores of single player game runs.
3. The settings of the game will allow for a player to turn off the sound in the game.
4. The game will be able to share a post on Facebook upon player's request.
## GUI mock-ups
![alt text](GUI-MockUp-main.png "Main Screen")
![alt text](GUI-MockUp-level.png "Level Screen")
![alt text](GUI-MockUp-level_paused.png "Level Paused Screen")
![alt text](GUI-MockUp-settings.png "Settings Screen")

# Test Design
## Listing of the expected final test cases
1. Test to the movement of the user throughout the level.
2. Test randomness of the generated level.
3. Test out-of-bounds behaviour.
4. Test player losing.
5. Test highscore saving.
