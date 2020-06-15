/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lunz.fin.config.conf.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-17 21:12
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;
    @Resource(name="redisTemplate")
    private HashOperations<String, String, Object> hashOperations;
    @Resource(name="redisTemplate")
    private ListOperations<String, Object> listOperations;
    @Resource(name="redisTemplate")
    private SetOperations<String, Object> setOperations;
    @Resource(name="redisTemplate")
    private ZSetOperations<String, Object> zSetOperations;
    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;
    /**  登录过期时长*/
    public final static long LOGIN_EXPIRE = 60 * 15;

    /** 
     * 普通缓存放入并设置时间 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期 
     * @return true成功 false 失败 
     */  
    public void setStr(String key,Object val){
    	try{
    		redisTemplate.opsForValue().set(key, val,LOGIN_EXPIRE,TimeUnit.SECONDS);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    /** 
     * 普通缓存获取 
     * @param key 键 
     * @return 值 
     */  
    public Object getStr(String key){
    	return key==null?null:redisTemplate.opsForValue().get(key);
    }
    /** 
     * 判断key是否存在 
     * @param key 键 
     * @return true 存在 false不存在 
     */  
    public boolean hasKey(String key){
    	try{
    		return redisTemplate.hasKey(key);
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
    
    /** 
     * 递增 
     * @param key 键 
     * @param by 要增加几(大于0) 
     * @return 
     */  
    public long incr(String key, long delta){    
        if(delta<0){  
            throw new RuntimeException("递增因子必须大于0");  
        }  
        return redisTemplate.opsForValue().increment(key, delta);  
    }  
    
    /** 
     * 删除缓存 
     * @param key 可以传一个值 或多个 
     */  
    @SuppressWarnings("unchecked")  
    public void delStr(String ... key){  
        if(key!=null&&key.length>0){  
            if(key.length==1){  
                redisTemplate.delete(key[0]);  
            }else{  
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }  
        }  
    }  
    
    public void set(String key, Object value, long expire){
        valueOperations.set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }
}
