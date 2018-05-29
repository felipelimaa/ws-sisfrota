package br.com.unimednatal.sisfrota.repository

import br.com.unimednatal.sisfrota.entity.Marca
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MarcaRepository extends PagingAndSortingRepository<Marca, Long>{
    Marca findOneByNome(String nome)
    Marca findOneById(Long id_marca)
    Marca findByAtivo(Boolean ativo)
}