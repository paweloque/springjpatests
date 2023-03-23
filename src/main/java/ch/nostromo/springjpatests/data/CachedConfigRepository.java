package ch.nostromo.springjpatests.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CachedConfigRepository implements ConfigRepository {

    @Autowired
    private MyConfigRepository myConfigRepository;

    @Value("${masterdata.karenzfrist}")
    private String karenzFrist;

    private int cacheCallCounter = 0;
    private int noCacheCallCounter = 0;

    @Override
    @Cacheable("config")
    public Optional<MyConfig> findById(long id) {
        System.out.println("###### calling CACHE config ######");
        cacheCallCounter += 1;
        Optional<MyConfig> byId = myConfigRepository.findById(id);
        byId.get().cacheMark = cacheCallCounter;
        byId.get().karenzFrist = karenzFrist;
        return byId;
    }

    @Override
    public Optional<MyConfig> findByIdNoCache(long id) {
        System.out.println("###### calling NO-CACHE config ######");
        noCacheCallCounter += 1;
        Optional<MyConfig> byId = myConfigRepository.findById(id);
        byId.get().cacheMark = noCacheCallCounter;
        byId.get().karenzFrist = karenzFrist;
        return byId;
    }

    @Override
    public List<MyConfig> findAll() {
        return myConfigRepository.findAll();
    }

    @Override
    public void save(MyConfig myConfig) {
        myConfigRepository.save(myConfig);
    }
}
