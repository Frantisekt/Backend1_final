package com.dh.clinica.entity;

import jakarta.persistence.*;

import lombok.Getter;

@Getter
public enum EstadoConsulta {
    PROGRAMADA("Programada"),
    EN_PROCESO("En proceso"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String descripcion;

    EstadoConsulta(String descripcion) {
        this.descripcion = descripcion;
    }
}