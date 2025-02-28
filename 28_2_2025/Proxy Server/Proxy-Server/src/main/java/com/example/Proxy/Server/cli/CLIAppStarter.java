package com.example.Proxy.Server.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.Proxy.Server.service.ProxyServerService;

@Component
public class CLIAppStarter implements CommandLineRunner {
   @Autowired	
   ProxyConfig proxyConfig;
   
   @Autowired
   ProxyServerService proxyServerService;
   
   public CLIAppStarter()
   {

//	   System.out.println("Now reading CLIAPPStarter");
   }
	@Override
	public void run(String... args)   {
//		System.out.println("Now reading CLIAPPStarter runs()");
		int size=args.length;
		for(int i=0;i<size;i++)
		{
			if(args[i].equals("--port") && i+1<size)
				proxyConfig.setPort(Integer.parseInt(args[i+1]));
			else if(args[i].equals("--origin") && i+1<size)
				  proxyConfig.setHost(args[i+1]);
			else if(args[i].equals("--clear-cache"))
			{ 
				if(proxyServerService.clearCache())
					System.out.println("Cache cleared!");
				else
					 System.out.println("unable to clear cache due to some redis config mismatch!");
			}
			else if(args[i].equals("--request") && i+1<size)
			{
				String url=args[i+1];
					ResponseEntity<String> response=proxyServerService.getResponse(url);
					System.out.println("Response:"+response);
				
			}
		}
		
	}
      
}
