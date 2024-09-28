package com.fiap.mssistemalanchonete.dataprovider.repository;

import com.fiap.mssistemalanchonete.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonete.core.exception.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonete.dataprovider.mapper.PedidoMapper;
import com.fiap.mssistemalanchonete.dataprovider.repository.jpa.IPedidoRepository;
import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.enums.StatusPedidoEnum;
import com.fiap.mssistemalanchonete.core.port.PedidoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

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
        PedidoEntity entity = pedidoMapper.toEntity(pedido);
        if (Objects.nonNull(entity.getCombos())){
            entity.getCombos().forEach(comboEntity -> comboEntity.setPedido(entity));
        }
        return pedidoMapper.toDomain(
          iPedidoRepository.save(
            entity
          )
        );
    }

    @Override
    public Pedido atualizarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = iPedidoRepository.findByCodigo(pedido.getCodigo())
          .orElseThrow(PedidoNotFoundException::new);

        pedidoMapper.merge(pedido, pedidoEntity);
        if (Objects.nonNull(pedidoEntity.getCombos())){
            pedidoEntity.getCombos().forEach(comboEntity -> comboEntity.setPedido(pedidoEntity));
        }
        return pedidoMapper.toDomain(iPedidoRepository.save(pedidoEntity));
    }

    @Override
    public Pedido consultarPedidoPorCodigo(String codigo) {
        return iPedidoRepository.findById(codigo)
                .map(pedidoMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deletarPedido(Pedido pedido) {
        iPedidoRepository.delete(pedidoMapper.toEntity(pedido));
    }

    @Override
    public Page<Pedido> listarPedidosPorStatus(Pageable pageable, List<StatusPedidoEnum> statusList) {
        List<String> statusNameList = statusList.stream()
          .map(StatusPedidoEnum::name)
          .toList();
        return pedidoMapper.toDomainPage(iPedidoRepository.findAllByStatusIn(statusNameList, pageable));
    }

    @Override
    public Page<Pedido> listarPedidos(Pageable pageable) {
        Page<PedidoEntity> pedidoEntityPage = iPedidoRepository.findAllWithoutFinished(pageable);
        return pedidoMapper.toDomainPage(pedidoEntityPage);
    }
}
