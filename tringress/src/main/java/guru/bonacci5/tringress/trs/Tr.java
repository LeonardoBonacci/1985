package guru.bonacci5.tringress.trs;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tr implements Cloneable {

  private String transferId; //required
  private String from; //required
  private String to; //required
  private BigDecimal amount; //required
  private long when; //required
}