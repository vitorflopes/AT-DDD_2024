package com.example.PetFrieds_Transporte.transporte.eventos;

import lombok.Data;

import java.util.Date;

@Data
public class PedidoEntregue {
    private Long idPedido;
    private Date dataHoraEntrega;

    public PedidoEntregue() {
    }

    public PedidoEntregue(Long idPedido, Date dataHoraEntrega) {
        this.idPedido = idPedido;
        this.dataHoraEntrega = dataHoraEntrega;
    }
}
