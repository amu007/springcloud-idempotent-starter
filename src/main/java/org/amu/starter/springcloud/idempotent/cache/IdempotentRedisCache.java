package org.amu.starter.springcloud.idempotent.cache;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.amu.starter.springcloud.idempotent.IdempotentVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdempotentRedisCache implements IdempotentCacheInterface {
	
	@Autowired
	private RedisHashUtil redisHashUtil;
	
	@Override
	public void setCache(String key, IdempotentVo idempotentVo) {
		Map<String, Object> map = null;
		
		map = BumblebeeBeanUtils.transBean2Map(idempotentVo);
	
		redisHashUtil.putAll(key, map);
	}

	@Override
	public IdempotentVo get(String key) {
		IdempotentVo idempotentVo = new IdempotentVo();
		Map<String, Object> map = redisHashUtil.entries(key);
		try {
			BeanUtils.copyProperties(idempotentVo, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return idempotentVo;
	}

	@Override
	public boolean lock(String key, IdempotentVo idempotentVo) {
		boolean isCreatedLock =  redisHashUtil.lock(key, "idempotentKey", idempotentVo.getIdempotentKey());
		setCache(key, idempotentVo);
		return isCreatedLock;
	}

}
