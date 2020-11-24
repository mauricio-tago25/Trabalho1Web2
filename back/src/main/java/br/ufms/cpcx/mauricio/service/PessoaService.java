package br.ufms.cpcx.mauricio.service;

import br.ufms.cpcx.mauricio.entity.Pessoa;
import br.ufms.cpcx.mauricio.enuns.ESituacaoPessoa;
import br.ufms.cpcx.mauricio.enuns.ETipoPessoa;
import br.ufms.cpcx.mauricio.pojo.PessoaPojo;
import br.ufms.cpcx.mauricio.repository.PessoaFisicaRepository;
import br.ufms.cpcx.mauricio.repository.PessoaJuridicaRepository;
import br.ufms.cpcx.mauricio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Pessoa> buscarTodasStream(Long idResponsavel, String nomeResponsavel, String tipoPessoa, String situacaoPessoa) {
        List<Pessoa> filtroPessoas;

        filtroPessoas = buscarTodas().stream()
                .filter(pessoa -> pessoa.getIdResponsavel().getId().equals(idResponsavel))
                .filter(pessoa -> pessoa.getIdResponsavel().getNome().equals(nomeResponsavel))
                .filter(pessoa -> pessoa.getTipoPessoa().toString().equals(tipoPessoa))
                .filter(pessoa -> pessoa.getSituacaoPessoa().toString().equals(situacaoPessoa))
                .collect(Collectors.toList());

        return filtroPessoas;
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