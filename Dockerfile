# Pull base image
From tomcat:8-jre8

MAINTAINER Alberto Mezcua (alberto.mezcua@gmail.com)

COPY 		./target/football-teams-api.war /usr/local/tomcat/webapps/football-teams-api.war