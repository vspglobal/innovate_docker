FROM innovate_java
MAINTAINER A D Ministator email: peteja@vsp.com, edha@vsp.com

# Update the image with the latest packages (recommended)
RUN yum update -y; yum clean all

# Add wget 
RUN yum install wget -y

# Get the tomcat binaries

#RUN wget http://artifactory.vsp.com/ext-install-local/apache-tomcat-7.0.52.tar.gz /root
#RUN tar -xvf /root/apache-tomcat-7.0.52.tar.gz /opt/

# Unpack the tomcat binaries


# Docker automatically extracted. So move files to web directory
# RUN mv /tmp/mysite/* /var/www/html

EXPOSE 8080

# ENTRYPOINT /bin/bash
