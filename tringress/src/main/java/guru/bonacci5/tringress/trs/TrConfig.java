package guru.bonacci5.tringress.trs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
public class TrConfig {

	@Value("${spring.kafka.bootstrap-servers}") String bootstrapServer;
  @Value("${redis.host}") String redisHost;
  
  
  @Bean
  public ProducerFactory<String, Tr> producerFactory() {
    DefaultKafkaProducerFactory<String, Tr> f = new DefaultKafkaProducerFactory<>(senderProps());
//    f.setTransactionIdPrefix("tx-");
    return f;
  }

  private Map<String, Object> senderProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//    props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "i-should-be-unique-when-scaling");
    return props;
  }
  
  @Bean
  public KafkaTemplate<String, Tr> kafkaTemplate(ProducerFactory<String, Tr> pf) {
    return new KafkaTemplate<String, Tr>(pf);
  }
  
//  @Bean
//  public KafkaTransactionManager<String, Tr> kafkaTransactionManager() {
//    KafkaTransactionManager<String, Tr> ktm = new KafkaTransactionManager<>(producerFactory());
//    ktm.setTransactionSynchronization(AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
//    return ktm;
//  }

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
  public NewTopic topic1() {
       return new NewTopic("transfers_for_coro", 1, (short) 1);
  }
}
