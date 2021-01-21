# COVID-ilness_runner
Computer Game 


[![Watch the video](https://github.com/AndreyShor/COVID-ilness_runner/blob/main/screen.jpg)](https://youtu.be/oRZkaqdNQos)

## Overview
The main idea of the game is to create an arcade game in which the main character
will shrink from the virus keeping a 2 meter distance and look for face masks and
antiseptics during the game to increase health

## Game Architecture

![alt text](https://github.com/AndreyShor/COVID-ilness_runner/blob/main/arcitecture.jpg?raw=true)

**GUI classes**

Main GUI - display main menu and start program
Instruction GUI - display menu with instructions

**Control classes**

menuButton - controller and generator of menu buttons
gameControll - control event on a scene during game play process
mainCharacter - control movement of main character on a screen

**Factory classes**

objectFactory - create Randomly element on scene
gameObject - abstract method create observable to control alive or dead state of the
objects
///////////
Trees - tree game object
Bench - bench game object
BigHouse - big house game object
SmallHouse - small house game object
SingleVirus - create enemy single object
……
////////

**Loaders classes**

SoundLoader - load all sound effects for a game
SpriteLoaderGame - load all sprites for game
SpriteLoaderMenu - load all sprites for menu

**Animation class**

MainMenuAnimation - create animation for menu logo
style.css - add css styles inside the application

## Factory Pattern

This template delegates the creation of objects to the heirs of the parent class
(objectFactory ). This allows you to use in the program code not specific classes, but
to manipulate abstract objects (gameObject ) at a higher level.

## MVC

Model-View-Controller - a scheme for dividing application data, user interface and
control logic into three separate components: model, view and controller - in such a
way that modification of each component can be carried out independently


In my case,
- model - Factory classes
- view - GUI classes
- controller - Control classes

## Potential application improvements by adding a database

One of the potential uses of Db in this application is to create a best player list to
show players with a high score. It can be done this way.

```java
// package code
java.sql
// variable for connection
private Connection connection
// save connection
connection = DriverManager.getConnection(dbURL, user, password);
// request SQL query from server
Statement stmnt = connection.createStatement(); ResultSet rs =
stmnt.executeQuery("select * from person");
// or insert player query is
// INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
```
