package com.vsp.innovate.web;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vsp.innovate.domain.Info;
import com.vsp.innovate.service.InfoService;

@RestController
@RequestMapping(value="/")
public class InfoRestController {
	
	@Autowired
    private InfoService service;
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
    public Info getInfo() {
       return service.getInfo();
    }
	
	@RequestMapping(value="/inet", method=RequestMethod.GET)
    public InetAddress getInetAddress() {
        return service.getInetAddress();
    }
	
	@RequestMapping(value="/image", method=RequestMethod.GET)
    public String getImageUrl() {
        return service.getImageUrl();
    }

	
}
