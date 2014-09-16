package com.vsp.docker.meta;

public class EtcdReader {
	
	private volatile String svcCache = "[]";

	public EtcdReader() {
		// TODO start etcd reader job
		// Long poll etcd and cache the results
		new Thread(new Runnable() {
			public void run() {
				svcCache = "[{\"foo\":\"bar\", \"timestamp\":\""
					+ System.currentTimeMillis() + "\"}]";
			}
		}).start();
	}

	public String getServices() {
		return svcCache;
	}
}
