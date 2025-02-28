package com.example.Proxy.Server.cli;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value="my.redis")
public class ProxyConfig {
   private String host;
   private Integer port;
  public ProxyConfig()
   {
//	   System.out.println("Now reading ProxyConfigHHHHHH");
   }
public String getHost() {
	return host;
}
public void setHost(String host) {
	this.host = host;
}
public Integer getPort() {
	return port;
}
public void setPort(Integer port) {
	this.port = port;
}
   
}
