package guru.bonacci._1985.tringress.trans;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS, reason = "Relax buddy..")
public class TransferConcurrencyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
