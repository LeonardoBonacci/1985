package guru.bonacci5.tringress.validation;

import lombok.Value;

@Value
public class TrValidationResult {
  
  private boolean isValid;
  private String errorMessage;
}