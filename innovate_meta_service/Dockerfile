FROM innovate_java

MAINTAINER john.petersen@vsp.com, peteja@vsp.com, edha@vsp.com

EXPOSE 8080
ADD target/meta-service-1.0-SNAPSHOT.jar /deployments/

CMD java -Djava.security.egd=file:/dev/./urandom -jar /deployments/meta-service-1.0-SNAPSHOT.jar 
