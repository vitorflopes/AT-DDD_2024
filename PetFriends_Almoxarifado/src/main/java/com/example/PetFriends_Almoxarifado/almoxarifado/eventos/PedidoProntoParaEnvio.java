package com.example.PetFriends_Almoxarifado.almoxarifado.eventos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PedidoProntoParaEnvio implements Serializable {
    private Long idPedido;
    private Date dataHora;
    private boolean temEstoque;

    public PedidoProntoParaEnvio(Long idPedido, Date dataHora, boolean temEstoque) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.temEstoque = temEstoque;
    }
}
