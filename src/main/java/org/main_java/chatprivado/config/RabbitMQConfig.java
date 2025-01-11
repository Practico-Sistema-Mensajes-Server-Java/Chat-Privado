package org.main_java.chatprivado.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NOMBRE_COLA = "cola_mensajes_publicos";
    public static final String NOMBRE_INTERCAMBIO = "intercambio_mensajes_publicos";
    public static final String CLAVE_ENRUTAMIENTO = "clave_mensajes_publicos";

    @Bean
    public Queue cola() {
        return new Queue(NOMBRE_COLA, true);
    }

    @Bean
    public TopicExchange intercambio() {
        return new TopicExchange(NOMBRE_INTERCAMBIO);
    }

    @Bean
    public Binding enlace(Queue cola, TopicExchange intercambio) {
        return BindingBuilder.bind(cola).to(intercambio).with(CLAVE_ENRUTAMIENTO);
    }
}