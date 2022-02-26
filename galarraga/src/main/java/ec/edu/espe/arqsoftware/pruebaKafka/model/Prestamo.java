/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.pruebaKafka.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {

    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    
    @Column(name = "valor_pago", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorPago;
    
    @Column(name = "fecha_pago", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    
    @Column(name = "hora_pago", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaPago;
    
    @Column(name = "numero_cuota", nullable = false)
    private Integer numeroCuota;
}
