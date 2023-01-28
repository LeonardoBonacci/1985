package guru.bonacci._1985.admin.domain;

import java.math.BigDecimal;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Where(clause = "active = true")
@Table(name = "account", uniqueConstraints={
  @UniqueConstraint(columnNames = {"user_id", "pool_id"}),
  @UniqueConstraint(columnNames = {"name", "pool_id"})
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, updatable = false)
  private String name;

  @Column
  private String description;

  @Column
  private BigDecimal startAmount;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false) 
  private UserInfo user;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "pool_id", nullable = false, updatable = false)
  private Pool pool;

  @JsonIgnore
  @Column 
  @Builder.Default
  private boolean active = true;

  // to facilitate cdc
  @Column(nullable = false, updatable = false)
  private String poolName;

  // to facilitate cdc
  @Column(nullable = false, updatable = false)
  private String poolAccountId;

  @Override
  public String toString() {
    return "AccountDetails(id="+id
            +", name="+name
            +", description="+description
            +", user.name="+user.getName()
            +", pool.name="+pool.getType()+")";
  }
}