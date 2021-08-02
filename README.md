# SportsBook

This Back-End application provides a solution to manage sports events scores

Points can be added to a team's score, but never subtracted. 

The range of valid points is between 0-9

When trying to add negative points, the application restricts the action and will not update the score. For example:

 					 #######   Team A vs Team B  #######   current score :   0-1

Update new score = 0-0  ---- the score will not be updated, since it is not possible to subtract points

Update new score = 1-1  ---- the score will be updated 

The score 9-9 is the maximum allowed range

The testing works

# Steps to reproduce in order to try out the application

1-. Install MariaDB database

2-. Import and configure the database:

	2.1 Create the database 
	
	2.2 Use the configuration of the application.properties file
	|
	V
	
	username=root
	password=1gradosuperior
	server.port= 8050
	
   See example in the source Example. 
   Files: capture-1.PNG and capture-2.PNG
	

	2.3 Source: The project's SportEventDB folder contains the SQL file to import.
	
3-. Once the database is created, you can import the project and execute it

   	Important: 
   	In SportsBookApplicationTests test class, 
   	you will find the following constant: final private int ID_SPORTEVENT_TO_ANALYSE = 1;
   	the number "1" is the id of the object SportEvent, so before running the application, it must be created in 
   	the database or the testing and build will fail ( because the object would not exist in the data base)
   
   	Uninstall the libary JAVA-11 and Install the default library in module path to fix warnings
   	Disable project specific settings in Java Compiler configuration. Update and save and later enable again with
   	Compiler compliance level 11. Update and Save.
   
   	There should be no more warnings at this stage.
   
   	See example in the source Example. 
   	Capture-3.PNG Capture-4.PNG

	3.1 Build the Maven project: Run as --> Maven Build
	
	3.2 Run the application:  Run as --> Spring Boot App
	
4-. The project works and has no problems? Let's go

    After opening the database, right click on the project and run as Maven build.
    When the build is finished, run the project as Spring Boot app.
	
	 Open your browser and navigate to:  http://localhost:8050/SportsEvents
	
	 Test the Rest API with Postman by sending the desired HTTP requests (GET,POST,PUT and DELETE)
	
    See example in the source Example. 
   
    Get sports events
    Capture-5.PNG 
   
    Get sport event by id
    Capture-6.PNG
   
    Post sport event
    Capture-7.PNG 
   
    Put sport event
    Capture-8.PNG
   
    Delete sport event
    Capture-9.PNG
   
	More details in the project
	
	Thank you !
	
	