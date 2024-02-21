package br.com.unifil.buscar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;
	@Value("${spring.redis.port}")
	private int REDIS_PORT;
	@Value("${spring.redis.password}")
	private String REDIS_PASSWORD;
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		
		configuration.setHostName(REDIS_HOSTNAME);
		configuration.setPort(REDIS_PORT);
		configuration.setPassword(REDIS_PASSWORD);

		return new LettuceConnectionFactory(configuration);
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
	}
}
