# SpaceAdventures Game in Java
### A game i did during my first year at the University 

## About
The game was created back in 2017 and was developed in Eclipse Oxygen. I thought it would be of more use in my University era projects here on GitHub than collecting bit-dust on some harddrive of mine.

Collect as many points as possible in each level and try to stay alive as long as possible. Two powerups in the form of a lifebuoy that givs one additional life, the second powerup is to turn invisible to the enemy fire.

## Screenshot
![Screenshot of the game in action](images/game.png?raw=true "Screenshot of the game in action")

## Java version
The Java version i used to compile this game was.
```
$ javac -version
javac 9.0.4

$ java -version
java 9.0.4
```
## Compiling
Both of the commands must be run from the src directory.

Compile the game. 
```
$ javac -d ../bin ./game/SpaceAdventure.java
```

Copy the images directory to the bin directory.
```
$ cp ./sprites/ ../bin/ -r
```

## Running
Run the program. Must be ran from the bin directory.
```
$ java game/SpaceAdventure
```

**Note** that you can't append .class to the SpaceAdventure-file when executing the game.

![Screenshot of the terminal](images/terminal.png?raw=true "Screenshot of the terminal")

## Different screens in the game

### Start
![Screenshot Start screen](images/startscreen.png?raw=true "Screenshot Start screen")

### Paus menu
![Screenshot Paus menu](images/pausmenu.png?raw=true "Screenshot Paus menu")

### Game over
![Screenshot Game over](images/gameover.png?raw=true "Screenshot Game over")

## Author
[Qulle](https://github.com/qulle/)