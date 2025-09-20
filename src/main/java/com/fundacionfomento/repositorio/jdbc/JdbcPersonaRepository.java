package com.fundacionfomento.repositorio.jdbc;

import com.fundacionfomento.configuracion.DataBaseConfig;
import com.fundacionfomento.modelo.enums.TipoPersona;
import com.fundacionfomento.repositorio.interfaces.PersonaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcPersonaRepository implements PersonaRepository {

    private final DataBaseConfig db;
    public JdbcPersonaRepository(DataBaseConfig db){ this.db = db; }

    @Override
    public boolean existsByIdAndTipo(int id, TipoPersona tipo) throws Exception {
        String sql = "SELECT COUNT(*) FROM personas WHERE id=? AND tipo=?";
        try (Connection con = db.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, tipo.name());
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return rs.getInt(1) > 0; }
        }
    }
}