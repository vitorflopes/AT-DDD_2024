package com.example.PetFriends_Almoxarifado.almoxarifado.infra.service;

import com.example.PetFriends_Almoxarifado.almoxarifado.eventos.PedidoProntoParaEnvio;
import com.example.PetFriends_Almoxarifado.almoxarifado.eventos.PedidoStatusAlterado;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class AlmoxarifadoService {
    private static final Logger LOG = LoggerFactory.getLogger(AlmoxarifadoService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Autowired
    private JacksonPubSubMessageConverter pedidoProntoParaEnvioConverter;

    @ServiceActivator(inputChannel = "inputMessageChannelAlmoxarifado")
    public void processarPedido(PedidoStatusAlterado evento,
                                @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<PedidoStatusAlterado> message) throws InterruptedException {
        LOG.info("***** Evento recebido: "+evento);

        if (evento.getNovoStatus().toString().equals("FECHADO")) {
            Thread.sleep(60000);

            Random random = new Random();
            boolean temEstoque = random.nextInt(100) >= 2;

            if (temEstoque) {
                LOG.info("Pedido {} pronto para envio!", evento.getIdPedido());
            } else {
                LOG.warn("Pedido {} sem estoque! {}", evento.getIdPedido(), evento);
            }

            enviarPedidoPronto(new PedidoProntoParaEnvio(evento.getIdPedido(), new Date(), temEstoque));
        }

        message.ack();
    }

    private void enviarPedidoPronto(PedidoProntoParaEnvio evento) {
        pubSubTemplate.setMessageConverter(pedidoProntoParaEnvioConverter);
        pubSubTemplate.publish("pet_almoxarifado", evento);
        LOG.info("***** Evento PedidoProntoParaEnvio publicado: {}", evento);
    }
}
