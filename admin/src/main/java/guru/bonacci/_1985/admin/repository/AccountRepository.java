package guru.bonacci._1985.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.bonacci._1985.admin.domain.AccountDetails;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long> {

  List<AccountDetails> findByPoolId(Long poolId);
  
  List<AccountDetails> findByPoolIdAndNameLike(Long poolId, String name);
}
