package com.hieubq.ServiceIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.hieubq.Service.CachingService;

@Service
public class CachingServiceIml implements CachingService{
	@Autowired
	CacheManager cacheManager;

	@Override
	public void evictAllCaches() {
		cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}

}
