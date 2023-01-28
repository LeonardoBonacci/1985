package guru.bonacci._1985.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.bonacci._1985.admin.domain.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
}
