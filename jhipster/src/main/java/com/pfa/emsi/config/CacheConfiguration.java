package com.pfa.emsi.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.pfa.emsi.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.pfa.emsi.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.pfa.emsi.domain.User.class.getName());
            createCache(cm, com.pfa.emsi.domain.Authority.class.getName());
            createCache(cm, com.pfa.emsi.domain.User.class.getName() + ".authorities");
            createCache(cm, com.pfa.emsi.domain.EspaceVert.class.getName());
            createCache(cm, com.pfa.emsi.domain.EspaceVert.class.getName() + ".zones");
            createCache(cm, com.pfa.emsi.domain.Zone.class.getName());
            createCache(cm, com.pfa.emsi.domain.Zone.class.getName() + ".arrosages");
            createCache(cm, com.pfa.emsi.domain.Zone.class.getName() + ".plantations");
            createCache(cm, com.pfa.emsi.domain.Zone.class.getName() + ".grandeurs");
            createCache(cm, com.pfa.emsi.domain.TypeSol.class.getName());
            createCache(cm, com.pfa.emsi.domain.Plante.class.getName());
            createCache(cm, com.pfa.emsi.domain.Utilisateur.class.getName());
            createCache(cm, com.pfa.emsi.domain.Capteur.class.getName());
            createCache(cm, com.pfa.emsi.domain.Boitier.class.getName());
            createCache(cm, com.pfa.emsi.domain.Boitier.class.getName() + ".zones");
            createCache(cm, com.pfa.emsi.domain.Boitier.class.getName() + ".connectes");
            createCache(cm, com.pfa.emsi.domain.Grandeur.class.getName());
            createCache(cm, com.pfa.emsi.domain.TypePlante.class.getName());
            createCache(cm, com.pfa.emsi.domain.Plantation.class.getName());
            createCache(cm, com.pfa.emsi.domain.Arrosage.class.getName());
            createCache(cm, com.pfa.emsi.domain.Connecte.class.getName());
            createCache(cm, com.pfa.emsi.domain.Installation.class.getName());
            createCache(cm, com.pfa.emsi.domain.ExtraUser.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
