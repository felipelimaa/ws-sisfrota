package br.com.unimednatal.sisfrota.entity

import br.com.unimednatal.sisfrota.Tipo.Setor
import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@Entity
class Frota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    String prefixo

    @ManyToOne
    Modelo modelo

    @NotNull
    Long km_entrada

    Long km_saida

    @Column(length = 4)
    Integer ano

    Setor setor

    Boolean veiculoProprio

    Boolean ativo

}
