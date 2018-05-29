package br.com.unimednatal.sisfrota.controller

import br.com.unimednatal.sisfrota.entity.Frota
import br.com.unimednatal.sisfrota.repository.FrotaRepository
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
@RequestMapping("/frota")
class FrotaController {
    @Autowired
    FrotaRepository frotaRepository

    @GetMapping
    Iterable<Frota> listarFrotas(){
        frotaRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listarFrota(@PathVariable("id") Long id){
        Optional<Frota> frotaBD = frotaRepository.findById(id)

        if (frotaBD) {
            ResponseEntity.ok(frotaBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity<Frota> inserirFrota(@RequestBody Frota frota){
        Frota frotaBD = frotaRepository.findByPrefixoAndAtivoAndSetor(frota.prefixo, true, frota.setor)

        if (frotaBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            frota.ativo = true
            frotaRepository.save(frota)
            ResponseEntity.status(HttpStatus.CREATED).header("Location","/" + frota.id).body()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletarFrota(@PathVariable("id") Long id){
        Frota frotaBD = frotaRepository.findOneById(id)

        if (frotaBD) {
            frotaBD.ativo = false
            frotaRepository.save(frotaBD)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }


}
