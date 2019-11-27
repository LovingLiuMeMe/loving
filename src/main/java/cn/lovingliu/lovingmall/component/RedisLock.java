package cn.lovingliu.lovingmall.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: redis锁 避免超卖
 * @Date：Created in 2019-11-27
 */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Desc 加锁
     * @param key 包含productId
     * @param value 当前时间+超时时间
     * @Author LovingLiu
     */
    public boolean lock(String key,String value){
        // redis setNx 代表只在键不存在时，才对键进行设置操作。
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        String currentValue = stringRedisTemplate.opsForValue().get(key);
        // 如果锁过期
        if(StringUtils.isNotBlank(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()){
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key,value);

            if(StringUtils.isNotBlank(oldValue) && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * @Desc 解锁
     * @Author LovingLiu
     */
    public void unlock(String key,String value){
        try{
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(currentValue) && currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式解锁失败】,{}",e.getMessage());
        }

    }
}
