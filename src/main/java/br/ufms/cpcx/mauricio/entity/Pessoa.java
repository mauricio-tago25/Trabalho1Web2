package br.ufms.cpcx.mauricio.entity;

import br.ufms.cpcx.mauricio.enuns.ESituacaoPessoa;
import br.ufms.cpcx.mauricio.enuns.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @Column(name = "PES_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "PES_ID_RESPONSAVEL")
    private Pessoa idResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "PES_TIPO_PESSOA")
    private ETipoPessoa tipoPessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "PES_SITUACAO_PESSOA")
    private ESituacaoPessoa situacaoPessoa;

    @Column(name = "PES_NOME", length = 256, nullable = false)
    private String nome;

    @Column(name = "PES_APELIDO", length = 256, nullable = false)
    private String apelido;

    @Column(name = "CLI_DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    public Pessoa(Long id) {
        this.id = id;
    }
}
