package com.fundacionfomento.modelo.base;

public class Direccion {

    private final String calle;
    private final String ciudad;
    private final String departamento;

    public Direccion(String calle, String ciudad, String departamento) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }
}
