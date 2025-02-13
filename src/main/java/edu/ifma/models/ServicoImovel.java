package edu.ifma.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "servicosImovel")
public class ServicoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false,
            foreignKey = @ForeignKey(name = "profissionaisProfissional"))
    private Profissionais profissionais;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false,
            foreignKey = @ForeignKey(name = "imoveisImovel"))
    private Imoveis imovel;

    @Column(name = "data_servico")
    private Date dataServico;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(columnDefinition = "TEXT")
    private String obs;
}
