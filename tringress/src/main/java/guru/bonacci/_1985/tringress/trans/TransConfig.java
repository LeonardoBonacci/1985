package guru.bonacci._1985.tringress.trans;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class TransConfig {

  @Value("${spring.redis.host}") String redisHost;
  

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
  	var redis = new RedisStandaloneConfiguration();
  	redis.setDatabase(0);
  	redis.setHostName("localhost");
    redis.setPort(16379);
    redis.setPassword("mypass");
    return new LettuceConnectionFactory(redis);
  }
  
  @Bean
  public NewTopic internal() {
       return new NewTopic("__transaction_state", 50, (short) 1);
  }

  @Bean
  public NewTopic topic1() {
       return new NewTopic("transfers_for_coro", 1, (short) 1);
  }
}
