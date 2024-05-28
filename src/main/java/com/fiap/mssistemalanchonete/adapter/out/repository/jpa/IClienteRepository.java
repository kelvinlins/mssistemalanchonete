package com.fiap.mssistemalanchonete.adapter.out.repository.jpa;

import com.fiap.mssistemalanchonete.adapter.out.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
  Optional<ClienteEntity> findByCpf(String cpf);
}
