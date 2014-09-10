package com.vsp.innovate.domain;

import java.net.InetAddress;

public class Info {
	private InetAddress inet;
	private String version;
	private String imageUrl
	;
	public InetAddress getInet() {
		return inet;
	}
	public void setInet(InetAddress inet) {
		this.inet = inet;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
