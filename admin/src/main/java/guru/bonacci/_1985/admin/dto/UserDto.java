package guru.bonacci._1985.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UserDto {

  @NotNull
  private String name;
  
  private String description;
}
