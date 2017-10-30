package org.amu.starter.springcloud.idempotent.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RedisHashUtil {

	@Autowired
	JedisConnectionFactory jedisConnectionFactory;

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setEnableTransactionSupport(true);
		template.setConnectionFactory(jedisConnectionFactory);
		return template;
	}

	@Autowired
	private RedisTemplate<String, Object> ssRedisTemplate;

	@Value("${bumblebee.expiretime.default.hash:60}")
	private int expireTime = 0;

	private HashOperations<String, String, Object> ssHash;

	@PostConstruct
	public void postConstruct() {
		ssHash = ssRedisTemplate.opsForHash();
	}

	public void putAll(String key, Map<String, Object> map) {
		 ssHash.putAll(key, map);
		 ssRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

//		List<Object> txResults = ssRedisTemplate.execute(new SessionCallback<List<Object>>() {
//			public List<Object> execute(RedisOperations operations) throws DataAccessException {
//				operations.multi();
//				ssHash.putAll(key, map);
//				ssRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//
//				// This will contain the results of all ops in the transaction
//				return operations.exec();
//			}
//		});
	}

	// public void putAll(String key, Map<String, String> map) {
	// ssHash.putAll(key, map);
	// ssResdisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	// }

	public void put(String key, String hashKey, Object value) {
//		List<Object> txResults = ssRedisTemplate.execute(new SessionCallback<List<Object>>() {
//			public List<Object> execute(RedisOperations operations) throws DataAccessException {
//				operations.multi();
				ssHash.put(key, hashKey, value);
				ssRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

//				// This will contain the results of all ops in the transaction
//				return operations.exec();
//			}
//		});
	}

	public boolean lock(String key, String hashKey, Object value) {
//		List<Object> txResults = ssRedisTemplate.execute(new SessionCallback<List<Object>>() {
//			public List<Object> execute(RedisOperations operations) throws DataAccessException {
//				operations.multi();
				boolean rtn = ssHash.putIfAbsent(key, hashKey, value);
				ssRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

				return rtn;
//				// This will contain the results of all ops in the transaction
//				return operations.exec();
//			}
//		});
	}
	public Object get(String key, String hashKey) {
		return ssHash.get(key, hashKey);
	}

	public boolean contains(String key) {
		return ssRedisTemplate.hasKey(key);
	}

	public boolean contains(String key, String hashKey) {
		return ssHash.hasKey(key, hashKey);
	}

	public Map<String, Object> entries(String key) {
		return ssHash.entries(key);
	}
}
