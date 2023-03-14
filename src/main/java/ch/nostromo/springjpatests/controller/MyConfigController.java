package ch.nostromo.springjpatests.controller;

import ch.nostromo.springjpatests.data.ConfigRepository;
import ch.nostromo.springjpatests.data.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MyConfigController {

    @Autowired
    private ConfigRepository myConfigRepository;

    @GetMapping("/myconfigs")
    public List<MyConfig> myConfig() {

        // Why not get it multiple times in one tx ? ;)
        for (int i = 0; i < 100; i++) {
            myConfigRepository.findAll();
        }

        // Twice with key
        myConfigRepository.findById(99L);
        myConfigRepository.findById(99L);

        return myConfigRepository.findAll();
    }

    @GetMapping("/configs")
    public List<Optional<MyConfig>> cachedConfig() {
        Optional<MyConfig> cachedConfig = cachedConfigValue(1L);
        Optional<MyConfig> plainConfig = configValue(1L);
        ArrayList<Optional<MyConfig>> configs = new ArrayList<>();
        configs.add(cachedConfig);
        configs.add(plainConfig);
        return configs;
    }

    @Cacheable("config")
    public Optional<MyConfig> cachedConfigValue(long id) {
        Optional<MyConfig> configValue = myConfigRepository.findById(id);
        return configValue;
    }

    public Optional<MyConfig> configValue(long id) {
        Optional<MyConfig> configValue = myConfigRepository.findByIdNoCache(id);
        return configValue;
    }

}
