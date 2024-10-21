package ProyectoCombustible.TP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;

@SpringBootApplication
//@CacheConfig
public class TpCombustibleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpCombustibleApplication.class, args);
	}

}
