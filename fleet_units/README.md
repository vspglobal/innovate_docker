
#Instructions

### How to submit the unit files
```
fleetctl submit innovate_api_v1@.service 
fleetctl submit innovate_api_v2@.service 
```

### How to run the containers

```
fleetctl start innovate_api_v1@{8001..8003}.service 
```

### How to upgrade

```
fleetctl start innovate_api_v2@{8011..8013}.service 
fleetctl stop innovate_api_v1@{8001..8003}.service 

```
### How to scale

```
fleetctl start innovate_api_v2@{8014..8016}.service 
```

### How to fetch the available servers

```
curl -L http://127.0.0.1:4001/v2/keys/services/innovate_api
```

### How to destroy the containers

```
fleetctl destroy innovate_api_v1@.service innovate_api_v2@.service innovate_api_v1@{8001..8003}.service innovate_api_v2@{8011..8013}.service
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
