package com.fiap.mssistemalanchonete.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="pedido")
public class PedidoEntity {

    @Id
    @Column(name="codigoPedido")
    private String codigo;

    @JoinColumn(name = "codigoCliente", nullable = true)
    @ManyToOne
    private ClienteEntity cliente;

    @Column(name = "codigoProduto", nullable = false)
    @ManyToMany
    private List<ProdutoEntity> itens;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "statusPedido", nullable = false)
    private String status;
}
