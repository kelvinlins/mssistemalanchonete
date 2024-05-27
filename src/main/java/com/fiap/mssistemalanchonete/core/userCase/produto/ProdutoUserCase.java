package com.fiap.mssistemalanchonete.core.userCase.produto;

import com.fiap.mssistemalanchonete.core.domain.error.exception.ProdutoNotFoundException;
import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ProdutoUserCase {

    private final ProdutoPort produtoPort;

    @Autowired
    public ProdutoUserCase(ProdutoPort produtoPort){
        this.produtoPort = produtoPort;
    }

    public Produto salvarProduto(Produto produto) throws Exception {

        String codigo = produto.getCodigo();

        if (!ObjectUtils.isEmpty(consultarProdutoPorCodigo(codigo)))
            throw new Exception();

        return produtoPort.salvarProduto(produto);
    }

    public List<Produto> consultarProdutoPorCategoria(String code) {
        return Collections.emptyList(); //TODO implementar
    }

    public Produto atualizarProduto(Produto produto, String codigo) throws Exception {

        Produto produtoAntigo = consultarProdutoPorCodigo(codigo);
        return produtoPort.atualizarProduto(produtoAntigo, produto);
    }

    public void deletarProduto(String codigo) throws Exception {

        Produto produto = consultarProdutoPorCodigo(codigo);
        produtoPort.deletarProduto(produto);
    }

    public Produto consultarProdutoPorCodigo(String codigo) {
        return produtoPort.consultarProdutoPorCodigo(codigo)
          .orElseThrow(()-> new ProdutoNotFoundException(codigo));
    }
}
