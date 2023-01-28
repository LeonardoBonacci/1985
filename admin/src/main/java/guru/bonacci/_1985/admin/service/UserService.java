package guru.bonacci._1985.admin.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.bonacci._1985.admin.domain.UserInfo;
import guru.bonacci._1985.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repo;

  
  public Optional<UserInfo> getUser(Long id) {
    return repo.findById(id);
  }

  @Transactional(transactionManager = "transactionManager")
  public UserInfo createUser(UserInfo user) {
    return repo.saveAndFlush(user);
  }

  @Transactional(transactionManager = "transactionManager")
  public UserInfo updateUser(UserInfo user) {
    return repo.saveAndFlush(user);
  }
}
