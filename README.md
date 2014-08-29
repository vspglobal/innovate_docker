
#Instructions

## How to build the images

* Run the `rebuild.sh` script

## How to run the containers

* Detached 

	`docker run -d -t  -p 10001:8080 --name sample innovate_sampleapp`


* With tty

	`docker run -i -t  -p 10001:8080 --name sample innovate_sampleapp /bin/bash`


## Cleaning up containers and images

* run `clean.sh`

## Port forwarding

Port `10001` seems to work. Couldn't get `8080` to work. Run `portforward.sh` to open up for traffic.







## Troubleshooting

Overcoming DNS resolution problems when outside DNS servers are blocked

First run this:
VBoxManage modifyvm boot2docker-vm --natdnshostresolver1 on


Then run this:
boot2docker ssh

In the boot2docker VM run:
sudo /usr/local/etc/init.d/docker stop
sudo /usr/local/etc/init.d/docker start


OR


$ boot2docker ssh
$ sudo udhcpc
$ sudo /etc/init.d/docker restart
