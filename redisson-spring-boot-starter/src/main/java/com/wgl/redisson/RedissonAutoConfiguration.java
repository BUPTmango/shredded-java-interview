package com.wgl.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/28 22:13
 */
@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedissonProperties.class)
@Configuration
public class RedissonAutoConfiguration {

    @Bean
    RedissonClient redissonClient(RedissonProperties redissonProperties) {
        Config config = new Config();
        // 判断是否启用ssl
        String prefix = redissonProperties.isSsl() ? "rediss://" : "redis://";

        String host = redissonProperties.getHost();
        int port = redissonProperties.getPort();

        config.useSingleServer()
                .setAddress(prefix + host + ":" + port)
                .setConnectTimeout(1000 * 30);

        return Redisson.create(config);
    }

}

