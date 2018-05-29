package br.com.unimednatal.sisfrota.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@Entity
class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(length = 100)
    @NotNull
    String nome

    @ManyToOne
    Marca marca

    Boolean ativo
}
