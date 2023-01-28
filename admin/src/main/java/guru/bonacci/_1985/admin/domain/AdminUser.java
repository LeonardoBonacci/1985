package guru.bonacci._1985.admin.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private UserInfo user;
  
  @Column(nullable = false)
  private String bankDetails;

  @JsonIgnore
  @Builder.Default
  @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER)
  private List<Pool> pools = new ArrayList<>();
  
  @Override
  public String toString() {
    return "AdminUser(id="+id
        +", user.name="+user.getName()
        +", pools.name="+pools.stream().map(Pool::getName).collect(Collectors.toList()).toString();
  }
}