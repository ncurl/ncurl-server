package sh.ncurl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sh.ncurl.entity.CurlData;
import sh.ncurl.web.RateLimitInterceptor;

@SpringBootApplication
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/**");
            }
        };
    }

    @Bean
    public RedisTemplate<String, CurlData> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, CurlData> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<CurlData> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(CurlData.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
