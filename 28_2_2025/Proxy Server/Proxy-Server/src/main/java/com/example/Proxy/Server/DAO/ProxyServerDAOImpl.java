package com.example.Proxy.Server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProxyServerDAOImpl implements ProxyServerDAO {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Override
	public String getKey(String key){
		try {
		return redisTemplate.opsForValue().get(key);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public void setKey(String url, String response)throws Exception {
		redisTemplate.opsForValue().set(url,response);
		
	}

	@Override
	public boolean clearCache()  {
		try {
		redisTemplate.getConnectionFactory().getConnection().flushAll();	
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
