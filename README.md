list of things done
1. change the image file format from jpg to png & made use of Image_Name in shieldImage class
2. organise classes using packages
3. fix null Pointer issue by adding Object.RequireNonNull
4. fix invocationTargetError by adding timeline.stop() when transitioning to next level
5. make gameOver display not out of screen bounds.
6. renaming some variable to improve readability such as Max_Frame_With_Shield to Shield_Duration
7. Rename consecutiveMovesInSameDirection to Frames_With_Same_Move for better understanding
8. remove getProjectileInitialPosition() and instead use the existing method from parent getProjectileYPosition(Y_Projectile_Position_Offset) this removes redundancy
9. separate return Math.random() < FIRE_RATE into its own method called enemyFireCurrentFrame to improve readability and modularity 
10. remove redundant abstract method from fighterplane and projectile class. 
11. remove the same override updatePosition() in different children of projectile class, since the method is the same it can be place generally in projectile class, and each individual velocity will be passed onto the projectile class using super()
12. remove redundant setDestroy 
13. separate shield responsibilities from Boss 
14. separate Movement responsibilities from Boss 
15. combine moveup,down,stop methods into one using switch() 
16. initialise friendly unit method in level parent, since its the same for levelone and two 
17. separate collision logic from level parent 
18. separate user input logic from level parent
19. separate actors updating, removal mechanic from level parent 
20. Separate gameOver logic from level parent
21. separated win condition from checkIfGameOver, to new method checkIfLevelCompleted
22. move levelview and leveltwoview to displays
23. replace deprecated Observer class with JavaFX's StringProperty and SimpleStringProperty for event handling
24. combine movevertically and movehorizontally method into one
25. added left and right movement to boss movement pattern
26. enhance SRP moving generateenemyFire, addEnemyUnit, updatekillcount, getcurrentnumenemies etc to gameactormanager 
27. added switchScreen method in MainController to handle screen transition between diffrent fxml files
28. added screenController which delegates user interaction to MainController class(formerly controller), startGame,exitGame methods. 
29. added win, lose, and menu fxml, removed class for gameover and win image, since i directly added the image to the fxml
30. added ammunition logic to user plane
31. fix the looping problem where beacuse userprojectile is the actor in the outter loop it allows for multiple enemies to be killed in inner loop when projectiles intersect.
32. remove enemy.destroyed() from handleEnemyPenetration, so it wont mark it as destroyed when enemy pass defense line. 
33. added removePenetratedEnemies() which removes enemies enemy which pass defense line.
34. replace update kill mechanic to be base on number of enemies which are set to destroyed instead of remaining enemies on screen.
35. made Level Tutorial
36. added tutorial button to menu fxml
37. made Level Tutorial View to display instructions to user.
38. added backToMenu in Level Parent, used for when Level tutorial ends
39. organize managers into dedicated manager package
40. added displays for ammo and kill target for level advancement
41. add pause option to userinputhandler and pause display image.
42. fix some minor problems with user being able to generate projectiles when paused and ammo regenerating while paused
43. add go back to menu method in ScreenController class for win and lose fxml to have backtomenu button function
44. added initialiseLevelView in levelParent, this ensures level subclass constructor is initialise first before initialiselevelview, so boss object wont be null
45. fix visibility of shield and update shield image to be forcefield type of shield and make shield follow boss plane
46. added boss healthbar display
47. added custom rectangle hitbox, hitboxes are returned by each actors
48. added display for hitbox for adjusting hitbox size
49. modified gameActorManager and collsionManager to centralise the variables by passing variables to class constructor, and change some access modifier to private for encapsulation
50. added new enemytype "enemyVersiontwo" and enemyversiontwo movement 
51. renamed level2 to levelboss
52. renamed leveltwoview to levelbosview
53. added level 2 and 3 which contains new enemy type
54. modified boss level to include enemyversiontwo
55. further seperate input handling from levelParent, seprated the even handler handling to userinputhandler
56. made base movement pattern class for bossmovementpattern and new enemy type movement pattern
57. renamed Max_frames with same move and Frame with same move to max moves since last shuffle and move executed for better readabillity 
58. move entity behavior related class into behavior package in entities package
59. Added TimelineManager to manage time line of leveParent and Userplane ammo
60. Added LevelViewHandler responsible for managing the view of the current level
61. Move activeActor ActiveActordestructibles and destructibles to package destructibles
62. added EnemySpawner class which contains logic for spawning enemies(enemyplane, enemyplanev2), due to similarities across levels


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

## 4. Additional Features
### 4.1 Features Working Properly

### 4.2 Features Working But Not Properly
    

## 5. Features Not Implemented

## 6. Problems
