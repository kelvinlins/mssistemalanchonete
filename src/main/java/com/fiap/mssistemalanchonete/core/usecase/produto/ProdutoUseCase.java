package com.fiap.mssistemalanchonete.core.usecase.produto;

import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoAlreadyExistsException;
import com.fiap.mssistemalanchonete.core.exception.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoPort;
import com.fiap.mssistemalanchonete.core.usecase.ProdutoUseCaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoUseCase implements ProdutoUseCaseFacade {

    private final ProdutoPort produtoPort;

    @Autowired
    public ProdutoUseCase(ProdutoPort produtoPort){
        this.produtoPort = produtoPort;
    }


    @Override
    public Produto salvarProduto(Produto produto) {

        String codigo = produto.getCodigo();

        if (!ObjectUtils.isEmpty(produtoPort.consultarProdutoPorCodigo(codigo)))
            throw new ProdutoAlreadyExistsException(codigo);

        return produtoPort.salvarProduto(produto);
    }

    @Override
    public List<Produto> consultarProdutoPorCategoria(String categoria) {
        return produtoPort.consultarProdutoPorCategoria(categoria);
    }

    @Override
    public Produto atualizarProduto(Produto produto, String codigo) {

        Produto produtoAntigo = produtoPort.consultarProdutoPorCodigo(codigo);

        if (ObjectUtils.isEmpty(produtoAntigo))
            throw new ProdutoNotFoundException(codigo);

        return produtoPort.atualizarProduto(produtoAntigo, produto);
    }

    @Override
    public void deletarProduto(String codigo) {

        Produto produto = produtoPort.consultarProdutoPorCodigo(codigo);

        if (ObjectUtils.isEmpty(produto))
            throw new ProdutoNotFoundException(codigo);

        produtoPort.deletarProduto(produto);
    }

    @Override
    public List<Produto> consultarTodosProdutos() {
        return produtoPort.consultarTodosProdutos();
    }

    public Produto consultarProdutoPorCodigo(String codigo) {
        return Optional.ofNullable(produtoPort.consultarProdutoPorCodigo(codigo))
                .orElseThrow(() -> new ProdutoNotFoundException(codigo));
    }
}
