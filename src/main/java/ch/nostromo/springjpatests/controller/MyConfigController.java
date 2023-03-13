package ch.nostromo.springjpatests.controller;

import ch.nostromo.springjpatests.data.MyConfig;
import ch.nostromo.springjpatests.data.MyConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MyConfigController {

    @Autowired
    private MyConfigRepository myConfigRepository;

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




}
