
#Instructions

### How to build the images

* Run the `rebuild.sh` script

### How to run the containers

* Detached 

	`docker run -d -t  -p 10001:8080 --name sample innovate_sampleapp`


* With tty

	`docker run -i -t  -p 10001:8080 --name sample innovate_sampleapp /bin/bash`


### Cleaning up containers and images

* run `clean.sh`

### Port forwarding

Port `10001` seems to work. Couldn't get `8080` to work. Run `portforward.sh` to open up for traffic.

# Use Quay.IO for registry

`docker tag innovate_service:v2.0 quay.io/vspglobal/innovate_service:v2.0`  
`docker push quay.io/vspglobal/innovate_service:v2.0`
  
# Docker registry

The registry has a RESTful interface. Below are some basic commands you can run to search, inspect and delete images.

`GET` 

Search for repositories: `http://gtscloud-0158.vsp.com:5000/v1/search`   
List tags for repository: `http://gtscloud-0158.vsp.com:5000/v1/repositories/{namespace}/{repository}/tags`  
Inspect image: `http://gtscloud-0158.vsp.com:5000/v1/images/{image id}/json`  


`DELETE`

Delete repository: `http://gtscloud-0158.vsp.com:5000/v1/repositories/{namespace}/{repository}`  
Delete tag: `http://gtscloud-0158.vsp.com:5000/v1/repositories/{namespace}/{repository}/tags/{tag}`  
  
`Push to registry`

Tag first: `docker tag innovate_service:v1.0 gtscloud-0158:5000/vspglobal/innovate_service:v1.0`  
Then push: `docker push gtscloud-0158:5000/vspglobal/innovate_service:v1.0`    

# Building docker images with Jenkins  
 
Make sure that docker is installed on the VM where Jenkins is running (or some other machine, but then keep in mind that the URL below will change to reflect that. Example below assumes that Docker is running on the same VM as Jenkins)  

 
### Docker 

We need to make sure that the Docker REST service is started. Do this by starting up Docker deamon with the following command  
  
`/usr/bin/docker -H tcp://127.0.0.1:4243 -H unix:///var/run/docker.sock -d &` 
  
or set  
  
`DOCKER_OPTS='-H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock'`  
  
and then restart Docker  
  
`service docker restart`
  
  
### In Jenkins  
 
* Install the `Docker build steps` plugin 
* In the `Manage Jenkins` section, set `DOCKER_URL` to `http://127.0.0.1:4243`   
* Create a new job that builds the artifact    
* Create a new job that builds the image  

I found that the docker plugin is somewhat flakey (could be me not fully understanding how it works yet also), and I was unable to get it to run as a build step in an existing job. I had to instead create a separate job that only created the image from the artifact that was built in the previous job. Doing a pipeline build with both jobs firing one after the other would work well here.   
   
Another useful tip: The `create image` build step assumes that the local Docker repo already has the parent image, or it will fail. Either manually pull the parent image in the cli, or run the `pull image` build step (although, wasn't able to get this to work yet).  


### Useful links  
  
* http://www.virtuallyghetto.com/2014/07/quick-tip-how-to-enable-docker-remote-api.html
* http://blog.trifork.com/2013/12/24/docker-from-a-distance-the-remote-api/  
 
# Troubleshooting

Overcoming DNS resolution problems when outside DNS servers are blocked  

First run this:
`VBoxManage modifyvm boot2docker-vm --natdnshostresolver1 on`  

  
Then run this:  
`boot2docker ssh`  

In the boot2docker VM run:  
  
````
sudo /usr/local/etc/init.d/docker stop  
sudo /usr/local/etc/init.d/docker start  
````

OR

````
$ boot2docker ssh
$ sudo udhcpc
$ sudo /etc/init.d/docker restart
````

# Jenkins:
`http://ec2-54-244-91-57.us-west-2.compute.amazonaws.com:81`


