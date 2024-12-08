## GitHub Link:https://github.com/SawYinQi/COMP2042

## 1. Compilation Instruction
    1.1 Clone repo using git clone https://github.com/SawYinQi/COMP2042.git or other alternatives.

    1.2 Open project in intelliJ.

    1.3 Load Maven dependencies via pop-up from intelliJ requires javafx 23.0.2, and jdk 21.0.2.

    1.4 Navigate to Main.java.

    1.5 Run Main.java.
## 2. Classes Modified
### 2.1 ShieldImage
    2.1.1 Change directory "/com/example/demo/images/shield.jpg" to "/com/example/demo/images/shield.png" so it will point to the correct directory.

    2.1.2 Handle NullPointerException by requiring new image object to be non-null.

    2.1.3 Made use of IMAGE_NAME assigning it to "/com/example/demo/images/shield.png" and passing to ..getResourced(IMAGE_NAME).. 

    2.1.4 Moved to displays package for organisation.
### 2.2 Main
    2.2.1 myController.launchGame() is replace by mainController.showMenu this will now show the main menu when you luanch game.
### 2.3 Controller (MainController)
    2.3.1 Renamed to MainController

    2.3.2 Added switchScreen method used for transitioning between screens.

    2.3.3 Added public method (showMainMenu, showWinScreen, showLoseScreen) which utilise switchScreen to display FXML files.

    2.3.4 Added launchTutorial method which goToLevel(LEVEL_Tutorial_CLASS_NAME) so when you press tutorial button levelTutorial will start.

    2.3.5 Replace Obersver with evenListenerProperty StringProperty beacuse its deprecated.

    2.3.6 Pass MainController object to Levels in goToLevel method, so it can use methods in MainController.

    2.3.7 Added paths to FXML files for switchScreen().
### 2.4 WinImage & GameOverImage
    2.4.1 Both deleted becuase both are added directly to win.fxml and lose.fxml respectively ,hence useless.
### 2.5 LevelView
    2.5.1 Renamed showHeartDisplay to showGameDisplay beacuse new object from new classes are added to root to display.

    2.5.2 Added methods to update the new classes updateAmmunitionDisplay, updateKillTargetDisplay, which takes in parameter int ammo and int kills to update the display.

    2.5.3 showPauseImage and hidePauseImage are added to set class PauseImage's display to visible true or false.

    2.5.4 Moved to displays package for organisation.
### 2.6 LevelViewLevelTwo (LevelBossView)
    2.6.1 Renamed to LevelBossView for better readability and understanding.
    
    2.6.2 Got rid of AddImageToRoot(), since i can just override showGameDisplays to include shield image and other related objects, making it redundant.

    2.6.3 Added showShield() and hideShield(), just for better readibillity.
    
    2.6.4 Added updateShieldVisibility() which handles displaying the image on or off base on its current state.

    2.6.5 Added updateShieldPosition() which sets the x and y layout of shield to match boss plane current position.

    2.6.6 Added updateBossHealth() which updates the boss health display base on boss's current health.

    2.6.7 Added updateLevelBossView() so each of the added methods can be called repeatedly by updateScene(), effectively updating it.

    2.6.8 Moved to displays package for organisation.
### 2.7 ActiveActor
    2.7.1 Combined moveHorizontally and moveVertically into move() making it more concise.

    2.7.2 Moved to entities package within destructibles package.
### 2.8 ActiveActorDestructible
    2.8.1 Deleted redundant SetDestroyed(), directly set it to true in destroy().

    2.8.2 Added abstract Rectangle getHitBox() for customizable hitbox for each entities.

     2.8.3 Moved to entities package within destructibles package for organisation.
### 2.9 Destructible
    2.8.3 Moved to entities package within destructibles package for organisation.
### 2.10 FighterPlane
    2.10.1 Moved to entities package within planes package for organisation.
### 2.11 User Plane
    2.11.1 Combined moveDown(), moveUp(), stop() into one moves() which takes in enum Direction, up, down, stop for conciseness and added readability.
    
    2.11.2 Added incrementAmmunition() for ammunition regenration mechanic, caps at 20 ammo.

    2.11.3 Added getAmmunition() returns ammo count for display or condition usages.

    2.11.4 Instantiated class TimeManager to manage ammunition timeline for ammunition regenration mechanic.

    2.11.5 Modify fireProjectile() to only fire when ammunition is more than 0 for ammo mechanic.

    2.11.6 Override getHitBox to adjust custom hitbox for user plane.

    2.11.7 Moved to entities package within planes package for organisation.
### 2.12 EnemyPlane
    2.12.1 Seperated the logic for determing if it should fire into projectileShouldFire(), as well as for added encapsulation.

    2.12.2 Override getHitBox to adjust custom hitbox for enemy plane.

    2.12.3 Modify updatePosition to use the move().

    2.12.4 Override getHitBox to adjust custom hitbox for enemy plane.

    2.12.5 Moved to entities package within planes package for organisation.
