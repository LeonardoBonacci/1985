package guru.bonacci5.tringress.trs;

import java.math.BigDecimal;

import guru.bonacci5.tringress.trs.validation.ExecFirst;
import guru.bonacci5.tringress.trs.validation.ExecSecond;
import guru.bonacci5.tringress.trs.validation.FieldsValueDifferent;
import jakarta.annotation.Nonnull;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@FieldsValueDifferent(
  groups = ExecSecond.class,
  field1 = "from", 
  field2 = "to", 
  message = "'from' and 'to' must be different"
)
@Value
@GroupSequence({TrDto.class, ExecFirst.class, ExecSecond.class})
public class TrDto {

  @NotBlank(message = "where is poolId?")
  private String poolId;

  @NotBlank(message = "where is from?", groups = ExecFirst.class)
  private String from;

  @NotBlank(message = "where is to?", groups = ExecFirst.class)
  private String to;

  @Nonnull
  @DecimalMin(value = "0.0", inclusive = false, message = "> 0.0 please")
  @Digits(integer = 4, fraction = 2)
  private BigDecimal amount;
}