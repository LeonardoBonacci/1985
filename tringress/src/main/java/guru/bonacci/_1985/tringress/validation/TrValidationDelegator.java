package guru.bonacci._1985.tringress.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import guru.bonacci._1985.rest.TrValidationRequest;
import guru.bonacci._1985.tringress.trs.Tr;
import guru.bonacci._1985.tringress.wallet.WalletClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrValidationDelegator {

  private final ApplicationContext appContext;
  private final WalletClient wallet;
  

  public boolean isValid(Pair<String, Tr> trContext) {
  	var poolId = trContext.getFirst();
  	var tr = trContext.getSecond();
    
		var trValidationRequest = new TrValidationRequest(poolId, tr.getFrom(), tr.getTo());
    var trValidationResponse = wallet.getValidationInfo(trValidationRequest);
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