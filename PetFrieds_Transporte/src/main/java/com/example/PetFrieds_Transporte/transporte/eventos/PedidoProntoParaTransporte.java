package com.example.PetFrieds_Transporte.transporte.eventos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PedidoProntoParaTransporte implements Serializable {
    private Long idPedido;
    private Date dataHora;

    public PedidoProntoParaTransporte() {
    }

    public PedidoProntoParaTransporte(Long idPedido, Date dataHora) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
    }
}
