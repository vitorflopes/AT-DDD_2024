package com.example.PetFriends_Almoxarifado.almoxarifado.infra.message;

import com.example.PetFriends_Almoxarifado.almoxarifado.eventos.PedidoStatusAlterado;
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
public class PetFriendsAlmoxarifadoMessageConfig {
    @Bean
    public JacksonPubSubMessageConverter pedidoStatusAlteradoConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(PedidoStatusAlterado.class, new PedidoStatusAlteradoSerializer());
        simpleModule.addDeserializer(PedidoStatusAlterado.class, new PedidoStatusAlteradoDeserializer());
        objectMapper.registerModule(simpleModule);
        return new JacksonPubSubMessageConverter(objectMapper);
    }

    @Bean
    public MessageChannel inputMessageChannelAlmoxarifado() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapterAlmoxarifado(
            @Qualifier("inputMessageChannelAlmoxarifado") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {

        pubSubTemplate.setMessageConverter(pedidoStatusAlteradoConverter());
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "pet_almoxarifado-sub");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(PedidoStatusAlterado.class);
        return adapter;
    }
}
