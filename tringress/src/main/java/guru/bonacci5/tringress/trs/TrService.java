package guru.bonacci5.tringress.trs;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.bonacci5.tringress.tip.TIPCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrService {

  private final TIPCache cache;
  private final TrProducer producer;
  

  @Transactional
  public Tr transfer(Pair<String, Tr> trContext) {
  	if (isBlocked(trContext.getFirst() + "." + trContext.getSecond().getFrom())) {
  		throw new ConcurrencyFailureException("Concurrent transfer request, please try again.");
  	}
  	
    var result = producer.send(trContext);
    log.info("sent {}", result);
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