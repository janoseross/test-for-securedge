package securedge.backend.config;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import securedge.backend.model.ExchangeRatesMessage;

@EnableScheduling
@Configuration
public class SchedulerConfig {
	
	private Random rand = new Random();
	private final double MIN_USD = 4.01;
	private final double MAX_USD = 4.99;
	private final double MIN_EUR = 4.8;
	private final double MAX_EUR = 5.3;
	private final double MIN_GBP = 5.01;
	private final double MAX_GBP = 5.55;
	
	@Autowired
	SimpMessagingTemplate template;
	
	@Scheduled(fixedDelay = 5000)
	public void sendAdhocMessages() {
		double usd, eur, gbp;
		usd = MIN_USD + rand.nextDouble() * (MAX_USD - MIN_USD);		
		eur = MIN_EUR + rand.nextDouble() * (MAX_EUR - MIN_EUR);
		gbp = MIN_GBP + rand.nextDouble() * (MAX_GBP - MIN_GBP);
		
		template.convertAndSend("/topic/exchange-rates", new ExchangeRatesMessage(usd, eur, gbp));
	}
}
