package com.example.PetFriends_Almoxarifado.almoxarifado.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private int quantidadeEmEstoque;

    @Embedded
    private Dimensoes dimensoes;
}
