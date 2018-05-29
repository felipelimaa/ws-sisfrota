package br.com.unimednatal.sisfrota.repository

import br.com.unimednatal.sisfrota.entity.Motorista
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MotoristaRepository extends PagingAndSortingRepository<Motorista, Long>{
    Motorista findOneById(Long id)
    Motorista findOneByCpf(String cpf)
    Motorista findByAtivo(Boolean ativo)

}