package com.example.demos;

import java.io.File;
import java.util.TreeMap;

public class Actividad_7 {

    private long tiempoAct;
    private TreeMap<File, Long> registros;

    public long getTiempoAct() {
        return tiempoAct;
    }

    public void setTiempoAct(long tiempoAct) {
        this.tiempoAct = tiempoAct;
    }

    public TreeMap<File, Long> getRegistros() {
        return registros;
    }

    public void setRegistros(TreeMap<File, Long> registros) {
        this.registros = registros;
    }
}
