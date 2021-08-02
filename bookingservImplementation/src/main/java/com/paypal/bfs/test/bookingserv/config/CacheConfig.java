package com.paypal.bfs.test.bookingserv.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class CacheConfig {
	
	@Bean
	public Cache<String, String> getCache() {
	 CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
	          .withCache("preConfigured",
	               CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
	                                              ResourcePoolsBuilder.heap(100))
	               .build())
	          .build(true);

	      
return cacheManager.createCache("myCache",
	          CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
	                                        ResourcePoolsBuilder.heap(100)).build());
	}
	    /*  myCache.put(1L, "da one!");
	      String value = myCache.get(1L);

	      cacheManager.close();*/


}
