package com.fiap.mssistemalanchonete.core.domain.repositories;

import com.fiap.mssistemalanchonete.core.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, String> {

}
