package com.vsp.docker.meta;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ServiceController {
	
	private EtcdReader etcd;

	public ServiceController() {
		etcd = new EtcdReader();
	}

	@RequestMapping(value="/services", produces="application/json")
	public String services() {

		// This needs the RestEasy treatment...

		return etcd.getServices();
	}
}
