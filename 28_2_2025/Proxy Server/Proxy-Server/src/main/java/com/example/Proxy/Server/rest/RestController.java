package com.example.Proxy.Server.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestController {
     
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
		
	}
}
