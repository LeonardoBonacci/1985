package guru.bonacci._1985.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.bonacci._1985.admin.domain.AdminUser;
import guru.bonacci._1985.admin.domain.Pool;

@Repository
public interface AdminRepository extends JpaRepository<AdminUser, Long> {

  Optional<AdminUser> findByPools(Pool pool);
}
