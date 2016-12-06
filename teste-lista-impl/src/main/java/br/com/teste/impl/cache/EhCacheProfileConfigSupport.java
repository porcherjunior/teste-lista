package br.com.teste.impl.cache;

import net.sf.ehcache.config.CacheConfiguration;

import java.util.List;

public interface EhCacheProfileConfigSupport {

    public List<CacheConfiguration> ehCacheProfilesConfig();

}
