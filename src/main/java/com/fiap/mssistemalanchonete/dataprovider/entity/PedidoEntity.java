package com.fiap.mssistemalanchonete.dataprovider.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="pedido")
public class PedidoEntity {

    @Id
    @Column(name="codigo_pedido")
    private String codigo;

    @JoinColumn(name = "codigoCliente", nullable = true)
    @ManyToOne
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComboEntity> combos;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "status_pedido", nullable = false)
    private String status;

    @Column(name = "hora_checkout")
    LocalDateTime horaCheckout;
}
