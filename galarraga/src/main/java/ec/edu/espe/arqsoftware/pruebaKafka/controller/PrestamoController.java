/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.pruebaKafka.controller;

import ec.edu.espe.arqsoftware.pruebaKafka.service.PrestamoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @GetMapping("/prestamo/{numeroPagos}")
    public ResponseEntity generarPago(@PathVariable("numeroPagos") Integer elementos) {
        try {
            this.service.generatePayment(elementos);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Se produjo un error {} ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
