	
PolyParkTrack

	PolyParkTrack is an idea that I have with the intention to solve the parking jam in Cal Poly. I always heard my friends and roommates complaining about spending too much time in the morning finding a available parking spot. PolyParkTrack is able to solve this problem by keeping track of parking spots that are available for students and allow students in Cal Poly to know exactly where the nearest parking spot is available to them.

Getting Started
	
	This is the prototype of how PolyParkTrack should behave. For this prototype, the application reads in the json file containing required information for each parking structure in. Then the GUI displays number of parking spot in each Parking Structure on cal poly are shown on the right with the format of Parking Structure #: #. The first # referring to the parking structure number, the second # is referring to number of available parking spots in that parking structure. 
	Next to the field containing number of parking spots available in each parking structure, is a static cal poly map that allows users to hand pick the location he/she want to start. After picking the location, the user is able to input on the text field the car type he/she wants to find where 1: Compact, 2:Electric, 3: Handicap, 4: Normal. Then the user is able to press "find the spot" button on the right of the screen. After pressing "find the spot" button, a bunch of text on the button of the screen will be shown. The bottom text displays the information containing the nearest parking structure with the closest parking spot that user has picked. Under the parking spot information are the text containing the distance and time takes to reach that parking spot.
	
	1: User is able to pick a location he/she desire to start on the static cal poly map 
	![alt tag](https://github.com/cpe305/fall2016-project-wenmin518/blob/master/parking.jpg)
	
Built With

	Javafx - Graphic User Interface	
	Maven - Dependency Management

Versioning
	1.0.0

Author
	Wenmin He

License
	Do not use it, It does not work with API now