package guru.bonacci._1985.wallet.queries;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BalanceQueriesImpl implements BalanceQueries {

  @PersistenceContext
  private EntityManager entityManager;

	
	@Override
	public BigDecimal findBalance(String topic, String name) {
		var queryStr = String.format("WITH "
				+ "					  debit AS (SELECT sum(amount) AS SMILE FROM kafka.trans.%s where to = :name),"
				+ "					  credit AS (SELECT sum(amount) AS CRY FROM kafka.trans.%s where _from = :name)"
				+ "					SELECT coalesce(SMILE, 0) - coalesce(CRY, 0) AS balance FROM debit, credit"
				, topic, topic);

		var query = entityManager.createNativeQuery(queryStr);
		query.setParameter("name", name);

		return BigDecimal.valueOf((double)query.getSingleResult());
	}
}
