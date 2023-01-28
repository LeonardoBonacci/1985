package guru.bonacci5._1985.tringress.validation.pool;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import guru.bonacci5._1985.tringress.validation.PoolTypeBasedValidator;
import guru.bonacci5._1985.tringress.validation.TrValidationResponse;
import guru.bonacci5._1985.tringress.validation.TrValidationResult;

@Component("sardex")
public class SardexPoolTypeValidator implements PoolTypeBasedValidator {

  private static final BigDecimal MIN_BALANCE = BigDecimal.valueOf(-1000);

  
  @Override 
  public TrValidationResult validate(TrValidationResponse info, BigDecimal amount) {
    if (info.getPoolType() == null && !info.getFromIsValid() || !info.getToIsValid() || info.getFromsBalance() == null) {
      return new TrValidationResult(false, "invalid transfer attempt");
    }

    return hasSufficientFunds(info.getFromsBalance()) ?
          new TrValidationResult(true, null) :
          new TrValidationResult(false, "insufficient balance");
  }    
      
  @Override 
  public boolean hasSufficientFunds(BigDecimal balance) {
    return balance.compareTo(MIN_BALANCE) > -1;
  }
}    