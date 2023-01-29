package guru.bonacci.trino.wallet;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci._1985.rest.TrValidationRequest;
import guru.bonacci._1985.rest.TrValidationResponse;
import guru.bonacci.trino.wallet.queries.BalanceQueries;
import guru.bonacci.trino.wallet.queries.Queries;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class WalletApp {

	private final Queries queries;
	private final BalanceQueries balanceQueries;
	
	public static void main(String[] args) {
		SpringApplication.run(WalletApp.class, args);
	}

	
	@PostMapping("wallet")
	public TrValidationResponse validationInfo(@RequestBody @Valid TrValidationRequest trValRequest) {
		log.info(trValRequest.toString());

		var poolType = queries.findPoolType(trValRequest.getPoolId());

		return poolType.map(pType -> {
			var users = queries.findAllNamesInPool(trValRequest.getFrom(), trValRequest.getTo());
			var balance = balanceQueries.findBalance(trValRequest.getPoolId(), trValRequest.getFrom());
			return new TrValidationResponse(pType, users.contains(trValRequest.getFrom()), users.contains(trValRequest.getTo()), balance);

		}).orElse(new TrValidationResponse(null, false, false, BigDecimal.ZERO));
	}
}
