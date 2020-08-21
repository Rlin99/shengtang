package club.banyuan.mall.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    //获取Zset数据
    public Set<Object> zrange(String key, long beginIndex, long endIndex){
        return redisTemplate.opsForZSet().range(key,beginIndex,endIndex);
    }

    //得到Zset 中的总数
    public long getTotalZset(String key){
       return redisTemplate.opsForZSet().zCard(key);
    }

    //设置Zset
    public boolean zset(String key, double i, String value){
        try{
            redisTemplate.opsForZSet().add(key, value, i);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //设置key的过期时间
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //通过key获取值
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    //设置key And value
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //设置键值对并设置过期时间
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除key
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
}
