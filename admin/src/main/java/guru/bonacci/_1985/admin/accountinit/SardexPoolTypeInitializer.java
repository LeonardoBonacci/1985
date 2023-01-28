package guru.bonacci._1985.admin.accountinit;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import guru.bonacci._1985.admin.domain.Pool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("sardex")
public class SardexPoolTypeInitializer implements PoolTypeBasedInitializer {

  @Override 
  public BigDecimal determineStartAmount(Pool pool) {
    log.info("determine start amount for pool {}", pool);
    return BigDecimal.ZERO;
  }    
}    

