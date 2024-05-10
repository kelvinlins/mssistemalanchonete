package com.fiap.mssistemalanchonete.adapter.out.repository.jpa;

import com.fiap.mssistemalanchonete.adapter.out.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, String> {
}
