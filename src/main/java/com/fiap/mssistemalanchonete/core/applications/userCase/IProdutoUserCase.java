package com.fiap.mssistemalanchonete.core.applications.userCase;

import com.fiap.mssistemalanchonete.core.domain.produto.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProdutoUserCase {

    Produto setProduto(Produto produto);

    List<Produto> getProdutoPorCategoria(String code);

}
