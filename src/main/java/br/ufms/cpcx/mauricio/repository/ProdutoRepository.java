package br.ufms.cpcx.mauricio.repository;

import br.ufms.cpcx.mauricio.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
