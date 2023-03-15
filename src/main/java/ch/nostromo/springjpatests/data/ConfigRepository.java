package ch.nostromo.springjpatests.data;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface ConfigRepository {

    Optional<MyConfig> findById(long id);

    Optional<MyConfig> findByIdNoCache(long id);

    List<MyConfig> findAll();

    void save(MyConfig myConfig);
}
