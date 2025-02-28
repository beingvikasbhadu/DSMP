package com.example.Proxy.Server.DAO;

public interface ProxyServerDAO {
     public String getKey(String url) ;
     public void setKey(String url, String response) throws Exception;
     public boolean clearCache() ;
}
