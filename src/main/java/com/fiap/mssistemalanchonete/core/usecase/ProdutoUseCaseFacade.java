package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Produto;

import java.util.List;

public interface ProdutoUseCaseFacade {

    Produto salvarProduto(Produto produto);

    List<Produto> consultarProdutoPorCategoria(String categoria);

    Produto atualizarProduto(Produto produto, String codigo);

    void deletarProduto(String codigo);

    List<Produto> consultarTodosProdutos();

    Produto consultarProdutoPorCodigo(String codigo);

}
