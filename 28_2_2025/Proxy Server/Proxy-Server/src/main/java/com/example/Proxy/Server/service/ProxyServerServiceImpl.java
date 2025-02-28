package com.example.Proxy.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Proxy.Server.DAO.ProxyServerDAO;

@Service
public class ProxyServerServiceImpl implements ProxyServerService {

	@Autowired
	ProxyServerDAO proxyServerDAO;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> getResponse(String url)  {

		// checking is there hit or miss in redis cache

		try{
			String value = proxyServerDAO.getKey(url);
        
		ResponseEntity<String> fetched_data=fetchData(url);
		// if miss
		if (value == null) {
			// now forward request to origin server
//			Object fetched_data = restTemplate.getForObject(url, String.class);
			
			if (fetched_data == null)
				return ResponseEntity.notFound().header("X-Cache", "MISS").build();
			proxyServerDAO.setKey(url, fetched_data.getBody());

			return ResponseEntity.ok().header("X-Cache", "MISS").contentType(MediaType.APPLICATION_JSON).body(fetched_data.getBody());
		}
		
		// else
		else {
			return ResponseEntity.ok().header("X-Cache", "HIT").contentType(MediaType.APPLICATION_JSON).body(value);
		}
		}
		catch(Exception e)
		{
			System.out.println("Now redis not running!!!");
			ResponseEntity<String> fetched_data=fetchData(url);
			if (fetched_data == null)
				return ResponseEntity.notFound().header("X-Cache", "Not Working").build();
			

			return ResponseEntity.ok().header("X-Cache", "Not Working").contentType(MediaType.APPLICATION_JSON).body(fetched_data.getBody());
			
		}
	}

	@Override
	public boolean clearCache()  {
		return proxyServerDAO.clearCache();
		
	}
	
	public ResponseEntity<String> fetchData(String url)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
		headers.set("Accept", "application/json");
		headers.set("Cache-Control", "no-cache");

		// Add authentication headers if required (e.g., API Key)
		// headers.set("Authorization", "Bearer YOUR_API_KEY");

		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Send request using exchange() for better error handling
		return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	}
	

}
