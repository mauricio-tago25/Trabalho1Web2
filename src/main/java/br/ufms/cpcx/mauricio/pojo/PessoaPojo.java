package br.ufms.cpcx.mauricio.pojo;

import br.ufms.cpcx.mauricio.entity.Pessoa;
import br.ufms.cpcx.mauricio.entity.PessoaFisica;
import br.ufms.cpcx.mauricio.entity.PessoaJuridica;
import br.ufms.cpcx.mauricio.enuns.ESituacaoPessoa;
import br.ufms.cpcx.mauricio.enuns.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaPojo {

    private Long id;
    private Pessoa idResponsavel;
    private ETipoPessoa tipoPessoa;
    private ESituacaoPessoa situacaoPessoa;
    private String nome;
    private String apelido;
    private LocalDate dataNascimento;
    private String cpf;
    private String rg;
    private String cnpj;

    public PessoaFisica gerarPessoaFisica(PessoaPojo pessoaPojo) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setId(pessoaPojo.getId());
        pessoaFisica.setIdResponsavel(pessoaPojo.getIdResponsavel());
        pessoaFisica.setTipoPessoa(pessoaPojo.getTipoPessoa());
        pessoaFisica.setSituacaoPessoa(pessoaPojo.getSituacaoPessoa());
        pessoaFisica.setNome(pessoaPojo.getNome());
        pessoaFisica.setApelido(pessoaPojo.getApelido());
        pessoaFisica.setDataNascimento(pessoaPojo.getDataNascimento());
        pessoaFisica.setCpf(pessoaPojo.getCpf());
        pessoaFisica.setRg(pessoaPojo.getRg());

        return pessoaFisica;
    }

    public PessoaJuridica gerarPessoaJuridica(PessoaPojo pessoaPojo) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(pessoaPojo.getId());
        pessoaJuridica.setIdResponsavel(pessoaPojo.getIdResponsavel());
        pessoaJuridica.setTipoPessoa(pessoaPojo.getTipoPessoa());
        pessoaJuridica.setSituacaoPessoa(pessoaPojo.getSituacaoPessoa());
        pessoaJuridica.setNome(pessoaPojo.getNome());
        pessoaJuridica.setApelido(pessoaPojo.getApelido());
        pessoaJuridica.setDataNascimento(pessoaPojo.getDataNascimento());
        pessoaJuridica.setCnpj(pessoaPojo.getCnpj());

        return pessoaJuridica;
    }
}
