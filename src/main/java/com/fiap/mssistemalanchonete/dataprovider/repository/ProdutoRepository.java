package com.fiap.mssistemalanchonete.dataprovider.repository;

import com.fiap.mssistemalanchonete.dataprovider.entity.ProdutoEntity;
import com.fiap.mssistemalanchonete.dataprovider.mapper.ProdutoMapper;
import com.fiap.mssistemalanchonete.dataprovider.repository.jpa.IProdutoRepository;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository implements ProdutoPort {

    private final IProdutoRepository iProdutoRepository;
    private final ProdutoMapper produtoMapper;

    @Autowired
    public ProdutoRepository(IProdutoRepository iProdutoRepository, ProdutoMapper produtoMapper){
        this.iProdutoRepository = iProdutoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        return produtoMapper.toDomain(iProdutoRepository.save(produtoMapper.toEntity(produto)));
    }

    @Override
    public List<Produto> consultarProdutoPorCategoria(String categoria) {
        return iProdutoRepository.findByCategoria(categoria)
                .stream()
                .map(produtoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Produto> consultarTodosProdutos() {
        return iProdutoRepository.findAll()
                .stream()
                .map(produtoMapper::toDomain)
                .toList();
    }

    @Override
    public Produto atualizarProduto(Produto produtoAntigo, Produto produtoNovo) {
        ProdutoEntity produtoEntity = produtoMapper.toEntity(produtoAntigo);
        produtoMapper.merge(produtoNovo, produtoEntity);
        return produtoMapper.toDomain(iProdutoRepository.save(produtoEntity));
    }

    @Override
    public Produto consultarProdutoPorCodigo(String codigo) {
        return iProdutoRepository.findById(codigo)
                .map(produtoMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deletarProduto(Produto produto) {
        iProdutoRepository.delete(produtoMapper.toEntity(produto));
    }
}
