## GitHub Link:https://github.com/SawYinQi/COMP2042

## 1. Compilation Instruction
    1.1 Clone repo using git clone https://github.com/SawYinQi/COMP2042.git or other alternatives.
    1.2 Open project in intelliJ.
    1.3 Load Maven dependencies via pop-up from intelliJ.
    1.4 Navigate to Main.java 
    1.5 Run Main.java.
## 2. Classes Modified
### 2.1 ShieldImage Class
    2.1.1 Change directory "/com/example/demo/images/shield.jpg" to "/com/example/demo/images/shield.png".

    2.1.2 Handle NullPointerException by requiring new image object to be non-null.

    2.1.3 Make use of IMAGE_NAME assigning it to "/com/example/demo/images/shield.png" and passing to ..getResourced(IMAGE_NAME)..
### 2.2 Main Class
    2.2.1 myController.launchGame() is replace by mainController.showMenu.
### 2.3 Controller Class (MainController)
    2.3.1 Renamed to MainController 

    2.3.2 Added switchScreen method used for transitioning between screens.

    2.3.3 Added public method (showMainMenu, showWinScreen, showLoseScreen) which utilise switchScreen to display FXML files.

    2.3.4 Added launchTutorial method which goToLevel(LEVEL_Tutorial_CLASS_NAME) so when you press tutorial button levelTutorial will start.

    2.3.5 Replace deprecated Obersver with evenListenerProperty StringProperty.

    2.3.6 Pass MainController object to Levels in goToLevel method, so it can use methods in MainController.

    2.3.7 Added paths to FXML files for switchScreen()
### 2.4 WinImage & GameOverImage
    2.4.1 Both deleted becuase both are added directly to win.fxml and lose.fxml respectively ,hence useless.

### 2.5 LevelView
    2.5.1 Renamed showHeartDisplay to showGameDisplay beacuse new object from new classes are added to root to display.

    2.5.2 Added methods to update the new classes updateAmmunitionDisplay, updateKillTargetDisplay, which takes in parameter int ammo and int kills to update the display.

    2.5.3 showPauseImage and hidePauseImage are added to set class PauseImage's display to visible true or false.
## 3. New Classes
### 3.1 
## 4. Additional Features
### 4.1 Features Working Properly
    4.1.1 Enhanced boss movement pattern set, allowing it to move vertically and horizontally.

    4.1.2 Menu screen, includes title of game , basic background,working play, tutorial, and exit buttons.

    4.1.3 Game over screen, includes game over image, working retry, main menu and exit buttons.

    4.1.4 Win screen, includes win image, working play again, main menu and exit buttons.

    4.1.5 Tutorial level teaches users the game controlls, includes a dummy enemy plane target, and instruction manuel.

    4.1.6 Ammunition mechanic to the game, user plane has an initial ammo count of 5, with maximum 20 capacity, ammo is regenerated every 1s and is displayed for players to see, this is implemented with the intention of restricting player's fire rate, making players value each shot attempts.

    4.1.7 Kills to advance display for players to track how many more enemies they need to eliminate.

    4.1.8 Puase option to user inputs, allows player to puase the game by pressing p key with a puase image as its indicator.

    4.1.9 Boss health bar display, located at the top right of the screen, allows players to track the boss's health percentage.

    4.1.10 Enemy plane version two, new type of enemy which moves vertically, it has random vertical move sets base on the original design of boss move sets.

    4.1.11 New levels, level two now spawns enemy plane version two, while level three spawns a mix of enemy plane and enemy plane version two.

    4.1.12 Enhance difficulty of level boss, in addition to boss plane, this level now spawns two enemy plane version two.

    4.1.13 Shield image, it now follows boss plane and visibillity is toggle on and off depending on shield activation.

    4.1.14 Hitbox, instead of using image bounding box for the hitbox, I used rectangle instance and adjust hitbox for each game entities manuelly, this improves collsion accuracies.
### 4.2 Features Working But Not Properly
    4.2.1 Boss movement pattern moves diagonally in initial implementation, however I did not like that so I made getNextMove return an array of {x,y} for translation, where the next move selected will be applied to x or y while the other stays zero in translation {0, y} or {x, 0}, this prevent x and y translation at the same time.

    4.2.2 Shield image initially was not visible, once I fixed it by adding it to root group, I saw that it wasnt following the boss, so I made a method updateShieldPosition which will updates the layout of the shield image's x and y position to the boss's position in levelBossView.

    4.2.3 Enemy plane version two initially moves vertically off screen due to the spawning position, so I modify updatePosition to allow it to move horizontally into screen view, and once it reached the left bound I setted, it will begin moving vertically as intended for this unit.

    4.2.4 Pause option was not handled properly so initially it allowed user plane's ammuntition to regenrate and spawn user projectiles while in pause state, so I stopped ammunition's timeline when in pause state and made sure timeline animation must be running in order to fire user projectiles.
## 5 Features Not Implemented (Decided to work on other courseworks)
    5.1 Background music

    5.2 Game sound effects

    5.3 Plane destruction animation

    5.4 Settings screen

    5.5 Transition screen between levels
## 6. Problems
    6.1 I found it difficult to complete the coursework effectively, beacuse its my first time doing refactoring activities, progress was slow in the beginning which left me behind on some of my other courseworks, but in the end i still managed to get some worked done eventhough its not the best I would have wanted, I decided to make peace with my refactoring and started on my other courseworks and studied for quiz nearing the end of November.
    
    



