package guru.bonacci._1985.wallet.queries;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// not used - only needed to create a spring-data-repository
// room for improvement here
@Data
@Entity
@Table(name = "user_info", catalog = "mysql", schema = "_1985")
@NoArgsConstructor
@AllArgsConstructor
public class Unused {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
}