### 2.13 Boss
    2.13.1 Separated movement pattern and shield logic from boss to improved SRP.
    
    2.13.2 Modified updatePosition to include horizontal movement with set horizontal boundaries.

    2.13.3 Override getHitBox to adjust custom hitbox for boss plane.

    2.13.4 Moved to entities package within planes package for organisation.
### 2.14 Projectile
    2.14.1 Modified updatePosition() to include move(), since all subclass has the same updatePosition, using the constructor to pass on horizontal velocity from its subclass will reduce redundancy.

    2.14.2 Added updateActor() to reduce redundancy in subclasses since their all the same.

    2.14.3 Moved to entities package within projectiles package for organisation.
### 2.15 BossProjectile
    2.15.1 Override getHitBox to adjust custom hitbox for boss projectiles.

    2.15.2 Super in constrcutor now passed on horizontal velocity of of boss projectile to superclass projectile, for updatePosition().

    2.15.3 Moved to entities package within projectiles package for organisation.
### 2.16 EnemyProjectile
    2.16.1 Override getHitBox to adjust custom hitbox for enemy projectiles.

    2.16.2 Super in constrcutor now passed on horizontal velocity of of enemy projectile to superclass projectile, for updatePosition().

    2.16.3 Moved to entities package within projectiles package for organisation.
### 2.17 UserProjectile
    2.17.1 Override getHitBox to adjust custom hitbox for user projectiles.

    2.17.2 Super in constrcutor now passed on horizontal velocity of of user projectile to superclass projectile, for updatePosition().

    2.17.3 Moved to entities package within projectiles package for organisation.
### 2.18 LevelParent
    2.18.1 LevelParent constructor now accepts MainController, this allows LevelParent to use methods from MainCOntroller, needed for displaying game over screen or win screen.

    2.18.2 Seperated actor, collsion, user input, timeline, level's state(gameover, win or going next level) and level display related logic to new classes (GameActorManager, CollsionManager, LevelStateManager, LevelViewHandler, UserInputHandler, TimelineManager), improve SRP.

    2.18.3 Added initializeLevelView(), this is called in subclasses to ensure subclass constructor is setup first before instantiateLevelView() is called, to avoid null pointer exception.

    2.18.4 Handle NullPointerException, background now requires new image object to be non-null.

    2.18.5 Modified goNextLevel() to use StringProperty to replace previously deprecated observer methods for level transitions.

    2.18.6 Moved to levels package for organisation.
### 2.19 LevelOne
    2.19.1 Removed checkIfGameOver() since its now handled by LevelStateManager, this improves SRP.

    2.19.2 Removed initialiseFriendlyUnits since its handled by GameActorManager, this improves SRP.

    2.19.3 Modify SpawnEnemyUnits to use EnemySpawner, now spawning is logic is handled by the utility class improving SRP and code reusebility since the spawning logic is quite similar across levels.

    2.19.4 Moved to levels package for organisation.
### 2.20 LevelTwo(LevelBoss)
    2.20.1 Renamed to LevelBoss, to improve readibility.
    
    2.20.2 Removed checkIfGameOver() since its now handled by LevelStateManager, this improves SRP.

    2.20.3 Removed initialiseFriendlyUnits since its handled by GameActorManager, this improves SRP.

    2.20.4 Modified spawnEnemyUnit to only spawn boss if enemy nits does not contain boss, and also utilise EnemySpawn class to add 2 additinal enemy units to make the level harder.

    2.20.5 Moved to levels package for organisation.
## 3. New Classes
### 3.1 ScreenController 
    3.1.1 Class for FXML files to communicate with MainController, just for better readability where all screen button methods are in one place.

    3.1.2 Located in controller package.
### 3.2 LevelTutorialView 
    3.2.1 Displays the UI elements specific to LevelTutorial class, such as instruction label.

    3.2.2 Located in displays package.
### 3.3 PauseImage
    3.3.1 Sets up the pause image, includes methods like showPauseImage() and hidePauseImage() to toggle visibility on and off.
    
    3.3.2 Located in displays package.
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
## 5. Features Not Implemented (Decided to work on other courseworks)
    5.1 Background music

    5.2 Game sound effects

    5.3 Plane destruction animation

    5.4 Settings screen

    5.5 Transition screen between levels
## 6. Problems
    6.1 I found it difficult to complete the coursework effectively, beacuse its my first time doing refactoring activities, progress was slow in the beginning which left me behind on some of my other courseworks, but in the end i still managed to get some worked done eventhough its not the best I would have wanted, I decided to make peace with my refactoring and started on my other courseworks and studied for quiz nearing the end of November.

    
    



