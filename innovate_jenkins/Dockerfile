FROM jenkins:1.554
MAINTAINER A D Ministator email: peteja@vsp.com, edha@vsp.com

# Add docker 
USER root
RUN apt-get -y install docker.io
RUN ln -sf /usr/bin/docker.io /usr/local/bin/docker

USER jenkins
