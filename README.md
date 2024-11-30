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
