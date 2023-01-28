package guru.bonacci._1985.admin.dto;

import guru.bonacci._1985.pools.PoolType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PoolDto {

  @NotNull
  private String name;

  @NotNull
  private PoolType type;
}