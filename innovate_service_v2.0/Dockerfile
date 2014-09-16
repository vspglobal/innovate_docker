FROM innovate_java

MAINTAINER peteja@vsp.com, edha@vsp.com

EXPOSE 8080
ADD target/dockerinfo-1.0.0-SNAPSHOT.jar /deployments/

CMD java -Djava.security.egd=file:/dev/./urandom -jar /deployments/dockerinfo-1.0.0-SNAPSHOT.jar 
