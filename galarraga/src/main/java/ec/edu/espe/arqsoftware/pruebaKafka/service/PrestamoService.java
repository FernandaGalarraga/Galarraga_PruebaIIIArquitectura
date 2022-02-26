/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.pruebaKafka.service;

import com.github.javafaker.Faker;
import ec.edu.espe.arqsoftware.pruebaKafka.dao.PrestamoRepository;
import ec.edu.espe.arqsoftware.pruebaKafka.kafka.KafkaProducer;
import ec.edu.espe.arqsoftware.pruebaKafka.model.Prestamo;
import java.math.BigDecimal;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepo;
    private final Faker faker = new Faker();
    private final KafkaProducer<Prestamo> kafkaTestProducer;

    public PrestamoService(PrestamoRepository prestamoRepo, KafkaProducer<Prestamo> kafkaTestProducer) {
        this.prestamoRepo = prestamoRepo;
        this.kafkaTestProducer = kafkaTestProducer;
    }

    public Prestamo savePagoPrestamo(Prestamo p) {
        return this.prestamoRepo.save(p);
    }

    public void generatePayment(Integer numElementos) {
        for (Integer i = 0; i < numElementos; i++) {
            Prestamo pago = new Prestamo();
            pago.setCodigo(Integer.valueOf(generateId()));
            pago.setFechaPago(faker.date().between(new Date(), new Date(2021,12,10)));
            pago.setHoraPago(new Date());
            pago.setNumeroCuota(faker.number().randomDigitNotZero());
            pago.setValorPago(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 2000)));
            log.info("datos: " + pago);
            this.kafkaTestProducer.runner(pago);
        }
    }

    public String generateId() {
        StringBuilder pin = new StringBuilder();
        while (pin.length() < 7) {
            Integer digito = (int) (Math.random() * 10);
            pin.append(digito);
        }
        return pin.toString();
    }
}
