package com.fiap.mssistemalanchonete.adapter.out.repository.jpa;

import com.fiap.mssistemalanchonete.adapter.out.entity.ProdutoEntity;
import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    List<Produto> findByCategoria(String categoria);
}
