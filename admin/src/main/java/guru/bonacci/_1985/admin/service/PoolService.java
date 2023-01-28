package guru.bonacci._1985.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.bonacci._1985.admin.domain.Pool;
import guru.bonacci._1985.admin.query.IdAndName;
import guru.bonacci._1985.admin.repository.AccountRepository;
import guru.bonacci._1985.admin.repository.AdminRepository;
import guru.bonacci._1985.admin.repository.PoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoolService {

  private final PoolRepository poolRepo;
  private final AdminRepository adminRepo;
  private final AccountRepository accountRepo;
//FIXME  private final KafkaTemplate<String, KafkaPool> kafkaTemplate;
  
  
  public Optional<Pool> getPool(Long id) {
    return poolRepo.findById(id);
  }

  public Integer getPoolSize(Long id) {
    return accountRepo.findByPoolId(id).size();
  }

  public List<String> allPoolNames() {
    return poolRepo.findAllProjectedBy().stream()
                  .map(IdAndName::getName)
                  .collect(Collectors.toList());
  }
  
  // https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync
  @Transactional("transactionManager")
  public Pool createPool(Long adminId, Pool pool) {
  	adminRepo.findAll().forEach(u -> log.info("" + u.getId()));
    var admin = adminRepo.findById(adminId)
         .orElseThrow(() -> new EntityNotFoundException("Cannot find admin with id " + adminId));
    pool.setAdmin(admin);
    
//    var kafkaPool = KafkaPool.from(pool); 
//    kafkaTemplate.send("pool", kafkaPool.getPoolId(), kafkaPool);
    
    return poolRepo.saveAndFlush(pool);
  }
  
  @Transactional(transactionManager = "transactionManager")
  public void deactivate(Long id) {
    getPool(id).ifPresent(pool -> {
      pool.setActive(false);
      pool.setAdmin(null);
      
//      kafkaTemplate.send("pool", pool.getName(), null);
      poolRepo.saveAndFlush(pool);
    });
  }
}
