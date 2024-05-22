package com.fiap.mssistemalanchonete.adapter.out.repository.jpa;

import com.fiap.mssistemalanchonete.adapter.out.entity.PedidoEntity;
import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPedidoRepository extends JpaRepository<PedidoEntity, String> {

  Optional<PedidoEntity> findByCodigo(String codigo);
}
