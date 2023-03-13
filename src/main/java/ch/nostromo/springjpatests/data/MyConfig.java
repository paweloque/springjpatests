package ch.nostromo.springjpatests.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String value;

}
