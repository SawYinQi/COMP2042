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