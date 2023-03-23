package ch.nostromo.springjpatests;

import ch.nostromo.springjpatests.data.MyConfig;
import ch.nostromo.springjpatests.data.MyConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootApplication
@Component
@EnableCaching
public class SpringjpatestsApplication implements ApplicationRunner {

	@DynamicPropertySource
	static void registerPgProperties(DynamicPropertyRegistry registry) {
		registry.add("masterdata.karenzfrist", () -> "43");
	}

	@Autowired
	private MyConfigRepository myConfigRepository;

	public void run(ApplicationArguments args) {
		myConfigRepository.save(new MyConfig(99L, "Globi was here!")); // Test record
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringjpatestsApplication.class, args);
	}
}
