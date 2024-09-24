package com.example.PetFrieds_Transporte.transporte.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "entregas")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPedido;
    private Date dataColeta;
    private Date dataEntrega;
    private StatusEntrega status;

    @Embedded
    private Endereco enderecoEntrega;
}
