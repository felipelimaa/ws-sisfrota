package br.com.unimednatal.sisfrota.entity

import br.com.unimednatal.sisfrota.Tipo.CategoriaServico
import com.fasterxml.jackson.annotation.JsonFormat

import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Enumerated
    CategoriaServico categoriaServico

    @ManyToOne
    Motorista motorista

    @ManyToOne
    Frota frota

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date dataServico

    String descricao

    Float valor

    Long km_servico

    Boolean ativo
}
