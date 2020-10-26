package br.ufms.cpcx.mauricio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA_FISICA")
@PrimaryKeyJoinColumn(name = "PES_ID")
public class PessoaFisica extends Pessoa {

    @Column(name = "PF_CPF", length = 20, nullable = false)
    private String cpf;

    @Column(name = "PF_RG", length = 20, nullable = false)
    private String rg;
}