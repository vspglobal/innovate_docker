
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
