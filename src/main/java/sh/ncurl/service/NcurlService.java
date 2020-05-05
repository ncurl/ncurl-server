package sh.ncurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sh.ncurl.util.IdUtil;

import java.time.Duration;

/**
 * @author mei
 * @date 2020/5/3
 */
@Service
public class NcurlService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String get(String id) {
        return stringRedisTemplate.opsForValue().get(id);
    }

    public String save(String body) {
        String id = IdUtil.generate();
        stringRedisTemplate.opsForValue().set(id, body, Duration.ofHours(24));
        return id;
    }

}
