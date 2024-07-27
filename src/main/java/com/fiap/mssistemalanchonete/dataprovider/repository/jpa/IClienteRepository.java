package com.fiap.mssistemalanchonete.dataprovider.repository.jpa;

import com.fiap.mssistemalanchonete.dataprovider.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
  Optional<ClienteEntity> findByCpf(String cpf);
}
