package com.fiap.mssistemalanchonete.dataprovider.repository.jpa;

import com.fiap.mssistemalanchonete.dataprovider.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPedidoRepository extends JpaRepository<PedidoEntity, String> {

  Optional<PedidoEntity> findByCodigo(String codigo);
  Page<PedidoEntity> findAllByStatusIn(List<String> statusList, Pageable pageable);
  @Query("SELECT p FROM pedido p WHERE p.status != 'FINALIZADO' ORDER BY " +
          "CASE p.status " +
          "  WHEN 'PRONTO' THEN 1 " +
          "  WHEN 'EM_PREPARACAO' THEN 2 " +
          "  WHEN 'RECEBIDO' THEN 3 " +
          "END, p.horaCheckout ASC")
  Page<PedidoEntity> findAllWithoutFinished(Pageable pageable);

}
