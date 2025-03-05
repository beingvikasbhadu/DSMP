package com.example.URL.Shortener.service;

import com.example.URL.Shortener.bean.URLBean;

public interface URLShortenerService {
    public  URLBean saveUrlDetail(URLBean bean) throws Exception;
    public  URLBean getUrlDetailById(String shortCode) throws Exception;
    public URLBean updateUrlDetial(URLBean bean) throws Exception;
    public Integer deleteUrlDetail(String url) throws Exception;
    
}
