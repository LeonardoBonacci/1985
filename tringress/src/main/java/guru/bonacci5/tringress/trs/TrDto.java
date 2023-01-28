package guru.bonacci5.tringress.trs;

import java.math.BigDecimal;

import lombok.Value;

//@FieldsValueDifferent(
//  groups = ExecSecond.class,
//  field1 = "from", 
//  field2 = "to", 
//  message = "'from' and 'to' must be different"
//)
@Value
//@GroupSequence({TransferDto.class, ExecFirst.class, ExecSecond.class})
public class TrDto {

//  @NotBlank(message = "poolId has to be present")
  private String poolId;

//  @NotBlank(message = "from has to be present", groups = ExecFirst.class)
  private String from;

//  @NotBlank(message = "to has to be present", groups = ExecFirst.class)
  private String to;

//  @Nonnull
//  @DecimalMin(value = "0.0", inclusive = false, message = "> 0.0 please")
//  @Digits(integer = 4, fraction = 2)
  private BigDecimal amount;
}