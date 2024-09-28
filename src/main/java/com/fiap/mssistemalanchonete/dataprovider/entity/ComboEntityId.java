package com.fiap.mssistemalanchonete.dataprovider.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class ComboEntityId {

  @Column(name = "ordemId")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
  private PedidoEntity pedido;
}
