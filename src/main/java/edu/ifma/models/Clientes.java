package edu.ifma.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, length = 80)
    private String nome;

    @NonNull
    @Column(nullable = false,length = 11, unique = true)
    private String cpf;

    @NonNull
    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(name = "dt_nascimento")
    private Date dataNascimento;
}
