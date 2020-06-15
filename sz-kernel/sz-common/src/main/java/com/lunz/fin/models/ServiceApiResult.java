package com.lunz.fin.models;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:59:27
 */
public class ServiceApiResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public ServiceApiResult() {
		put("code", 0);
		put("state",true);
		put("msg", "success");
	}
	
	public static ServiceApiResult error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ServiceApiResult error(String msg) {
		return error(500, msg);
	}
	
	public static ServiceApiResult error(int code, String msg) {
		ServiceApiResult r = new ServiceApiResult();
		r.put("code", code);
		r.put("state",false);
		r.put("msg", msg);
		return r;
	}

	public static ServiceApiResult ok(String msg) {
		ServiceApiResult r = new ServiceApiResult();
		r.put("msg", msg);
		return r;
	}
	public static ServiceApiResult ok(String msg, String appid) {
		ServiceApiResult r = new ServiceApiResult();
		r.put("msg", msg);
		r.put("appid", appid);
		return r;
	}
	
	public static ServiceApiResult ok(Map<String, Object> map) {
		ServiceApiResult r = new ServiceApiResult();
		r.putAll(map);
		return r;
	}
	
	public static ServiceApiResult ok() {
		return new ServiceApiResult();
	}

	@Override
	public ServiceApiResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public static ServiceApiResult toMap(String key, Object value) {
		ServiceApiResult r = new ServiceApiResult();
		r.put(key, value);
		return r;
		
	}

}
