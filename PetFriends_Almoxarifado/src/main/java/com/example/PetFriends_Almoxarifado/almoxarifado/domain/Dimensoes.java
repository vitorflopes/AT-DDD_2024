package com.example.PetFriends_Almoxarifado.almoxarifado.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class Dimensoes {
    private final BigDecimal altura;
    private final BigDecimal largura;
    private final BigDecimal profundidade;

    public Dimensoes(BigDecimal altura, BigDecimal largura, BigDecimal profundidade) {
        if (altura == null || largura == null || profundidade == null) {
            throw new IllegalArgumentException("Valores não podem ser nulos");
        } else if (altura.compareTo(BigDecimal.ZERO) < 0 || largura.compareTo(BigDecimal.ZERO) < 0 || profundidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valores não podem ser negativos");
        }

        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimensoes dimensoes = (Dimensoes) o;
        return Objects.equals(altura, dimensoes.altura) && Objects.equals(largura, dimensoes.largura) && Objects.equals(profundidade, dimensoes.profundidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(altura, largura, profundidade);
    }
}
