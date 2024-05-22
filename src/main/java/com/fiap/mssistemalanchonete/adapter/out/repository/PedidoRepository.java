package com.fiap.mssistemalanchonete.adapter.out.repository;

import com.fiap.mssistemalanchonete.adapter.out.entity.PedidoEntity;
import com.fiap.mssistemalanchonete.adapter.out.error.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonete.adapter.out.mapper.PedidoMapper;
import com.fiap.mssistemalanchonete.adapter.out.repository.jpa.IPedidoRepository;
import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import com.fiap.mssistemalanchonete.core.port.PedidoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepository implements PedidoPort {

    private final IPedidoRepository iPedidoRepository;
    private final PedidoMapper pedidoMapper;

    @Autowired
    public PedidoRepository(IPedidoRepository iPedidoRepository, PedidoMapper pedidoMapper){
        this.iPedidoRepository = iPedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        return pedidoMapper.toDomain(iPedidoRepository.save(pedidoMapper.toEntity(pedido)));
    }

    @Override
    public Pedido atualizarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = iPedidoRepository.findByCodigo(pedido.getCodigo())
          .orElseThrow(PedidoNotFoundException::new);

        pedidoMapper.merge(pedido, pedidoEntity);
        return pedidoMapper.toDomain(iPedidoRepository.save(pedidoEntity));
    }

    @Override
    public Pedido consultarPedidoPorCodigo(String codigo) {
        var pedidoEntity = iPedidoRepository.findById(codigo)
          .orElseThrow(PedidoNotFoundException::new);
        return pedidoMapper.toDomain(pedidoEntity);
    }

    @Override
    public void deletarPedido(Pedido pedido) {
        iPedidoRepository.delete(pedidoMapper.toEntity(pedido));
    }

    @Override
    public List<Pedido> listarPedidos() {
        return List.of();
    }
}
