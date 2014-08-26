FROM innovate_java
MAINTAINER A D Ministator email: peteja@vsp.com

# Update the image with the latest packages (recommended)
RUN yum update -y; yum clean all

# Install Apache Web Server
RUN yum install -y git; yum clean all

# Add the tar file of the web site 
# ADD ./mysite.tar /tmp/

# Docker automatically extracted. So move files to web directory
# RUN mv /tmp/mysite/* /var/www/html

# EXPOSE 80

# ENTRYPOINT /bin/bash
