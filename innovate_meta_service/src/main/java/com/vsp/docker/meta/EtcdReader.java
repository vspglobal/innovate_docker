package com.vsp.docker.meta;

import java.net.MalformedURLException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EtcdReader {

	private volatile String svcCache = "";

	public void start(final String urlString, final long delay,
			final long errorDelay) throws MalformedURLException {
System.out.println("ETC URL -> "+urlString);
		new Thread(new Runnable() {
			private boolean keepGoing = true;

			public void run() {
				RestTemplate rest = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.add("Accept", "application/json");
				HttpEntity<String> rqst = new HttpEntity<String>("", headers);

				while (keepGoing) {
					ResponseEntity<String> resp = rest.exchange(urlString,
							HttpMethod.GET, rqst, String.class);

					if (resp.getStatusCode().is2xxSuccessful()) {
						if (resp.hasBody()) {
							svcCache = resp.getBody();
						}
					} else {
						System.err.println(resp.getStatusCode().toString());
						sleep(errorDelay);
					}
					sleep(delay);
				}
				System.err.println("EtcdReader exiting!");
			}

			private void sleep(long ms) {
				try {
					Thread.sleep(ms);
				} catch (InterruptedException e) {
					keepGoing = false;
				}
			}
		}).start();
	}

	public String getServices() {
		return svcCache;
	}
}
