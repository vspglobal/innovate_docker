The meta-service polls etcd for the list of services and makes it available to clients.

Launch meta-service with the following command:
docker run -i -t -e "POLL_DELAY_MS=1000" -e "POLL_ERROR_DELAY_MS=5000" -e "ETCD_QUERY_URL=http://ec2-54-244-91-57.us-west-2.compute.amazonaws.com:4001/v2/keys/services/innovate_api" -p 10001:8080 --rm --name meta-service innovate_meta_service:v1.0

These are required environment variables: 
* ETCD_QUERY_URL: Defines the query used to poll etcd
* POLL_DELAY_MS: Milliseconds the service waits before requerying etcd after a successfull request.
* POLL_ERROR_DELAY_MS: Milliseconds the service waits before requerying etcd after a failed request.
