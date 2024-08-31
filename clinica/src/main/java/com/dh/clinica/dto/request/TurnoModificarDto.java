package com.dh.clinica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoModificarDto {
    private Integer id;
    private Integer paciente_id;
    private Integer odontologo_id;
    private String fecha;
}