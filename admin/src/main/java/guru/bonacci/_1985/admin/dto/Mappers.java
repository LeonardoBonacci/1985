package guru.bonacci._1985.admin.dto;

import guru.bonacci._1985.admin.domain.AccountDetails;
import guru.bonacci._1985.admin.domain.Pool;
import guru.bonacci._1985.admin.domain.UserInfo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mappers {

  public AccountDetails mapAccount(AccountDto dto) {
    return AccountDetails.builder().name(dto.getName()).description(dto.getDescription()).build();
  }
  
  public UserInfo mapUser(UserDto dto) {
    return UserInfo.builder().name(dto.getName()).description(dto.getDescription()).build();
  }

  public Pool mapPool(PoolDto dto) {
    return Pool.builder().name(dto.getName()).type(dto.getType()).build();
  }
}
