package com.dh.clinica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "procedimientos_odontologicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimientoOdontologico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @NotBlank
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank
    private String piezaDental;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal costo;
}
