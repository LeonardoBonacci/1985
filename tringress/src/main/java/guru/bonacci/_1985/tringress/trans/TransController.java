package guru.bonacci._1985.tringress.trans;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.springframework.data.util.Pair;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("transfers")
@RequiredArgsConstructor
public class TransController {

	private final TransService service;
	
	
	@PostMapping
  public Callable<Trans> transfer(@RequestBody @Valid TransDto dto) {
    return () -> {
      var watch = new StopWatch();
      watch.start();

      var trContext = toTr(dto);
      var result = service.transfer(trContext);
  
      watch.stop();
      log.info("Processing Time : {}", watch.getTotalTimeMillis()); 
    
      return result;
    };  
  }
  
  static Pair<String, Trans> toTr(TransDto dto) {
    return Pair.of(dto.getPoolId(), 	
    		Trans.builder()
            .transferId(UUID.randomUUID().toString())
            .from(dto.getFrom())
            .to(dto.getTo())
            .amount(dto.getAmount())
            .build());
  }
}
