package com.fiap.mssistemalanchonete.core.domain.repositories;

import com.fiap.mssistemalanchonete.core.domain.entity.ProdutoEntity;
import com.fiap.mssistemalanchonete.core.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProdutoRepository extends JpaRepository<ProdutoEntity, String> {

    List<Produto> findByCategoria(String categoria);

}
