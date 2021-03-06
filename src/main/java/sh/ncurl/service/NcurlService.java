package sh.ncurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sh.ncurl.entity.CurlData;
import sh.ncurl.entity.CurlResult;
import sh.ncurl.util.IdUtil;

import java.time.Duration;

/**
 * @author mei
 * @date 2020/5/3
 */
@Service
public class NcurlService {

    @Value("${web.url.prefix}")
    private String webUrlPrefix;

    @Autowired
    private RedisTemplate<String, CurlData> redisTemplate;

    public CurlData get(String id) {
        CurlData curlData = redisTemplate.opsForValue().get(id);
        if (curlData == null) {
            return null;
        }
        curlData.setExpire(redisTemplate.getExpire(id));
        return curlData;
    }

    public CurlResult save(CurlData curlData) {
        String id = IdUtil.generate();
        Duration expireDuration = Duration.ofSeconds(1800);
        Long expire = curlData.getExpire();
        if ( expire != null && expire > 0) {
            expireDuration = Duration.ofSeconds(expire);
        } else {
            expire = expireDuration.getSeconds();
        }
        redisTemplate.opsForValue().set(id, curlData, expireDuration);
        CurlResult curlResult = new CurlResult();
        curlResult.setId(id);
        curlResult.setWebUrl(webUrlPrefix + id);
        curlResult.setExpire(expire);
        return curlResult;
    }

}
