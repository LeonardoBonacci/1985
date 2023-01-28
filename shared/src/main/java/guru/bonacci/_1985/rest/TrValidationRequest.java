package guru.bonacci._1985.rest;

import javax.annotation.Nonnull;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class TrValidationRequest {

	@Nonnull private String poolId; 
	@Nonnull private String from;
	@Nonnull private String to;
}