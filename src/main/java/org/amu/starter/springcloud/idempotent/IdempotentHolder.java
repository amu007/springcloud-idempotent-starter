package org.amu.starter.springcloud.idempotent;

public class IdempotentHolder {

//	private static ThreadLocal<String> idempotentKey = new ThreadLocal<>();

	private static ThreadLocal<IdempotentVo> idempotentVo = new ThreadLocal<>();
	
//	public static final Integer STATUS_NONE = 0;
//	public static final Integer STATUS_BEGIN = 1;
//	public static final Integer STATUS_REDIRECT = 2;
//	
//	public static final Integer STATUS_FINISHED = 10;
	

//	public static String getIdempotentKey() {
//		return idempotentKey.get();
//	}
//
//	public static void setIdempotentKey(String idempotentKeyStr) {
//		idempotentKey.set(idempotentKeyStr);
//	}
	
	public static IdempotentVo getIdempotentVo() {
		return idempotentVo.get();
	}

	public static void setIdempotentVo(IdempotentVo idempotentVoOri) {
		idempotentVo.set(idempotentVoOri);
	}

	public static void clear() {
//		idempotentKey.remove();
		idempotentVo.remove();
	}
}
