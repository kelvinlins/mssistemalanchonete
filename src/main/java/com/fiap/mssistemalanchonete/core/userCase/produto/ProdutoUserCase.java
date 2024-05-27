package com.fiap.mssistemalanchonete.core.userCase.produto;

import com.fiap.mssistemalanchonete.core.domain.error.exception.ProdutoAlreadyExistsException;
import com.fiap.mssistemalanchonete.core.domain.error.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoUserCase {

    private final ProdutoPort produtoPort;

    @Autowired
    public ProdutoUserCase(ProdutoPort produtoPort){
        this.produtoPort = produtoPort;
    }

    public Produto salvarProduto(Produto produto) {

        String codigo = produto.getCodigo();

        if (!ObjectUtils.isEmpty(produtoPort.consultarProdutoPorCodigo(codigo)))
            throw new ProdutoAlreadyExistsException(codigo);

        return produtoPort.salvarProduto(produto);
    }

    public List<Produto> consultarProdutoPorCategoria(String categoria) {
        return produtoPort.consultarProdutoPorCategoria(categoria);
    }

    public Produto atualizarProduto(Produto produto, String codigo) {

        Produto produtoAntigo = produtoPort.consultarProdutoPorCodigo(codigo);

        if (ObjectUtils.isEmpty(produtoAntigo))
            throw new ProdutoNotFoundException(codigo);

        return produtoPort.atualizarProduto(produtoAntigo, produto);
    }

    public void deletarProduto(String codigo) {

        Produto produto = produtoPort.consultarProdutoPorCodigo(codigo);

        if (ObjectUtils.isEmpty(produto))
            throw new ProdutoNotFoundException(codigo);

        produtoPort.deletarProduto(produto);
    }

    public List<Produto> consultarTodosProdutos() {
        return produtoPort.consultarTodosProdutos();
    }

    public Produto consultarProdutoPorCodigo(String codigo) {
        return Optional.ofNullable(produtoPort.consultarProdutoPorCodigo(codigo))
                .orElseThrow(() -> new ProdutoNotFoundException(codigo));
    }
}
