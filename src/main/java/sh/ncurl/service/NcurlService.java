package sh.ncurl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sh.ncurl.entity.ClientRequest;
import sh.ncurl.util.IdUtil;

import java.time.Duration;

/**
 * @author mei
 * @date 2020/5/3
 */
@Service
public class NcurlService {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ClientRequest get(String id) throws JsonProcessingException {
        String bodyString = stringRedisTemplate.opsForValue().get(id);
        return objectMapper.readValue(bodyString, ClientRequest.class);
    }

    public String save(ClientRequest body) throws JsonProcessingException {
        String bodyString = objectMapper.writeValueAsString(body);
        String id = IdUtil.generate();
        stringRedisTemplate.opsForValue().set(id, bodyString, Duration.ofHours(24));
        return id;
    }

}
