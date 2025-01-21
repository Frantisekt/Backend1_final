package com.dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;       

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    @JsonBackReference(value = "paciente-consulta")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_odontologo")
    private Odontologo odontologo;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private LocalTime hora;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamiento;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoConsulta estado;

    @DecimalMin(value = "0.0")
    private BigDecimal costo;

    @DecimalMin(value = "0.0")
    private BigDecimal montoPagado;
    
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private Set<ProcedimientoOdontologico> procedimientos;

    @ManyToOne
    @JoinColumn(name = "historial_clinico_id")
    @JsonBackReference(value = "historial-consulta")
    private HistorialClinico historialClinico;
}
