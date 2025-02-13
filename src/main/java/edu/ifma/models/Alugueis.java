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
@Table(name = "alugueis")
public class Alugueis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_locacao", nullable = false,
            foreignKey = @ForeignKey(name = "alocaLocacao"))
    private Locacao locacao;

    @NonNull
    @Column(name = "data_vencimento",nullable = false)
    private Date dataVencimento;

    @Column(name = "valor_pago", precision = 10, scale = 3)
    private BigDecimal valorPago;

    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(columnDefinition = "TEXT")
    private String obs;
}
