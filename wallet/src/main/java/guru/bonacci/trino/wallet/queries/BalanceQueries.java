package guru.bonacci.trino.wallet.queries;

import java.math.BigDecimal;

public interface BalanceQueries {

	BigDecimal findBalance(String name, String topic);
}
