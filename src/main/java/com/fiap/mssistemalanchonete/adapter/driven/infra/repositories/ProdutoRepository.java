package com.fiap.mssistemalanchonete.adapter.driven.infra.repositories;

import com.fiap.mssistemalanchonete.core.domain.entity.ProdutoEntity;
import com.fiap.mssistemalanchonete.core.domain.produto.Produto;
import com.fiap.mssistemalanchonete.core.domain.repositories.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoRepository {
    @Autowired
    IProdutoRepository repository;

    public Produto registraProduto(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoEntity.builder()
                .code(produto.getCode())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .categoria(produto.getCategoria())
                .build();

        repository.save(produtoEntity);
        return produto;

    }

    public List<Produto> retornaProdutoPorCategoria(String categoria){
        return repository.findByCategoria(categoria);
    }

}
