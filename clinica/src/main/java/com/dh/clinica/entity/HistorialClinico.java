package com.dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "historiales_clinicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    @JsonBackReference(value = "paciente-historial")
    private Paciente paciente;

    @Column(columnDefinition = "TEXT")
    private String antecedentesPersonales;

    @Column(columnDefinition = "TEXT")
    private String antecedentesFamiliares;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    @Column(columnDefinition = "TEXT")
    private String medicacionActual;

    @OneToMany(mappedBy = "historialClinico", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "historial-consulta")
    private Set<Consulta> consultas;
}