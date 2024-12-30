package com.dh.clinica.entity;

public enum Especialidad {
    ODONTOLOGIA_GENERAL("Odontología General"),
    CIRUGIA_ORAL("Cirugía Oral"),
    ORTODONCIA("Ortodoncia"),
    PERIODONCIA("Periodoncia");

    private final String descripcion;

    Especialidad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
