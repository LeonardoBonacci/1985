package guru.bonacci._1985.rest;

import java.math.BigDecimal;

import guru.bonacci._1985.pools.PoolType;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class TrValidationResponse {

  private PoolType poolType;
  private Boolean fromIsValid;
  private Boolean toIsValid;
  private BigDecimal fromsBalance;
}