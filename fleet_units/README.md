
#Instructions

### How to run the containers

```
fleetctl submit innovate_api@.service 
fleetctl start innovate_api@{8001..8010}.service 
```

### How to fetch the available servers

```
curl -L http://127.0.0.1:4001/v2/keys/services/innovate_api
```

### How to destroy the containers

```
fleetctl destroy innovate_api@{8001..8010}.service 
```

### How to create EC2 instances

Use the following user data replacing <Discovery URL> with a new discovery URL from https://discovery.etcd.io/new 

```
#cloud-config

coreos:
  etcd:
    discovery: <Discovery URL>
    addr: $private_ipv4:4001
    peer-addr: $private_ipv4:7001
  units:
    - name: etcd.service
      command: start
    - name: fleet.service
      command: start
    - name: format-ephemeral.service
      command: start
      content: |
        [Unit]
        Description=Formats the ephemeral drive
        [Service]
        Type=oneshot
        RemainAfterExit=yes
        ExecStart=/usr/sbin/wipefs -f /dev/xvdb
        ExecStart=/usr/sbin/mkfs.btrfs -f /dev/xvdb -b 16106127360
    - name: var-lib-docker.mount
      command: start
      content: |
        [Unit]
        Description=Mount ephemeral to /var/lib/docker
        Requires=format-ephemeral.service
        After=format-ephemeral.service
        Before=docker.service
        [Mount]
        What=/dev/xvdb
        Where=/var/lib/docker
        Type=btrfs

```
