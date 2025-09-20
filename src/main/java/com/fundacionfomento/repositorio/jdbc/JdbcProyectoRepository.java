package com.fundacionfomento.repositorio.jdbc;

import com.fundacionfomento.configuracion.DataBaseConfig;
import com.fundacionfomento.repositorio.interfaces.ProyectoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcProyectoRepository implements ProyectoRepository {
    private final DataBaseConfig db;
    public JdbcProyectoRepository(DataBaseConfig db){ this.db = db; }

    @Override
    public boolean existsById(int id) throws Exception {
        String sql = "SELECT COUNT(*) FROM proyectos WHERE id=?";
        try (Connection con = db.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return rs.getInt(1) > 0; }
        }
    }
}