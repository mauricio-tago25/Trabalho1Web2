package br.ufms.cpcx.mauricio.dto;


import br.ufms.cpcx.mauricio.entity.Produto;
import br.ufms.cpcx.mauricio.enuns.ETipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String descricao;
    private Long quantidadeEstoque;
    private Long idadePermitida;
    private BigDecimal precoCompra;

    public ProdutoDTO(Produto produto, ETipoPessoa tipoPessoa) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.idadePermitida = produto.getIdadePermitida();

        if (tipoPessoa.equals(ETipoPessoa.FISICA)) {
            this.precoCompra = produto.getPrecoVendaFisica();
        } else if (tipoPessoa.equals(ETipoPessoa.JURIDICA)) {
            this.precoCompra = produto.getPrecoVendaJuridica();
        }
    }
}
