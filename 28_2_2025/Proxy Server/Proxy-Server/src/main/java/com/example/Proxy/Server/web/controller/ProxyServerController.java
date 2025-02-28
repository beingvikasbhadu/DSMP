package com.example.Proxy.Server.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Proxy.Server.service.ProxyServerService;

@RestController
public class ProxyServerController {
    @Autowired
    ProxyServerService proxyServerService;
    
    @RequestMapping(value="/clear",method=RequestMethod.GET)
    public ResponseEntity<String> clearCache() throws Exception
    {
    	if(proxyServerService.clearCache())
    	return ResponseEntity.status(HttpStatus.OK).body("redis cache cleared!");
    	else 
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wrong redis server");
    }
    
	@RequestMapping(value="/**",method=RequestMethod.GET)
	public ResponseEntity<String>  handler(HttpServletRequest request) throws Exception
	{
		// parse uri
		String uri=request.getRequestURI();
		String query=request.getQueryString();
		
		
		String url=("https://"+uri.substring(1)+(query!=null ? "?"+query :""));
		System.out.println("url"+url);
		ResponseEntity<String> response=proxyServerService.getResponse(url);
		System.out.println("Response Headers: " + response.getHeaders()); // Debugging
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e)
	{
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:"+e.getMessage());
	}
	
}
