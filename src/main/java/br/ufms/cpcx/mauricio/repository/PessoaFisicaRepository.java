package br.ufms.cpcx.mauricio.repository;

import br.ufms.cpcx.mauricio.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
}
