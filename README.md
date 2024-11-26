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