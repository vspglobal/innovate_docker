FROM innovate_java
MAINTAINER A D Ministator email: peteja@vsp.com, edha@vsp.com

# Update the image with the latest packages (recommended)
RUN yum update -y; yum clean all


# Get the tomcat binaries

RUN wget http://artifactory.vsp.com/ext-install-local/apache-tomcat-7.0.52.tar.gz -P /root/

# Unpack the tomcat binaries
RUN tar -xvf /root/apache-tomcat-7.0.52.tar.gz -C /opt/



# EXPOSE 8080

# ENTRYPOINT /bin/bash
