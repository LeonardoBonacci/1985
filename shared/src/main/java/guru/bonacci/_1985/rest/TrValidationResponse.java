package guru.bonacci._1985.rest;

import java.math.BigDecimal;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class TrValidationResponse {

  private String poolType; //TODO make enum
  private Boolean fromIsValid;
  private Boolean toIsValid;
  private BigDecimal fromsBalance;
}