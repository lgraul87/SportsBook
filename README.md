# SportsBook API

This Back-End application provides a solution to manage sports events scores

Points can be added to a team's score, but never subtracted. 

When trying to add negative points, the application restricts the action and will not update the score. For example:

 					 #######   Team A vs Team B  #######   current score :   0-1

Update new score = 0-0  ---- the score will not be updated, since it is not possible to subtract points

Update new score = 1-1  ---- the score will be updated 

The testing works

# Steps to reproduce in order to try out the application

# 1-. Install MariaDB database

	https://bit.ly/3ii49i4


# 2-. Import and configure the database:

# 2.1 Create the database
	
name: sportevent 
	
# 2.2 Use the configuration of the application.properties file or change for valid values.

username=root ;
password=1gradosuperior ;
server.port= 8050 ;
	
See example in the source example. 
Files: capture-1.PNG and capture-2.PNG

# 2.3 The SQL file to import.
Source: SportEventDB
	
# 3-. Once the database is created, you can import the project and execute it

Url: https://github.com/lgraul87/SportsBook/tree/Main
		
You can download zip, extract and import the project.
   
Uninstall the library JAVA-11 and Install the default library in module path to fix warnings
Disable project specific settings in Java Compiler configuration. Update and save, and later enable again with
Compiler compliance level 11. Update and Save.
   
There should be no more warnings at this stage.
   
See example in the source example. 
Files: capture-3.PNG capture-4.PNG

# 3.1 Build the Maven project: Run as --> Maven Build or Coverage as for look the results of test (99 until 100 percent)

After opening the database, right click on the project and run as Maven build.
When the build is finished, run the project as Spring Boot app.

# 3.2 Run the application:  Run as --> Spring Boot App
	
# 4-. The project works and has no problems? Let's go

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
	
	