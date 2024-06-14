package br.com.unifil.buscar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisConfig is responsible for configuring and connecting to 
 * Redis cache.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;
	@Value("${spring.redis.port}")
	private int REDIS_PORT;
	@Value("${spring.redis.password}")
	private String REDIS_PASSWORD;
	
	
	/**
	 * Creates a connection with Redis. Uses configuration information provided
	 * in the application.yml file.
	 *
	 * @return {@link LettuceConnectionFactory}\
	 * @since 1.0
	 */
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		
		configuration.setHostName(REDIS_HOSTNAME);
		configuration.setPort(REDIS_PORT);
		configuration.setPassword(REDIS_PASSWORD);

		return new LettuceConnectionFactory(configuration);
	}
	
	/**
	 * Creates a RedisTemplate to simplify Redis data access.
	 *
	 * @return {@link RedisTemplate}
	 * @since 1.0
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
	}
}
