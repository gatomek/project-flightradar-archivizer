package pl.gatomek.flightradar.eventsaver.adapter.out.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitSenderConfig {
    static final String queueName = "LOG_NOTIFICATION";

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }
}
