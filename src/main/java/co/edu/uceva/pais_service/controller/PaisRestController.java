package co.edu.uceva.pais_service.controller;

import co.edu.uceva.pais_service.model.entities.Pais;
import co.edu.uceva.pais_service.model.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pais-service")
public class PaisRestController {

    @Autowired
    IPaisService paisService;

    /**
     * Endpoint para recibir un saludo
     * @param nombre Es el nombre que envian desde la url
     * @return Un saludo
     */
    @GetMapping("/hola/{nombre}")
    public String holaMundo(@PathVariable("nombre") String nombre){
        return "Hola "+ nombre;
    }

    /**
     * Metodo que lista los paises
     * @return Lista de paises
     */
    @GetMapping("/paises")
    public List<Pais> listar(){
        return paisService.findAll();
    }

    @GetMapping("/paises/{id}")
    public Pais buscarPais(@PathVariable("id") Long id){
        return paisService.findById(id);
    }

    @PostMapping("/paises")
    public Pais crearPais(@RequestBody Pais pais){
        return paisService.save(pais);
    }

    @DeleteMapping("/paises/{id}")
    public void borrarPais(@PathVariable("id") Long id){
        Pais pais;
        pais = paisService.findById(id);
        paisService.delete(pais);
    }

    @PutMapping("/paises")
    public Pais actualizarPais(@RequestBody Pais pais){
        return paisService.update(pais);
    }

}
