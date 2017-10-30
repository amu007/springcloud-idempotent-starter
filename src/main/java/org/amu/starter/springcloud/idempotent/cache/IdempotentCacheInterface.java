package org.amu.starter.springcloud.idempotent.cache;

import org.amu.starter.springcloud.idempotent.IdempotentVo;

public interface IdempotentCacheInterface {
	public void setCache(String key, IdempotentVo idempotentVo);
	
	public IdempotentVo get(String key) throws RuntimeException;
	
	public boolean lock(String key, IdempotentVo idempotentVo);
}
