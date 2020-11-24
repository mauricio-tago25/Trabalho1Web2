package br.ufms.cpcx.mauricio.repository;

import br.ufms.cpcx.mauricio.entity.Produto;
import br.ufms.cpcx.mauricio.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
