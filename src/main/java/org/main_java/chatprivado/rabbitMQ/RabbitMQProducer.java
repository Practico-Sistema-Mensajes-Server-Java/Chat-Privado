package org.main_java.chatprivado.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String intercambio, String claveEnrutamiento, Object mensaje) {
        rabbitTemplate.convertAndSend(intercambio, claveEnrutamiento, mensaje);
        System.out.println("Mensaje enviado a RabbitMQ: " + mensaje);
    }
}