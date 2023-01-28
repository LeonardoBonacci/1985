package guru.bonacci._1985.admin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AccountDto {

  @NotNull
  @Size(min = 4, max = 25)
  private String name;

  private String description;
}
