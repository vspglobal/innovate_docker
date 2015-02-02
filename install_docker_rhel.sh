#!/bin/sh
# Install Docker on RHEL 6.x
# First make sure you are on the latest version of 6.x. To do this first run "rhn_register". It will ask you to register with redhat. Input your redhat credentials. Once done, you can download the latest rpms using "yum upgrade"

# Once done run the following script:

cd /usr/src
wget http://dl.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm
rpm -ivh epel-release-6-8.noarch.rpm

yum install -y docker-io

DOCKER_OPTS='-H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock'
service docker restart

