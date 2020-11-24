package br.ufms.cpcx.mauricio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IP_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PED_ID_PEDIDO")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "PRO_ID_PRODUTO")
    private Produto produto;

    @Column(name = "IP_QUANTIDADE")
    private Long quantidade;
}
