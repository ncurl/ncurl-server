package sh.ncurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sh.ncurl.entity.CurlData;
import sh.ncurl.util.IdUtil;

import java.time.Duration;

/**
 * @author mei
 * @date 2020/5/3
 */
@Service
public class NcurlService {

    @Autowired
    private RedisTemplate<String, CurlData> redisTemplate;

    public CurlData get(String id) {
        return redisTemplate.opsForValue().get(id);
    }

    public String save(CurlData curlData) {
        String id = IdUtil.generate();
        Integer expire = curlData.getExpire();
        Duration expireDuration = Duration.ofSeconds(1800);
        if ( expire != null && expire > 0) {
            expireDuration = Duration.ofSeconds(expire);
        }
        redisTemplate.opsForValue().set(id, curlData, expireDuration);
        return id;
    }

}
