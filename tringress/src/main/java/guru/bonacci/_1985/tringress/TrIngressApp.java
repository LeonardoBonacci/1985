package guru.bonacci._1985.tringress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TrIngressApp {

	public static void main(String[] args) {
		SpringApplication.run(TrIngressApp.class, args);
	}
}
