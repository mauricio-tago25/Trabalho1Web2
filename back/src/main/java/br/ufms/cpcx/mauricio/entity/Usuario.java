package br.ufms.cpcx.mauricio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USU_ID")
    private Long id;

    @Column(name = "USU_IS_ADMINISTRADOR")
    private Boolean isAdministrador;

    @Column(name = "USU_LOGIN")
    private String login;

    @Column(name = "USU_SENHA")
    private String senha;

    @OneToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

}
