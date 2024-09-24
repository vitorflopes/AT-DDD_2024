package com.example.PetFrieds_Transporte.transporte.infra.message;

import com.example.PetFrieds_Transporte.transporte.eventos.PedidoProntoParaTransporte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class PetFriendsTransporteMessageConfig {
    @Bean
    public JacksonPubSubMessageConverter pedidoProntoParaTransporteConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(PedidoProntoParaTransporte.class, new PedidoProntoParaTransporteSerializer());
        simpleModule.addDeserializer(PedidoProntoParaTransporte.class, new PedidoProntoParaTransporteDeserializer());
        objectMapper.registerModule(simpleModule);
        return new JacksonPubSubMessageConverter(objectMapper);
    }

    @Bean
    public MessageChannel inputMessageChannelTransporte() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapterTransporte(
            @Qualifier("inputMessageChannelTransporte") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {

        pubSubTemplate.setMessageConverter(pedidoProntoParaTransporteConverter());
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "pet_transporte-sub");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(PedidoProntoParaTransporte.class);
        return adapter;
    }
}
