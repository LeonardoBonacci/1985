package guru.bonacci._1985.tringress.validation;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import guru.bonacci._1985.pools.PoolType;
import guru.bonacci._1985.rest.TrValidationRequest;
import guru.bonacci._1985.rest.TrValidationResponse;
import guru.bonacci._1985.tringress.trans.Trans;
import guru.bonacci._1985.tringress.wallet.WalletClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransValidationDelegator {

  private final ApplicationContext appContext;
  private final WalletClient wallet;
  

  public boolean isValid(Pair<String, Trans> trContext) {
  	var poolId = trContext.getFirst();
  	var tr = trContext.getSecond();
    
		var trValidationRequest = new TrValidationRequest(poolId, tr.getFrom(), tr.getTo());
//    var trValidationResponse = wallet.getValidationInfo(trValidationRequest);
    var trValidationResponse = new TrValidationResponse(PoolType.SARDEX, true, true, BigDecimal.ONE);
    log.debug("validation response: {}", trValidationResponse);
    
    var poolType = trValidationResponse.getPoolType();
    if (poolType == null) {
    	throw new InvalidTransferException("pool " + poolId + " ...");
    }    
    
    var validator = appContext.getBean(poolType.toString().toLowerCase(), PoolTypeBasedValidator.class);

    var validationResult = validator.validate(trValidationResponse, tr.getAmount());
    if (!validationResult.isValid()) {
    	throw new InvalidTransferException(validationResult.getErrorMessage());
    }

    return true;
  }
}