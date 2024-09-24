package com.example.PetFrieds_Transporte.transporte.infra.message;

import com.example.PetFrieds_Transporte.transporte.eventos.PedidoProntoParaTransporte;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PedidoProntoParaTransporteDeserializer extends StdDeserializer<PedidoProntoParaTransporte> {
    public PedidoProntoParaTransporteDeserializer() {
        super(PedidoProntoParaTransporte.class);
    }

    @Override
    public PedidoProntoParaTransporte deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        PedidoProntoParaTransporte evento = null;
        JsonNode node = jp.getCodec().readTree(jp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        try {
            evento = new PedidoProntoParaTransporte(
                    node.get("idPedido").asLong(),
                    sdf.parse(node.get("dataHora").asText())
            );
        } catch (ParseException e) {
            throw new IOException("Erro na data");
        }
        return evento;
    }
}
