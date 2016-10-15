# SIS JAVA TEST

Welcome!

## Prerequisites (Initial setup)

To be able to work with the code in this repository, there are some pieces of software that are needed in the local
machine before getting started:

1. [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html): The Oracle version is the recommended
  one.
2. [Maven 3](http://maven.apache.org/download.cgi): To be able to build the code and download its dependencies.  
3. [Docker](http://www.vagrantup.com/downloads.html): We use Vagrant to create a host for a dev local environment.

## Getting Started

So you are now ready to start your local dev environment so move to the root folder of this repository in your local
computer and issue following commands from your command line.

```
$ mvn clean install

```
You should see a `BUILD SUCCESS` at the end of the previous command.

To build the Docker image and start the Docker container run following comands.

$ docker build -t sis-test .
```

```
$ docker run -p 8080:8080 -t sis-test
```


It's going to take a few seconds while Docker container is created and a Tomcat instance is run. You can verify it with the
following command:

```
$ docker ps -a
```

## Resources

 * [Docker's Documentation](https://docs.docker.com/)
