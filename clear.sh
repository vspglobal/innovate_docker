
docker ps -a | awk '{print $1}' | xargs docker rm
docker images | grep -E 'innovate|none' |  awk '{print $3}' | xargs docker rmi

