package com.example.PetFriends_Almoxarifado.almoxarifado.infra.message;

import com.example.PetFriends_Almoxarifado.almoxarifado.domain.PedidoStatus;
import com.example.PetFriends_Almoxarifado.almoxarifado.eventos.PedidoStatusAlterado;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PedidoStatusAlteradoDeserializer extends StdDeserializer<PedidoStatusAlterado> {
    public PedidoStatusAlteradoDeserializer() {
        super(PedidoStatusAlterado.class);
    }

    @Override
    public PedidoStatusAlterado deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        PedidoStatusAlterado evento = null;
        JsonNode node = jp.getCodec().readTree(jp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        try {
            evento = new PedidoStatusAlterado(
                    node.get("idPedido").asLong(),
                    PedidoStatus.valueOf(node.get("novoStatus").asText()),
                    sdf.parse(node.get("dataHoraAlteracao").asText())
            );
        } catch (ParseException e) {
            throw new IOException("Erro na data!");
        }
        return evento;
    }
}
