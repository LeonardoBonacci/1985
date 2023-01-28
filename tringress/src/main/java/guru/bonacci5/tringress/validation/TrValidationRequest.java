package guru.bonacci5.tringress.validation;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class TrValidationRequest {

  private String poolId; //required
  private String from; //required
  private String to; //required
}