package guru.bonacci5.tringress;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.util.Pair;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci5.tringress.trs.Tr;
import guru.bonacci5.tringress.trs.TrDto;
import guru.bonacci5.tringress.trs.TrService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class TrIngressApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TrIngressApp.class, args);
	}

	@Override
  public void run(String... args) {
     log.info("EXECUTING : command line runner");

     var dto = new TrDto("pool2", "from", "to", BigDecimal.ONE);
     service.transfer(toTr(dto));
  }

	private final TrService service;
  
	@PostMapping(path = "transfers")
  public Callable<Tr> transfer(@RequestBody @Valid TrDto dto) {
    return () -> {
      var watch = new StopWatch();
      watch.start();

      var trContext = toTr(dto);
//      validateInput(tr);
      var result = service.transfer(trContext);
  
      watch.stop();
      log.info("Processing Time : {}", watch.getTotalTimeMillis()); 
    
      return result;
    };  
  }
  
  static Pair<String, Tr> toTr(TrDto dto) {
    return Pair.of(dto.getPoolId(), 	
    		Tr.builder()
            .transferId(UUID.randomUUID().toString())
            .from(dto.getFrom())
            .to(dto.getTo())
            .amount(dto.getAmount())
            .build());
  }
}
