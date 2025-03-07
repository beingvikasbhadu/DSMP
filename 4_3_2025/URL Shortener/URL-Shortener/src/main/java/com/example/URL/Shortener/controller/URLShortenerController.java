package com.example.URL.Shortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.URL.Shortener.bean.URLBean;
import com.example.URL.Shortener.service.URLShortenerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class URLShortenerController {
	
	@Autowired
	URLShortenerService urlShortenerService;
	
	@RequestMapping(value="/shorten",method=RequestMethod.POST)
	public ResponseEntity<?> convertUrl(@RequestBody URLBean bean)
	{
		
	 try {
	  URLBean responseBean=urlShortenerService.saveUrlDetail(bean);
	  if(responseBean!=null)
	  {
		  return ResponseEntity.status(HttpStatus.CREATED).body(responseBean);
	  }
	  else
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	  }
	 }
	 catch(Exception e)
	 {
		
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	 }
	}
	
	@RequestMapping(value="/shorten/{shortCode}",method=RequestMethod.GET)
	public ResponseEntity<?> getOriginalUrl(@PathVariable("shortCode") String shortCode, HttpServletResponse response	)
	{
		try {
			System.out.println("shortUrl:"+shortCode);
			URLBean responseBean=urlShortenerService.getUrlDetailById(shortCode);
			if(responseBean!=null)
				{
				String originalURL= responseBean.getUrl(); 
				 response.sendRedirect(originalURL); 
				return ResponseEntity.status(HttpStatus.OK).body(responseBean);
				}
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
			
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@RequestMapping(value="/shorten/{shortCode}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateOriginalUrl(@RequestBody URLBean bean, @PathVariable("shortCode") String shortCode)
	{
		System.out.println("IN PUT METHOD");
		try {
			bean.setShortCode(shortCode);
			URLBean responseBean=urlShortenerService.updateUrlDetial(bean);
			if(responseBean!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseBean);
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@RequestMapping(value="/shorten/{shortCode}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteDetail(@PathVariable("shortCode") String shortCode)
	{
		try {
			int hit=urlShortenerService.deleteUrlDetail(shortCode);
			if(hit!=0)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	


}
