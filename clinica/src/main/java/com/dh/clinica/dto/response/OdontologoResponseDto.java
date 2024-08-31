package com.dh.clinica.dto.response;

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
}
