package cn.saintshaga.example.service;

import cn.saintshaga.example.entity.IUser;
import cn.saintshaga.example.entity.User;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ContactUserRepositoryTests {
    @Configuration
    @EnableCaching
    static class UserRepositoryTestConfiguration {
        @Bean
        public UserRepository userRepository() {
            return new ContactUserRepository();
        }


        @Bean
        public CacheManager cacheManager() throws URISyntaxException {
            CachingProvider cachingProvider = Caching.getCachingProvider();
             return cachingProvider.getCacheManager(
                     getClass().getClassLoader().getResource("ehcache.xml").toURI(),
                     getClass().getClassLoader());
        }

        @Bean
        public JCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
            return new JCacheCacheManager(cacheManager);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, User> cache;

    @Before
    public void initialize() {
        this.cache = cacheManager.getCache(ContactUserRepository.CONTACT_USER_CACHE_NAME);
    }

    @After
    public void destroy() {
        this.cache.clear();
    }

    @Test
    public void noCache_whenStart() {
        assertFalse(cache.containsKey("test"));
    }

    @Test
    public void hasCache_afterCallGetUser() {
        String userId = "test";
        userRepository.getUser(userId);
        assertTrue(cache.containsKey(userId));
    }

    @Test(expected = Exception.class)
    public void dontCache_whenUserIdIsNull() {
        String userId = null;
        userRepository.getUser(userId);
    }

    @Test
    public void cache_whenGetUsers() {
        String userId1 = "test001";
        String userId2 = "test002";
        userRepository.getUser(userId1);
        assertTrue(cache.containsKey(userId1));
        assertFalse(cache.containsKey(userId2));
        Map<String, IUser> results = userRepository.getUsers(Lists.newArrayList(userId1, userId2, null));
        assertTrue(cache.containsKey(userId1));
        assertTrue(cache.containsKey(userId2));
        assertEquals(2, results.size());
    }

    @Test
    public void returnEmpty_whenInputNull() {
        assertTrue(userRepository.getUsers(null).isEmpty());
    }

}
