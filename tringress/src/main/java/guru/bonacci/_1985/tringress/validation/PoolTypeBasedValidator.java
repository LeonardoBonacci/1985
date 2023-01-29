package guru.bonacci._1985.tringress.validation;

import java.math.BigDecimal;

import guru.bonacci._1985.rest.TrValidationResponse;

public interface PoolTypeBasedValidator {

  TransValidationResult validate(TrValidationResponse info, BigDecimal amount);
  
  boolean hasSufficientFunds(BigDecimal balance, BigDecimal amount);
}