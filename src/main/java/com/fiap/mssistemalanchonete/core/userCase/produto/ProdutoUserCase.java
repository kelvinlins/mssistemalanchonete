package com.fiap.mssistemalanchonete.core.userCase.produto;

import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import com.fiap.mssistemalanchonete.core.port.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ProdutoUserCase {

    private final ProdutoPort produtoPort;

    @Autowired
    public ProdutoUserCase(ProdutoPort produtoPort){
        this.produtoPort = produtoPort;
    }

    public Produto salvarProduto(Produto produto) throws Exception {

        String codigo = produto.getCodigo();

        if (!ObjectUtils.isEmpty(produtoPort.consultarProdutoPorCodigo(codigo)))
            throw new Exception();

        return produtoPort.salvarProduto(produto);
    }

    public List<Produto> consultarProdutoPorCategoria(String code) {
        return Collections.emptyList(); //TODO implementar
    }

    public Produto atualizarProduto(Produto produto, String codigo) throws Exception {

        Produto produtoAntigo = produtoPort.consultarProdutoPorCodigo(codigo);

        if (!ObjectUtils.isEmpty(produtoAntigo))
            throw new Exception();

        return produtoPort.atualizarProduto(produtoAntigo, produto);
    }

    public void deletarProduto(String codigo) throws Exception {

        Produto produto = produtoPort.consultarProdutoPorCodigo(codigo);

        if (!ObjectUtils.isEmpty(produto))
            throw new Exception();

        produtoPort.deletarProduto(produto);
    }
}
