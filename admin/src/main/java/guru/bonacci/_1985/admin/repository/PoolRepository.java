package guru.bonacci._1985.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.bonacci._1985.admin.domain.Pool;
import guru.bonacci._1985.admin.query.IdAndName;

@Repository
public interface PoolRepository extends JpaRepository<Pool, Long> {

  List<IdAndName> findAllProjectedBy();
}
