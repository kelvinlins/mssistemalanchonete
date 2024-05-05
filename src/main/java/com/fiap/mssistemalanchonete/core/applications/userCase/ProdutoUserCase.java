package com.fiap.mssistemalanchonete.core.applications.userCase;

import com.fiap.mssistemalanchonete.adapter.driven.infra.repositories.ProdutoRepository;
import com.fiap.mssistemalanchonete.core.domain.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoUserCase implements IProdutoUserCase {

    @Autowired
    ProdutoRepository repository;

    @Override
    public Produto setProduto(Produto produto) {
        return repository.registraProduto(produto);
    }

    @Override
    public List<Produto> getProdutoPorCategoria(String code) {
        return repository.retornaProdutoPorCategoria(code);
    }

}
