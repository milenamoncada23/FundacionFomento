package com.fundacionfomento.modelo.personas;

import com.fundacionfomento.modelo.base.Persona;
import com.fundacionfomento.modelo.enums.TipoPersona;

public class Beneficiario extends Persona {
    public Beneficiario(Integer id){ super(id); }
    @Override public TipoPersona tipo() { return TipoPersona.BENEFICIARIO; }
}