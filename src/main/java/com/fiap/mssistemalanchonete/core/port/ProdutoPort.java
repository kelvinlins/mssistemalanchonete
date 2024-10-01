package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.model.Produto;

import java.util.List;

public interface ProdutoPort {
    Produto salvarProduto(Produto produto);
    List<Produto> consultarProdutoPorCategoria(String categoria);
    List<Produto> consultarTodosProdutos();
    Produto atualizarProduto(Produto produtoAntigo, Produto produtoNovo);
    Produto consultarProdutoPorCodigo(String codigo);
    void deletarProduto(Produto produto);
}
