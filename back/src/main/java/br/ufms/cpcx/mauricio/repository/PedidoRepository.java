package br.ufms.cpcx.mauricio.repository;

import br.ufms.cpcx.mauricio.entity.Pedido;
import br.ufms.cpcx.mauricio.enuns.EStatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
