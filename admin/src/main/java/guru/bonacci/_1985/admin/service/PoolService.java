package guru.bonacci._1985.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.kafka.common.config.TopicConfig;
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

	public static final String TRANS_TOPIC_PREFIX = "trans.";
	
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
  
  @Transactional
  public Pool createPool(Long adminId, Pool pool) {
    var admin = adminRepo.findById(adminId)
         .orElseThrow(() -> new EntityNotFoundException("Cannot find admin with id " + adminId));
    pool.setAdmin(admin);

    var kafkaTopic = 
    		TopicBuilder.name(TRANS_TOPIC_PREFIX + pool.getName())
    								.replicas(1)
    								.config(TopicConfig.RETENTION_BYTES_CONFIG, "-1")
    								.config(TopicConfig.RETENTION_MS_CONFIG, "-1")
    								.build();
    kafka.createOrModifyTopics(kafkaTopic);
    log.info("created Kafka topic {}", kafkaTopic.name());

    return poolRepo.saveAndFlush(pool);
  }
  
  @Transactional
  public void deactivate(Long id) {
    getPool(id).ifPresent(pool -> {
      pool.setActive(false);
      pool.setAdmin(null);
      
      poolRepo.saveAndFlush(pool);
    });
  }
}
