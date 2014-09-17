package com.vsp.docker.meta;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ServiceController {
	
	private EtcdReader etcd;

	private final String urlString = System.getenv("ETCD_QUERY_URL");
	private final long delay = Long.parseLong(System.getenv("POLL_DELAY_MS"));
	private final long errorDelay = Long.parseLong(System.getenv("POLL_ERROR_DELAY_MS"));

	public ServiceController() {
    System.out.println("ETCD_QUERY_URL:"+urlString);
		etcd = new EtcdReader();
		try {
			etcd.start(urlString, delay, errorDelay);
		} catch (MalformedURLException e) {
			System.err.println("FATALY Bad service URL: " + urlString);
			e.printStackTrace();
			SpringApplication.exit(null);
		}
	}

	@RequestMapping(value="/services", produces="application/json")
	public String services() {
		return etcd.getServices();
	}
}
