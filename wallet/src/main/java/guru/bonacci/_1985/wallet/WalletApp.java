package guru.bonacci._1985.wallet;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci._1985.pools.PoolType;
import guru.bonacci._1985.rest.TrValidationRequest;
import guru.bonacci._1985.rest.TrValidationResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication
public class WalletApp {

	public static void main(String[] args) {
		SpringApplication.run(WalletApp.class, args);
	}
	
	@PostMapping("wallet")
	public TrValidationResponse validationInfo(@RequestBody @Valid TrValidationRequest trValRequest) {
		log.info(trValRequest.toString());
		return new TrValidationResponse(PoolType.SARDEX, true, true, BigDecimal.ONE);
	}
}
