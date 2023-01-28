package guru.bonacci._1985.admin.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import guru.bonacci._1985.pools.PoolType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "accounts")
@Table
@Entity
@Where(clause = "active = true")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pool {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, updatable = false, unique = true)
  private String name;
  
  @Column
  private String description;

  @JsonIgnore
  @Builder.Default
  @OneToMany(mappedBy = "pool")
  private List<AccountDetails> accounts = new ArrayList<>(); // grows very large

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  private PoolType type;
  
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "admin_id")
  private AdminUser admin;
  
  @JsonIgnore
  @Column 
  @Builder.Default
  private boolean active = true;
}