package com.fiap.mssistemalanchonete.dataprovider.repository.jpa;

import com.fiap.mssistemalanchonete.dataprovider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    List<ProdutoEntity> findByCategoria(String categoria);
}
