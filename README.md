# SIS JAVA TEST

Welcome!

## Prerequisites (Initial setup)

To be able to work with the code in this repository, there are some pieces of software that are needed in the local
machine before getting started:

1. [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html): The Oracle version is the recommended
  one.
2. [Maven 3](http://maven.apache.org/download.cgi): To be able to build the code and download its dependencies.  
3. [Docker](https://www.docker.com/): We use Docker to build an image and copy the war file into the resulting container which is fully provided with a tomcat installation

## Technologies used:

1. Spring 4.3.2.RELEASE
2. Hibernate
3. JPA
4. Spock as unit test framework
5. Spring security
6. Log4j2
7. Embedded database HSQLDB.


## Getting Started

So you are now ready to start your local dev environment so move to the root folder of this repository in your local
computer and issue following commands from your command line.

```
$ mvn clean install

```
You should see a `BUILD SUCCESS` at the end of the previous command. Is important to run first this command to have the war file generated under target folder.

To build the Docker image and start the Docker container run following comands.

```
$ docker build -t sis-test .

$ docker run -p 8080:8080 -t sis-test
```


It's going to take a few seconds while Docker container is created and a Tomcat instance is run. The port expose is 8080 so you should be able to go to "http://localhost:8080/football-teams-api/". You can also verify the container is running with the
following command:

```
$ docker ps -a
```


## Use of the application

Once the application is running in the docker container you can now start to use the application. There are 3 different endpoints, all of them are secured so it needs to send some details in the headers

For reference you can consult football_teams.sql to see database structure and data.

```
Use below one as valid headers for authorization

X-SIS-UserId :1234-1234-1234-1234
Secret:secret-sis

```
Please find below all the endpoints with a brief description about how to use them:

1. (GET) http://localhost:8080/football-teams-api/teams/list --> it will return a list with all teams existing in the HSQLDB, no parameters are need it apart from header as above indicated.
2. (GET) http://localhost:8080/football-teams-api/teams/1 --> It will return an specific team based on the id passed if it exists otherwise "No Team Found" will be returned
3. (POST) http://localhost:8080/football-teams-api/teams/add --> Create a new team if no id is passed, otherwise it will update the team if already exists or return "No Team found". An example of the body to be sent is attached in the repository under "src/test/resources/datatest/addTeam.json"


With spring security it intercepts all the request and validate against "User" table if values in headers (X-SIS-UserId and Secret) match with any existing entry in this table. If not entry is found then "Access Denied" will be returned.



## Resources

 * [Docker's Documentation](https://docs.docker.com/)
