package ch.nostromo.springjpatests.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyConfig {

    public MyConfig(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String value;

    @Transient
    public int cacheMark;

}
