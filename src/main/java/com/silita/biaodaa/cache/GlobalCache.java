package com.silita.biaodaa.cache;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局缓存.
 *
 */
public class GlobalCache {

	protected Logger logger = Logger.getLogger(getClass());

	private static GlobalCache globalCache = new GlobalCache();

	private Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap;

	private GlobalCache() {
		analyzeRangeByFieldMap = new ConcurrentHashMap<>();
	}


	public static GlobalCache getGlobalCache() {
		return globalCache;
	}

	public Map<String, List<Map<String, Object>>> getAnalyzeRangeByFieldMap() {
		return analyzeRangeByFieldMap;
	}

	public void setAnalyzeRangeByFieldMap(Map<String, List<Map<String, Object>>> analyzeRangeByFieldMap) {
		this.analyzeRangeByFieldMap = analyzeRangeByFieldMap;
	}
}
