package guru.bonacci5.tringress.validation;

import java.math.BigDecimal;

public interface PoolTypeBasedValidator {

  TrValidationResult validate(TrValidationResponse info, BigDecimal amount);
  
  boolean hasSufficientFunds(BigDecimal balance);
}