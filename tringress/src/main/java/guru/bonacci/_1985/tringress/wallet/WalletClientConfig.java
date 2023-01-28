package guru.bonacci._1985.tringress.wallet;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.okhttp.OkHttpClient;

public class WalletClientConfig {

	@Bean
	public OkHttpClient client() {
		return new OkHttpClient();
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
