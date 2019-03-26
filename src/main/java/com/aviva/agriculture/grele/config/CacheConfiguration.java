package com.aviva.agriculture.grele.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.aviva.agriculture.grele.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Localisation.class.getName(), jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Culture.class.getName(), jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Culture.class.getName() + ".parcelles", jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Culture.class.getName() + ".garanties", jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Parcelle.class.getName(), jcacheConfiguration);
            cm.createCache(com.aviva.agriculture.grele.domain.Garantie.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
