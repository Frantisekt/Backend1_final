package com.dh.clinica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDto {
    private Integer id;
    private PacienteResponseDto pacienteResponseDto;
    private OdontologoResponseDto odontologoResponseDto;
    private String fecha;
}
