
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
  
  
# Docker registry

The registry has a RESTful interface. Below are some basic commands you can run to search, inspect and delete images.

`GET` 

Search for repositories: `http://gtscloud-0158.vsp.com:5000/v1/search`   
List tags for repository: `http://gtscloud-0158.vsp.com:5000/v1/repositories/{namespace}/{repository}/tags`  
Inspect image: `http://gtscloud-0158.vsp.com:5000/v1/images/{image id}/json`  


`DELETE`

Delete repository: `http://gtscloud-0158.vsp.com:5000/v1/repositories/{namespace}/{repository}`  
Delete tag: `http://gtscloud-0158.vsp.com:5000/v1/repositories/{namespace}/{repository}/tags/{tag}`  
  
  
  
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
