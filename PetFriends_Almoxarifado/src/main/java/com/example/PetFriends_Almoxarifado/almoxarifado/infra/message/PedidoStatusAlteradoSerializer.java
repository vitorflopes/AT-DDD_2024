package com.example.PetFriends_Almoxarifado.almoxarifado.infra.message;

import com.example.PetFriends_Almoxarifado.almoxarifado.eventos.PedidoStatusAlterado;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PedidoStatusAlteradoSerializer extends StdSerializer<PedidoStatusAlterado> {
    public PedidoStatusAlteradoSerializer() {
        super(PedidoStatusAlterado.class);
    }

    @Override
    public void serialize(PedidoStatusAlterado evento, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("idPedido", evento.getIdPedido());
        jgen.writeStringField("novoStatus", evento.getNovoStatus().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String data = sdf.format(evento.getDataHoraAlteracao());
        jgen.writeStringField("dataHoraAlteracao", data);
        jgen.writeEndObject();
    }
}
