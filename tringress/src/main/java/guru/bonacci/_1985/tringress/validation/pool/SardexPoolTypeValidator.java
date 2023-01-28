package guru.bonacci._1985.tringress.validation.pool;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import guru.bonacci._1985.rest.TrValidationResponse;
import guru.bonacci._1985.tringress.validation.PoolTypeBasedValidator;
import guru.bonacci._1985.tringress.validation.TransValidationResult;

@Component("sardex")
public class SardexPoolTypeValidator implements PoolTypeBasedValidator {

  private static final BigDecimal MIN_BALANCE = BigDecimal.valueOf(-1000);

  
  @Override 
  public TransValidationResult validate(TrValidationResponse info, BigDecimal amount) {
    if (info.getPoolType() == null && !info.getFromIsValid() || !info.getToIsValid() || info.getFromsBalance() == null) {
      return new TransValidationResult(false, "invalid transfer attempt");
    }

    return hasSufficientFunds(info.getFromsBalance()) ?
          new TransValidationResult(true, null) :
          new TransValidationResult(false, "insufficient balance");
  }    
      
  @Override 
  public boolean hasSufficientFunds(BigDecimal balance) {
    return balance.compareTo(MIN_BALANCE) > -1;
  }
}    