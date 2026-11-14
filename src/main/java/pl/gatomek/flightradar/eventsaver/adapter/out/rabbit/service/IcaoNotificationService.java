package pl.gatomek.flightradar.eventsaver.adapter.out.rabbit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.gatomek.flightradar.eventsaver.application.port.out.IcaoNotificationUseCase;

@RequiredArgsConstructor
@Service
public class IcaoNotificationService implements IcaoNotificationUseCase {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public void notify(String icao) {
        rabbitTemplate.convertAndSend(queue.getName(), icao);
    }
}
