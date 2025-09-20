package com.fundacionfomento.utils;

import com.fundacionfomento.modelo.donacion.Donacion;

import java.io.BufferedWriter;
import java.nio.file.*;
import java.util.List;

public final class CsvWriter {
    public static String donaciones(List<Donacion> list, String nombreArchivo) throws Exception {
        Path path = Paths.get(nombreArchivo);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write("id;personaId;proyectoId;monto;metodo;fecha;comentario\n");
            for (var d : list) {
                bufferedWriter.write(String.format("%d;%d;%s;%s;%s;%s;%s%n",
                        d.id(), d.personaId(),
                        d.proyectoId()==null?"":d.proyectoId().toString(),
                        d.monto().toPlainString(), d.metodo().name(), d.fecha(), d.comentario()));
            }
        }
        return path.toAbsolutePath().toString();
    }
}
