package br.com.unimednatal.sisfrota.repository

import br.com.unimednatal.sisfrota.entity.Modelo
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ModeloRepository extends PagingAndSortingRepository<Modelo, Long>{
    Modelo findOneByNome(String nome)
    Modelo findOneById(Long id)
    Modelo findByAtivo(Boolean ativo)
}