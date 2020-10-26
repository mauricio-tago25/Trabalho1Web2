package br.ufms.cpcx.mauricio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRO_ID")
    private Long id;

    @Column(name = "PRO_DESCRICAO")
    private String descricao;

    @Column(name = "PRO_QUANTIDADE_ESTOQUE")
    private Long quantidadeEstoque;

    @Column(name = "PRO_IDADE_PERMITIDA")
    private Long idadePermitida;

    @Column(name = "PRO_PRECO_COMPRA", precision = 20, scale = 2)
    private BigDecimal precoCompra;

    @Column(name = "PRO_PRECO_VENDA_FISICA", precision = 20, scale = 2)
    private BigDecimal precoVendaFisica;

    @Column(name = "PRO_PRECO_VENDA_JURIDICA", precision = 20, scale = 2)
    private BigDecimal precoVendaJuridica;

    public Produto(Long id) {
        this.id = id;
    }
}
