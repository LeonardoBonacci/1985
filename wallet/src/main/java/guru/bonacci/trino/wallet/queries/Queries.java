package guru.bonacci.trino.wallet.queries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import guru.bonacci._1985.pools.PoolType;

@Repository
public interface Queries extends org.springframework.data.repository.Repository<Unused, Long> {
	
	
	@Query(value = "select type from mysql._1985.pool where name = ?1", nativeQuery = true)
  Optional<PoolType> findPoolType(String name);

	
	@Query(value = " WITH"
			+ "	inpool AS (SELECT users.name FROM mysql._1985.user_info AS users"
			+ "	             JOIN mysql._1985.account AS accounts ON accounts.user_id = users.id"
			+ "	             JOIN mysql._1985.pool AS pools ON pools.id = accounts.pool_id"
			+ "	             WHERE users.name = ?1 OR users.name = ?2)"
			+ "	SELECT name FROM inpool", nativeQuery = true)
  List<String> findAllNamesInPool(String from, String to);

	
	@Query(value = "select * from mysql._1985.user_info where name = ?1", nativeQuery = true)
  Optional<Unused> findByName(String name);
}
