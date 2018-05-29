package br.com.unimednatal.sisfrota.controller

import br.com.unimednatal.sisfrota.entity.Motorista
import br.com.unimednatal.sisfrota.repository.MotoristaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/motorista")
class MotoristaController {
    @Autowired
    MotoristaRepository motoristaRepository

    @GetMapping
    Iterable<Motorista> listarMotoristas(){
        motoristaRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listaMotorista(@PathVariable("id") Long id){
        Optional<Motorista> motoristaBD = motoristaRepository.findById(id)

        if (motoristaBD){
            ResponseEntity.ok(motoristaBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity inserirMotorista(@RequestBody Motorista motorista){
        Motorista motoristaBD = motoristaRepository.findOneByCpf(motorista.cpf)

        if (motoristaBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            motorista.ativo = true
            motoristaRepository.save(motorista)
            ResponseEntity.status(HttpStatus.CREATED).header("Location","/" + motorista.id).body()
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity ativarMotorista(@PathVariable("id") Long id){
        Motorista motoristaBD = motoristaRepository.findOneById(id)

        if (motoristaBD){
            if (motoristaBD.ativo == false){
                motoristaBD.ativo = true
                motoristaRepository.save(motoristaBD)
                ResponseEntity.ok().build()
            } else {
                ResponseEntity.status(HttpStatus.FORBIDDEN).body()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletarMotorista(@PathVariable("id") Long id){
        Motorista motoristaBD = motoristaRepository.findOneById(id)

        if (motoristaBD){
            motoristaBD.ativo = false
            motoristaRepository.save(motoristaBD)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
