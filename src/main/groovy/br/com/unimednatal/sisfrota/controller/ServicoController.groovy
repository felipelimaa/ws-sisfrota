package br.com.unimednatal.sisfrota.controller

import br.com.unimednatal.sisfrota.Tipo.CategoriaServico
import br.com.unimednatal.sisfrota.entity.Servico
import br.com.unimednatal.sisfrota.repository.ServicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/servico")
class ServicoController {
    @Autowired
    ServicoRepository servicoRepository

    @GetMapping
    Iterable<Servico> listaServicos(){
        servicoRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listaServico(@PathVariable("id") Long id){
        Optional<Servico> servicoBD = servicoRepository.findById(id)

        if (servicoBD) {
            ResponseEntity.ok(servicoBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity<Servico> inserirServico(@RequestBody Servico servico) {
        servico.ativo = true
        servicoRepository.save(servico)
        ResponseEntity.status(HttpStatus.CREATED).header("Location", "/" + servico.id).body()
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletarServico(@PathVariable("id") Long id){
        Servico servicoBD = servicoRepository.findOneById(id)

        if (servicoBD) {
            servicoBD.ativo = false
            servicoRepository.save(servicoBD)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
