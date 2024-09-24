package com.example.PetFriends_Almoxarifado.almoxarifado.infra.repository;

import com.example.PetFriends_Almoxarifado.almoxarifado.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
