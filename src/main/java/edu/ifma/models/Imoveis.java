package edu.ifma.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "imoveis")
public class Imoveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_proprietario", nullable = false,
            foreignKey = @ForeignKey(name = "ProprietarioCliente"))
    private Clientes proprietario;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_imovel", nullable = false, foreignKey = @ForeignKey(name = "tipoImovel_Imovel"))
    private TipoImovel TipoImovel;

    @NonNull
    @Column(nullable = false, length =200)
    private String logradouro;

    @NonNull
    @Column(nullable = false, length = 45)
    private String bairro;

    @NonNull
    @Column(nullable = false, length = 10)
    private  String cep;

    @Column(columnDefinition = "TINYINT")
    private int metragem;

    @Column(columnDefinition = "TINYINT")
    private Byte dormitorios;

    @Column(columnDefinition = "TINYINT")
    private Byte banheiros;

    @Column(columnDefinition = "TINYINT")
    private Byte suites;

    @Column(name = "vagas_garagem", columnDefinition = "TINYINT")
    private Byte vagasGaragem;

    @Column(name = "valor_aluguel_sugerido")
    private Double valorAluguelSugerido;

    @Column(columnDefinition = "TEXT")
    private String obs;
}
