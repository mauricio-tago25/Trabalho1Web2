package br.ufms.cpcx.mauricio.repository;

import br.ufms.cpcx.mauricio.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

}
