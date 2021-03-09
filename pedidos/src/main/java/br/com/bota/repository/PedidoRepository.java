package br.com.bota.repository;

import br.com.bota.entity.Pedido;
import br.com.bota.entity.enums.PedidoStatusEnum;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByClienteIdOrderById(UUID id);

    List<Pedido> findByStatusIn(Set<PedidoStatusEnum> status);
}
