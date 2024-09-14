package com.dh.clinica.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private String path;
    private String mensaje;
    private int statusCode;
    private ZonedDateTime zonedDateTime;
    private List<String> errores;
}
