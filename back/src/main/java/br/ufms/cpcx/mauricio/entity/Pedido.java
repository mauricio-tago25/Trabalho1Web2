package br.ufms.cpcx.mauricio.entity;

import br.ufms.cpcx.mauricio.enuns.EStatusPedido;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PED_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PES_ID_PESSOA")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "PED_STATUS")
    private EStatusPedido status;

    @Transient
    private String statusNome;

    @Column(name = "PED_DATA_COMPRA")
    private LocalDate dataCompra;

    @Column(name = "PED_DATA_ENTREGA")
    private LocalDate dataEntrega;

    @Column(name = "PED_DESCONTO")
    private BigDecimal desconto;
}