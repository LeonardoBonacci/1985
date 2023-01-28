package guru.bonacci._1985.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import guru.bonacci._1985.admin.domain.UserInfo;

@Repository
@Transactional("transactionManager")
public interface UserRepository extends JpaRepository<UserInfo, Long> {
}
