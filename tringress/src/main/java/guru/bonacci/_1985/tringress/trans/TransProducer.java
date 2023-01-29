package guru.bonacci._1985.tringress.trans;

import org.springframework.data.util.Pair;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransProducer {

	public static final String TRANS_TOPIC_PREFIX = "trans.";
	
  private final KafkaTemplate<String, Trans> kafkaTemplate;

  @Transactional
  public Trans send(Pair<String, Trans> transferContext) {
  	var transfer = transferContext.getSecond();
  	// FIXME - not urgent - then when in the payload should somehow be the kafka timestamp
    long timestamp = sendMessage(TRANS_TOPIC_PREFIX + transferContext.getFirst(), transfer.getFrom(), transfer);
    transfer.setWhen(timestamp);
    return transfer;
  }
 
  // exposed for testing
  long sendMessage(String topic, String key, Trans message) {
    try {
      return kafkaTemplate.send(topic, key, message).get().getRecordMetadata().timestamp();
    } catch (Throwable t) {
      t.printStackTrace();
      return -1;
    }
  }
}