package br.com.unimednatal.sisfrota.controller

import br.com.unimednatal.sisfrota.entity.Marca
import br.com.unimednatal.sisfrota.repository.MarcaRepository
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
@RequestMapping("/marca")
class MarcaController {
    @Autowired
    MarcaRepository marcaRepository

    @GetMapping
    Iterable<Marca> listarMarcas(){
        marcaRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listaMarca(@PathVariable("id") Long id){
        Optional<Marca> marcaBD = marcaRepository.findById(id)

        if (marcaBD) {
            ResponseEntity.ok(marcaBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity inserirMarca(@RequestBody Marca marca){
        Marca marcaBD = marcaRepository.findOneByNome(marca.nome)

        if (marcaBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            marca.ativo = true
            marcaRepository.save(marca)
            ResponseEntity.status(HttpStatus.CREATED).header("Location", "/" + marca.id).body()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletarMarca(@PathVariable("id") Long id){
        Marca marcaBD = marcaRepository.findOneById(id)

        if (marcaBD) {
            marcaBD.ativo = false
            marcaRepository.save(marcaBD)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
