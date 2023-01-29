package guru.bonacci._1985.wallet.queries;

import java.math.BigDecimal;

public interface BalanceQueries {

	BigDecimal findBalance(String name, String topic);
}
