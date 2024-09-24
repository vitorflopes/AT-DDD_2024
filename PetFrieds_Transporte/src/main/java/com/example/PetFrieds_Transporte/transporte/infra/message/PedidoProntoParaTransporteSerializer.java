package com.example.PetFrieds_Transporte.transporte.infra.message;

import com.example.PetFrieds_Transporte.transporte.eventos.PedidoProntoParaTransporte;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PedidoProntoParaTransporteSerializer extends StdSerializer<PedidoProntoParaTransporte> {
    public PedidoProntoParaTransporteSerializer() {
        super(PedidoProntoParaTransporte.class);
    }

    @Override
    public void serialize(PedidoProntoParaTransporte evento, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("idPedido", evento.getIdPedido());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String data = sdf.format(evento.getDataHora());
        jgen.writeStringField("dataHora", data);
        jgen.writeEndObject();
    }
}
