package br.com.unimednatal.sisfrota.entity

import br.com.unimednatal.sisfrota.Tipo.Setor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(length = 100)
    String nome

    @Column(length = 11)
    String cpf

    String cnh

    @Column(length = 11)
    String telefone

    @Column(length = 11)
    String celular

    Setor setor

    Boolean ativo
}
