package br.ufms.cpcx.mauricio.service;

import br.ufms.cpcx.mauricio.entity.Pedido;
import br.ufms.cpcx.mauricio.enuns.EStatusPedido;
import br.ufms.cpcx.mauricio.repository.PedidoRepository;
import br.ufms.cpcx.mauricio.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        pedido.setStatusNome(pedido.getStatus().getNome());
        return pedido;
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido alterar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}