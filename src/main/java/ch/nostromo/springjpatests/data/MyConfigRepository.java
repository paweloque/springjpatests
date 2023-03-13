package ch.nostromo.springjpatests.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  MyConfigRepository extends JpaRepository<MyConfig, Long> {}
