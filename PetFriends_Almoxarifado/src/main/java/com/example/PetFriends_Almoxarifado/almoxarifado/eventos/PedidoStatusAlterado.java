package com.example.PetFriends_Almoxarifado.almoxarifado.eventos;

import com.example.PetFriends_Almoxarifado.almoxarifado.domain.PedidoStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PedidoStatusAlterado implements Serializable {
    private Long idPedido;
    private PedidoStatus novoStatus;
    private Date dataHoraAlteracao;

    public PedidoStatusAlterado() {}

    public PedidoStatusAlterado(Long idPedido, PedidoStatus novoStatus) {
        this.idPedido = idPedido;
        this.novoStatus = novoStatus;
        this.dataHoraAlteracao = new Date();
    }

    public PedidoStatusAlterado(Long idPedido, PedidoStatus novoStatus, Date dataHoraAlteracao) {
        this.idPedido = idPedido;
        this.novoStatus = novoStatus;
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    @Override
    public String toString() {
        return "PedidoStatusAlterado{" + "idPedido=" + idPedido + ", novoStatus=" + novoStatus + ", dataHoraAlteracao=" + dataHoraAlteracao + '}';
    }
}
