package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public static Cache<String, String> cacheObject() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        MutableConfiguration<String, String> mutableConfiguration = new MutableConfiguration<>();
        Cache<String, String> cache1 = cacheManager.createCache("Cache1", mutableConfiguration);
        cacheObject().put("key1", "value1");
        cacheObject().put("key2", "value1");
        System.out.println("values saved in cache");
        System.out.println(cacheObject().get("key1"));
        cacheManager.close();
        return cache1;
    }
}