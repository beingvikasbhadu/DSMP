package com.example.Proxy.Server.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Value("${my.redis.host}")
	private String redisHost;
	@Value("${my.redis.port}")
	private int redisPort;
	
  @Bean
   public JedisConnectionFactory jedisConnectionFactory()
   {
	   RedisStandaloneConfiguration config=new RedisStandaloneConfiguration();
	   config.setHostName(redisHost);
	   config.setPort(redisPort);
	   
	   JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory(config);
	   return jedisConnectionFactory;
	   
   }
   
  @Bean
   public RedisTemplate<String,String> redisTemplate()
   {
	  RedisTemplate<String,String> redisTemplate=new RedisTemplate<String,String>();
	  redisTemplate.setConnectionFactory(jedisConnectionFactory());
	  redisTemplate.setKeySerializer(new StringRedisSerializer());
	  redisTemplate.setValueSerializer(new StringRedisSerializer());
	  
	  return redisTemplate;
   }
}
