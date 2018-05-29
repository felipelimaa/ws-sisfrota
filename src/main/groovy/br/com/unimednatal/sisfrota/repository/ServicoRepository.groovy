package br.com.unimednatal.sisfrota.repository

import br.com.unimednatal.sisfrota.Tipo.CategoriaServico
import br.com.unimednatal.sisfrota.entity.Servico
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicoRepository extends PagingAndSortingRepository<Servico, Long>{
    Servico findOneById(Long id)
    Servico findByAtivo(Boolean ativo)

}