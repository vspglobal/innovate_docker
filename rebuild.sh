
docker build --rm=true -t="innovate_base" ./innovate_base/
docker build --rm=true -t="innovate_java" ./innovate_java/
docker build --rm=true -t="innovate_service:v1.0" ./innovate_service_v1.0/
docker build --rm=true -t="innovate_service:v2.0" ./innovate_service_v2.0/
docker build --rm=true -t="innovate_meta_service:v1.0" ./innovate_meta_service/

#docker build --rm=true -t="innovate_tomcat" ./innovate_tomcat/
#docker build --rm=true -t="innovate_sampleapp" ./innovate_sampleapp/

