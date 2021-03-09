package br.com.bota.repository;

import br.com.bota.entity.ItemPedido;
import br.com.bota.entity.ItemPedidoPK;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoItemRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
