package com.fiap.mssistemalanchonete.dataprovider.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "combo")
public class ComboEntity {

  @Id
  @Getter(value = AccessLevel.NONE)
  @Setter(value = AccessLevel.NONE)
  private ComboEntityId id;

  @ElementCollection
  @MapKeyJoinColumn(name="codigoProduto")
  @Column(name="quantidade")
  @CollectionTable(name="combo_itens")
  private Map<ProdutoEntity,Integer> itens;


  public void setId(Integer id) {
    if (Objects.isNull(this.id)){
      this.id = new ComboEntityId();
    }
    this.id.setId(id);
  }

  public Integer getId() {
    if (Objects.isNull(this.id)){
      return null;
    }
    return this.id.getId();
  }

  public void setPedido(PedidoEntity pedido) {
    if (Objects.isNull(this.id)){
      this.id = new ComboEntityId();
    }
    this.id.setPedido(pedido);
  }

  public PedidoEntity getPedido() {
    if (Objects.isNull(this.id)){
      return null;
    }
    return this.id.getPedido();
  }
}
