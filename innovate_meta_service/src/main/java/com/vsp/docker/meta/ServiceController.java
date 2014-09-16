package com.vsp.docker.meta;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ServiceController {
	
	private EtcdReader etcd;

	public ServiceController() {
		
		String urlString = "http://localhost:8000/data/services.json";
		
		etcd = new EtcdReader();
		try {
			etcd.start(urlString);
		} catch (MalformedURLException e) {
			System.err.println("Bad service URL: " + urlString);
			e.printStackTrace();
			SpringApplication.exit(null);
		}
	}

	@RequestMapping(value="/services", produces="application/json")
	public String services() {

		// TODO Add Access-Control-Allow-Origin header!!!
		
		return etcd.getServices();
	}
}
