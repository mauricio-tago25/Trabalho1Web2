package br.ufms.cpcx.mauricio.service;

import br.ufms.cpcx.mauricio.entity.Pessoa;
import br.ufms.cpcx.mauricio.enuns.ETipoPessoa;
import br.ufms.cpcx.mauricio.pojo.PessoaPojo;
import br.ufms.cpcx.mauricio.repository.PessoaFisicaRepository;
import br.ufms.cpcx.mauricio.repository.PessoaJuridicaRepository;
import br.ufms.cpcx.mauricio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public List<Pessoa> buscarTodas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).get();
    }

    public Object salvar(PessoaPojo pessoaPojo) {
        LocalDate adulto = LocalDate.now();
        adulto.minusYears(18);

        if (pessoaPojo.getTipoPessoa().equals(ETipoPessoa.FISICA)) {

            if (adulto.isAfter(pessoaPojo.getDataNascimento())) {

                return pessoaFisicaRepository.save(pessoaPojo.gerarPessoaFisica(pessoaPojo));
            } else if (pessoaPojo.getIdResponsavel() == null) {

                throw new RuntimeException("É necessário ter acompanhamento de uma pessoa adulta", null);
            } else {

                return pessoaFisicaRepository.save(pessoaPojo.gerarPessoaFisica(pessoaPojo));
            }
        } else {

            return pessoaJuridicaRepository.save(pessoaPojo.gerarPessoaJuridica(pessoaPojo));
        }
    }

    public Pessoa alterar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
}