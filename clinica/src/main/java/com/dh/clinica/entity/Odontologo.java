package com.dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min=5, max=15)
    private String matricula;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El valor debe contener solo letras y espacios")
    private String nombre;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El valor debe contener solo letras y espacios")
    private String apellido;
    @NotBlank
    private String email;
    @NotNull
    @Pattern(regexp = "^[0-9]+$", message = "El campo debe contener solo números.")
    private String telefono;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especialidad especialidad;


    @OneToMany(mappedBy = "odontologo")
    //@JsonIgnore
    @JsonManagedReference(value = "odontologo-turno")
    private Set<Turno> turnoSet;

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono'" + telefono + '\'' +
                ", especialidad" + especialidad + '\'' +
                '}';
    }
}
