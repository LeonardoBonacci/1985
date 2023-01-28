package guru.bonacci._1985.admin.accountinit;

import java.math.BigDecimal;

import guru.bonacci._1985.admin.domain.Pool;

public interface PoolTypeBasedInitializer {

  BigDecimal determineStartAmount(Pool pool);
}
