FROM centos:centos6
 
MAINTAINER A D Ministator email: peteja@vsp.com

# Update the image with the latest packages (recommended)
RUN yum update -y; yum clean all

# Install Java
# RUN wget https://artifactory.vsp.com/ext-install-local/jdk-7u25-linux-i586.tar.gz 
# RUN wget http://central.maven.org/maven2/log4j/log4j/1.2.17/log4j-1.2.17.jar -P /tmp

RUN yum install java-1.7.0-openjdk -y
