package com.example.PetFrieds_Transporte.transporte.infra.service;

import com.example.PetFrieds_Transporte.transporte.eventos.PedidoEntregue;
import com.example.PetFrieds_Transporte.transporte.eventos.PedidoProntoParaTransporte;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.Date;

@Service
public class TransporteService {
    private static final Logger LOG = LoggerFactory.getLogger(TransporteService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Autowired
    private JacksonPubSubMessageConverter pedidoEntregueConverter;

    @ServiceActivator(inputChannel = "inputMessageChannelTransporte")
    public void processarPedido(PedidoProntoParaTransporte evento,
                                @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<PedidoProntoParaTransporte> message) throws InterruptedException {
        LOG.info("***** Evento recebido: {}", evento);

        Thread.sleep(120000);

        LOG.info("Pedido {} entregue com sucesso!", evento.getIdPedido());

        enviarPedidoEntregue(new PedidoEntregue(evento.getIdPedido(), new Date()));

        message.ack();
    }

    private void enviarPedidoEntregue(PedidoEntregue evento) {
        pubSubTemplate.setMessageConverter(pedidoEntregueConverter);
        pubSubTemplate.publish("pet_transporte", evento);
        LOG.info("***** Evento PedidoEntregue publicado: {}", evento);
    }
}
