package com.example.URL.Shortener.base62;

import org.springframework.stereotype.Component;

@Component
public class Base62 {
	private int base=62;
	private String characters="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public String encodeUrl(Integer id)
	{
		
		String encodedUrl="";
		while(id!=0)
		{
			int rem=id%base;
			id/=62;
			encodedUrl=characters.charAt(rem)+encodedUrl;
		}
		return encodedUrl;
	}
     
	public Integer decodeUrl(String url)
	{
		int base=62;
		Integer id=0;
		
		int k=url.length();
		
		for(int i=k-1;i>=0;i--)
		{
			char ch=url.charAt(i);
			int value=characters.indexOf(ch);
			int mul=(int) Math.pow(base,k-i-1);
			
			id+=(mul*value);
		}
		return id;
	}
	
}
