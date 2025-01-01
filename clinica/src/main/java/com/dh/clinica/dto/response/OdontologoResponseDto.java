package com.dh.clinica.dto.response;

import com.dh.clinica.entity.Especialidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoResponseDto {
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String especialidad;
}
