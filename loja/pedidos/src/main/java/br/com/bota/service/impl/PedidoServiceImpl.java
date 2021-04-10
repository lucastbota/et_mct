package br.com.bota.service.impl;

import br.com.bota.entity.Pedido;
import br.com.bota.entity.enums.PedidoStatusEnum;
import br.com.bota.repository.PedidoRepository;
import br.com.bota.service.PedidoService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
public class PedidoServiceImpl implements PedidoService {
    @Inject
    private PedidoRepository repository;

    @Override
    public Pedido criar(Pedido pedido) {
        pedido.setStatus(PedidoStatusEnum.AGUARDANDO_PAGAMENTO);
        pedido.getItens().forEach(i -> i.setPedido(pedido));
        return repository.save(pedido);
    }

    @Override
    public List<Pedido> findBy(UUID clienteId) {
        return repository.findByClienteIdOrderById(clienteId);
    }

    @Override
    public void mudarStatus(Set<PedidoStatusEnum> status) {
        List<Pedido> pedidos = repository.findByStatusIn(status);
        log.info("Qtd pedidos: {}", pedidos.size());

        pedidos.forEach(logAntes().andThen(mudarStatus()).andThen(logDepois()));
    }

    @Override
    public void finalizarEntrega(UUID pedidoId) {
        repository.findById(pedidoId).ifPresent(p -> p.setStatus(PedidoStatusEnum.PEDIDO_ENTREGUE));
    }

    @Override
    public List<Pedido> obterPedidosSeparadosEntrega() {
        return repository.findByStatusIn(Set.of(PedidoStatusEnum.PEDIDO_SEPARADO_TRANSPORTADORA));
    }

    private Consumer<Pedido> logAntes() {
        return p -> log.info("Pedido {} - {}", p.getId(), p.getStatus());
    }

    private Consumer<Pedido> mudarStatus() {
        return p -> p.setStatus(p.getStatus().next());
    }

    private Consumer<Pedido> logDepois() {
        return p -> log.info("Pedido {} - {}", p.getId(), p.getStatus());
    }
}
