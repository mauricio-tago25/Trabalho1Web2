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
@Table(name = "TB_PESSOA_JURIDICA")
@PrimaryKeyJoinColumn(name = "PES_ID")
public class PessoaJuridica extends Pessoa {

    @Column(name = "PJ_CNPJ", length = 256, nullable = false)
    private String cnpj;
}