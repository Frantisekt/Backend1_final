package com.dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El apellido debe contener solo letras.")
    private String apellido;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre debe contener solo letras.")
    private String nombre;
    @NotBlank
    @Size(min=7, max=15)
    private String dni;
    @NotNull
    @Email(message = "Email debe ser un email valido.")
    private String email;
    @NotNull
    @Pattern(regexp = "^[0-9]+$", message = "El telefono debe contener solo números.")
    private String telefono;
    @NotNull
    private LocalDate fechaIngreso;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "paciente-turno")
    //@JsonIgnore
    private Set<Turno> turnoSet;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "paciente-historial")
    private HistorialClinico historialClinico;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "paciente-consulta")
    private Set<Consulta> consultas;

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", email'" + email + '\'' +
                ", telefono'" + telefono + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                '}';
    }
}
