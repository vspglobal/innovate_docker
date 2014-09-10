package com.vsp.innovate.service;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsp.innovate.conf.InfoSettings;
import com.vsp.innovate.domain.Info;

@Service
public class InfoService{

    @Autowired
    private  InfoSettings info;
    
    private Logger log = Logger.getLogger(InfoService.class);
    public InfoService() {
    	
    }
    
   
    
    public InetAddress getInetAddress(){
    	String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while(interfaces.hasMoreElements()){
	            	NetworkInterface iface = interfaces.nextElement();
	            	log.info("interface: "+iface.getDisplayName());
	            	if ("en0".equals(iface.getDisplayName()) || "eth0".equals(iface.getDisplayName())){
	            		Enumeration<InetAddress> addresses = iface.getInetAddresses();
		               	
		                while(addresses.hasMoreElements()) {
		                	InetAddress addr = addresses.nextElement();
				            if(addr instanceof Inet4Address){    	
		                	ip = addr.getHostAddress();
		                	log.info("ip: "+ip);
		                	return addr;
				            }
		                	
		                }
                		
                	}
	               
                }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }	
    return null;
    }
    
    
    public Info getInfo(){
    	Info info = new Info();
        info.setImageUrl(getImageUrl());
		info.setInet(getInetAddress());
		info.setVersion(getVersion());
		return info;
    }
    public String getImageUrl(){
    	return info.getImageurl();
    }
    public String getVersion(){
    	return info.getVersion();
    }
    }