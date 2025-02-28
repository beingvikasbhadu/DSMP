package com.example.Proxy.Server.service;

import org.springframework.http.ResponseEntity;

public interface ProxyServerService {
   public ResponseEntity<String> getResponse(String url) ;
   public boolean clearCache() ;
}
