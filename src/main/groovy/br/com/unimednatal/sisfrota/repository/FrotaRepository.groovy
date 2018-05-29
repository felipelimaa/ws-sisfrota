package br.com.unimednatal.sisfrota.repository

import br.com.unimednatal.sisfrota.Tipo.Setor
import br.com.unimednatal.sisfrota.entity.Frota
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface FrotaRepository extends PagingAndSortingRepository<Frota, Long>{
    Frota findOneById(Long id)
    Frota findByAtivo(Boolean ativo)
    Frota findByPrefixoAndAtivoAndSetor(String prefixo, Boolean ativo, Setor setor)

}