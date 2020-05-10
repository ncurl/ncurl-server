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
        redisTemplate.opsForValue().set(id, curlData, Duration.ofHours(24));
        return id;
    }

}
