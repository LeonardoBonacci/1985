package guru.bonacci5.tringress.validation;

import java.math.BigDecimal;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.ApplicationContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TrValidationDelegator {

  private final ApplicationContext appContext;
  
  private String poolField;
  private String fromField;
  private String toField;
  private String amountField;
  

  public boolean isValid(Object value) {
    var poolId = String.valueOf(new BeanWrapperImpl(value).getPropertyValue(poolField));
    var from = String.valueOf(new BeanWrapperImpl(value).getPropertyValue(fromField));
    var to = String.valueOf(new BeanWrapperImpl(value).getPropertyValue(toField));
    var amount = new BigDecimal(String.valueOf(new BeanWrapperImpl(value).getPropertyValue(amountField)));
    
    var validationRequest = new TrValidationRequest(poolId, from, to);
    //TODO rest call
    var validationResponse = new TrValidationResponse("sardex", true, true, BigDecimal.ONE);
    
    var poolType = validationResponse.getPoolType();
    if (poolType == null) {
    	throw new InvalidTransferException("pool " + poolId + " does not exist");
    }    
    
    var validator = appContext.getBean(poolType, PoolTypeBasedValidator.class);

    var validationResult = validator.validate(validationResponse, amount);
    if (!validationResult.isValid()) {
    	throw new InvalidTransferException(validationResult.getErrorMessage());
    }
    
    return true;
  }
}