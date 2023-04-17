# ee417_group_project
Group Project for EE417, Web App for Restaurant KlassyCafe

To install the provided web application for the restaraunt KlassyCafe there is either a WAR file provided
that can be deployed to a Tomcat server or the source code is provided. There are 2 options for this to be used.

#################################################################################################################
##						Tomcat Deployment						#
#################################################################################################################
The WAR file is compiled and able to be deployed to a Tomcat server. If using this option, the file is currently 
deployed to the EC2 instance of Tomcat 10 provided by Dr Ali Initzar. The user can then use the front end files 
labelled "front_end_EC2" to interface with this implementation. These files correctly implement the POST and GET 
requests to use the EC2 Tomcat 10 instance.
This has been tested and works correctly as of 17/4/23, the server may encounter issue which may result in a 
reboot or removal of WAR files, if this is the case, the user can redeploy the WAR file and the default name 
loaded upon deployment will allow for the files to interface correctly with the necessary services.

The user can also deploy to a local instance of Tomcat 10 to run the backend, if this is the case ensure that it 
is loaded to Port 8080 and the front end files are labelled "front_end_local". these files will implement the GET 
and POST requests for localhost:8080 rather than pointing to the external server as in the EC2 files. 

#################################################################################################################
##						Maven Project Deployment					#
#################################################################################################################
Alternatively to the 2 discussed methods, the maven project can be imported into Eclipse and run locally as a Java 
project, as long as the connection to the EC2 MySQL database is valid, this will run the backend services correctly.
To use this implementation ensure to use the files labelled "front_end_local" for the front end files, this will 
again ensure that POST and GET requests are pointed toward the localhost and not the EC2 server.