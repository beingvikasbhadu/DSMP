package com.example.URL.Shortener.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.URL.Shortener.base62.Base62;
import com.example.URL.Shortener.bean.URLBean;
import com.example.URL.Shortener.dao.URLDAOWrapper;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {
    
	@Autowired
	Base62 base62;
	@Autowired
	URLDAOWrapper urlDAOWrapper;
	
	@Override
	public URLBean saveUrlDetail(URLBean bean) throws Exception {
		if(bean.getUrl()==null) return null;
		// first convert the url into short code
		System.out.println("BEAN:"+bean);
		Integer last_id=urlDAOWrapper.getLastInsertedId();
		String shortCode=base62.encodeUrl((last_id==null ? 1: last_id+1));
		System.out.println("ShortURL:"+shortCode);
		bean.setShortCode(shortCode);
		bean.setCreatedAt(new Date());
		bean.setUpdatedAt(new Date());
		bean.setAccessCount(0);
		return urlDAOWrapper.saveEntity(bean);
	}

	@Override
	public URLBean getUrlDetailById(String shortCode) throws Exception {
		// decode 
		Integer id=base62.decodeUrl(shortCode);
		System.out.println("DECODED ID:"+id);
	return	urlDAOWrapper.getEntityById(id);
		
	}

	@Override
	public URLBean updateUrlDetial(URLBean bean) throws Exception {
		
		bean.setUpdatedAt(new Date());
		System.out.println("BEAN:"+bean);
		return urlDAOWrapper.updateEntityByUrl(bean);
	}

	@Override
	public Integer deleteUrlDetail(String shortCode) throws Exception {
		// first decode the shortUrl and get ID
		Integer id=base62.decodeUrl(shortCode);
		return urlDAOWrapper.deleteEntity(id);
	}

}
