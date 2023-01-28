package guru.bonacci._1985.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
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

	public static final String TRANSFER_TOPIC_PREFIX = "transfers_for_";

	private final PoolRepository poolRepo;
  private final AdminRepository adminRepo;
  private final AccountRepository accountRepo;
  private final KafkaAdmin kafka;
  
  
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
  
  @Transactional("transactionManager")
  public Pool createPool(Long adminId, Pool pool) {
  	adminRepo.findAll().forEach(u -> log.info("" + u.getId()));
    var admin = adminRepo.findById(adminId)
         .orElseThrow(() -> new EntityNotFoundException("Cannot find admin with id " + adminId));
    pool.setAdmin(admin);

    var kafkaTopic = TopicBuilder.name(TRANSFER_TOPIC_PREFIX + pool.getName()).replicas(1).build();
    kafka.createOrModifyTopics(kafkaTopic);
    
    return poolRepo.saveAndFlush(pool);
  }
  
  @Transactional(transactionManager = "transactionManager")
  public void deactivate(Long id) {
    getPool(id).ifPresent(pool -> {
      pool.setActive(false);
      pool.setAdmin(null);
      
      poolRepo.saveAndFlush(pool);
    });
  }
}
