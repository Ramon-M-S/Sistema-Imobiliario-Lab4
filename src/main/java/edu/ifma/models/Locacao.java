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
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false,
            foreignKey = @ForeignKey(name = "clienteInquilino"))
    private Clientes inquilino;


    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false,
            foreignKey = @ForeignKey(name = "imoveis_imovel"))
    private Imoveis imovel;

    @Column(precision = 10, scale = 3)
    private BigDecimal valorAluguel;

    @Column(precision = 10, scale = 3)
    private BigDecimal percentualMulta;

    @Column(name = "dia_vencimento", columnDefinition = "TINYINT")
    private Byte diaVencimento;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    private boolean ativo;

    @Column(columnDefinition = "TEXT")
    private String obs;
}
