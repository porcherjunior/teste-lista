package br.com.teste.impl;

import br.com.teste.impl.cache.KeyGeneratorByMethodName;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.SizeOfPolicyConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheModule implements CachingConfigurer {

    @Value("${cache.default.sizeMB:100}")
    private Integer defaultCacheSize;

    @Value("${cache.default.timeToIdleSeconds:3600}")
    private Integer defaultTimeToIdleSeconds;

    @Value("${cache.default.timeToLiveSeconds:3600}")
    private Integer defaultTimeToLiveSeconds;

    @Value("#{getObject('ehCacheProfilesConfig')}")
    private List<CacheConfiguration> ehCacheProfilesConfig;

    public CacheConfiguration defaultEhCacheProfileConfig() {
        return new CacheConfiguration("defaultCache", 0)
                .maxBytesLocalHeap(defaultCacheSize, MemoryUnit.MEGABYTES)
                .eternal(false)
                .timeToIdleSeconds(defaultTimeToIdleSeconds)
                .timeToLiveSeconds(defaultTimeToLiveSeconds)
                .memoryStoreEvictionPolicy("LRU")
                .sizeOfPolicy(new SizeOfPolicyConfiguration().maxDepth(5000))
                .statistics(true);
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        if (ehCacheProfilesConfig == null) ehCacheProfilesConfig = new ArrayList();
        ehCacheProfilesConfig.add(defaultEhCacheProfileConfig());
        ehCacheProfilesConfig.stream().forEach(configuration::cache);
        return new net.sf.ehcache.CacheManager(configuration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGeneratorByMethodName();
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        SimpleCacheResolver simpleCacheResolver = new SimpleCacheResolver();
        simpleCacheResolver.setCacheManager(cacheManager());
        return simpleCacheResolver;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

}
