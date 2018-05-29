package br.com.unimednatal.sisfrota.controller

import br.com.unimednatal.sisfrota.entity.Modelo
import br.com.unimednatal.sisfrota.repository.ModeloRepository
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
@RequestMapping("/modelo")
class ModeloController {
    @Autowired
    ModeloRepository modeloRepository

    @GetMapping
    Iterable<Modelo> listarModelos(){
        modeloRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listaModelo(@PathVariable("id") Long id){
        Optional<Modelo> modeloBD = modeloRepository.findById(id)

        if (modeloBD){
            ResponseEntity.ok(modeloBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity<Modelo> inserirModelo(@RequestBody Modelo modelo){
        Modelo modeloBD = modeloRepository.findOneByNome(modelo.nome)

        if (modeloBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            modelo.ativo = true
            modeloRepository.save(modelo)
            ResponseEntity.status(HttpStatus.CREATED).header("Location", "/" + modelo.id).body()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletarModelo(@PathVariable("id") Long id){
        Modelo modeloBD = modeloRepository.findOneById(id)

        if (modeloBD) {
            modeloBD.ativo = false
            modeloRepository.save(modeloBD)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
