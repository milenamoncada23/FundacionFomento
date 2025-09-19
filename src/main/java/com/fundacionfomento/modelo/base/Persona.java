package com.fundacionfomento.modelo.base;


import com.fundacionfomento.modelo.enums.TipoPersona;

public abstract class Persona {
    protected Integer id;
    protected String nombres, apellidos, documento, telefono, email;
    protected Direccion direccion; // composición

    protected Persona(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public abstract TipoPersona tipo();
}