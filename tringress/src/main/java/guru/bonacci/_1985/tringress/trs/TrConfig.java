package guru.bonacci._1985.tringress.trs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class TrConfig {

  @Value("${redis.host}") String redisHost;
  

  @SuppressWarnings("deprecation")
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
    connectionFactory.setDatabase(0);
    connectionFactory.setHostName("localhost");
    connectionFactory.setPort(16379);
    connectionFactory.setPassword("mypass");
    connectionFactory.setTimeout(60000);
    return connectionFactory;
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
