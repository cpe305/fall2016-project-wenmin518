 PolyParkTrack
===============
 
PolyParkTrack is an idea that I have with the intention to solve the parking jam in Cal Poly. I always heard my friends and roommates complaining about spending too much time in the morning finding a available parking spot. PolyParkTrack is able to solve this problem by keeping track of parking spots that are available for students and allow students in Cal Poly to know exactly where the nearest parking spot is available to them in real time.

## Introduction
This is the prototype of how PolyParkTrack should behave. For this prototype, the application reads in the json file containing required information for each parking structure in. Then the GUI displays number of parking spot in each Parking Structure on cal poly are shown on the right with the format of Parking Structure #: #. The first # referring to the parking structure number, the second # is referring to number of available parking spots in that parking structure. 

Next to the field containing number of parking spots available in each parking structure, is a static cal poly map that allows users to hand pick the location he/she want to start. After picking the location, the user is able to input on the text field the car type he/she wants to find where 1: Compact, 2:Electric, 3: Handicap, 4: Normal. Then the user is able to press "find the spot" button on the right of the screen. After pressing "find the spot" button, a bunch of text on the button of the screen will be shown. The bottom text displays the information containing the nearest parking structure with the closest parking spot that user has picked. Under the parking spot information are the text containing the distance and time takes to reach that parking spot.

### Getting Started
1. This is the starting screen of the PolyParkTrack.
	* Left side of the screen displays number of avialable parking spots in each parking structure.
	* Next to the text, is the static Cal Poly Campus map.
	* Text fields and "find the spot" button is under the map.
	* And text displaying "Welcome to PolyParkTrack" is shown on the bottom of the screen.
	* ![Alt text](/imagefiles/StartScreen.jpg?raw=true "Optional Title")
	
2. Then you are able to pick a starting location in the static map on the right.
	* A transparent circle will be shown on the map if you pick a valid location in the static map.
	* Otherwide, nothing would be happen.
	* ![Alt text](/imagefiles/PickLocation.jpg?raw=true "Optional Title")
	* If you press the "find the spaot" without a starting location, the application will complain about it and display "Pick a starting location".
	* ![Alt text](/imagefiles/NoStartingLoc.jpg?raw=true "Optional Title")
	


3. After picking the location, you are able to enter the parking type for your car, 1: Compact, 2: Electric, 3: Hadicap, 4: Normal as shown below
	* This field is not required to be filled to do find the nearest parking spot, it will be defaulted to be Normal.
	* String or numbers are both valid for car type field
		* 1 or Compact for Compact Car parking spot
		* 2 or Electric for Electric Car Parking spot
		* 3 or Handicap for Handicap Parking spot
		* 4 or Normal for Normal Parking Spot
		* ![Alt text](/imagefiles/PickCarType1.jpg?raw=true "Optional Title")
		* ![Alt text](/imagefiles/PickElectricCarType.jpg?raw=true "Optional Title")
	* The Message "Invalid Car Type" will display if you pick any type other than number and string referring to the car type
	* ![Alt text](/imagefiles/InvalidCarTypeScreen.jpg?raw=true "Optional Title")
	
4. The last step will be pressing the "find the spot" after you have pick the starting location and entered a valid car type.
	* The message will display the location of the nearest parking spot that you try to find and the distance and time it takes to get to the destination.
	* ![Alt text](/imagefiles/PrintTextScreen.jpg?raw=true "Optional Title")
	* ![Alt text](/imagefiles/FindElectricPark.jpg?raw=true "Optional Title")
	


### Implementation
1. Breath-First Search to find the shortest path between User picked location and nearest parking structure
2. Observable pattern
	* User is the subject
		* If user changes the location or changes the car type, then observer will be notified.
	* Calcation is the Observer
		* If user changes the location or car type, then calculation object will find the new nearest parkig type available and display it on the screen.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Framework used
* [Javafx](http://docs.oracle.com/javase/8/javase-clienttechnologies.htm)


## Versioning
*  1.0.0

## Authors

* **Wenmin He** - *Initial work* - [PurpleBooth](https://github.com/wenmin518)
