PolyParkTrack
============
fall2016-project-wenmin518

[![Build Status](https://travis-ci.org/cpe305/fall2016-project-wenmin518.svg?branch=master)](https://travis-ci.org/cpe305/fall2016-project-wenmin518)

[Click this linked to see the project github page](https://cpe305.github.io/fall2016-project-wenmin518/)

## Application Usage:
1. Click anywhere in the static Cal Poly campus map to select your starting location
  * program will complain if you do not have a starting location for parking spot find
2. Click on Textfield and input the corresponding car type your have in the field
  * Only valid string(Compact, Electric, Handicap, Normal) and valid integer(1 - 4) are allowed
  * Otherwise, the application complains and ask you for an valid input
3. Click "find the spot button", and the required information to find parking spot is displayed
  * It shows the nearest parking spot in the nearest parking structure with distance and time take to travel

## System Architecture:
* 3-tier architecture:
  * Graphic User Interface (javafx)
  * Business Login (Breath-first search and other calculation methods)
  * Data base (Read in the json file)
  
# Design Pattern:
  * Observer pattern
    * Subject -- User, when user changes his/her current location or car type, the observers will be notified
    * Observer -- Calculation object, receives the changes from subject and restart the required methods to find the nearest parking spot available

## Version

*  1.0.0, the first prototype that works as expected, but need to implemented APIs and Databases in the future.

## Authors
* **Wenmin He** - [github page](https://github.com/wenmin518)
