package guru.bonacci._1985.tringress.trans;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import guru.bonacci._1985.tringress.tip.TripCache;
import guru.bonacci._1985.tringress.validation.TransValidationDelegator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransService {

  private final TripCache cache;
  private final TransValidationDelegator validator;
  private final TransProducer producer;
  

  public Trans transfer(Pair<String, Trans> trContext) throws TransferConcurrencyException {
  	if (isBlocked(trContext.getFirst() + "." + trContext.getSecond().getFrom())) {
  		throw new TransferConcurrencyException();
  	}

  	validator.isValid(trContext);
  	
    var result = producer.send(trContext);
    log.info("sent to {}: {}", TransProducer.TRANS_TOPIC_PREFIX + trContext.getFirst(), result);
    return result;
  }
  
  private boolean isBlocked(String identifier) {
    if (!cache.lock(identifier)) {
      log.warn("attempt to override lock {}", identifier);
      return true;
    }
    return false;
  }
}