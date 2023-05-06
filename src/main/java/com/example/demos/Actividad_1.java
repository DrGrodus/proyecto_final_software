package com.example.demos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Actividad_1 {

    private long tiempoAct;

    private TreeMap<File, Long> registros;

    public TreeMap<File, Long> getRegistros() {
        return registros;
    }

    public void setRegistros(TreeMap<File, Long> registros) {
        this.registros = registros;
    }

    public long getTiempoAct() {
        return tiempoAct;
    }

    public void setTiempoAct(long tiempoAct) {
        this.tiempoAct = tiempoAct;
    }

    public void LectorArchivos(List<File> fileList) {
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        try {
            inicioAct = System.currentTimeMillis();
            String dirAndFile = "CS13309_Archivos_HTML/Files/";
            for (File fileIndex : fileList) {
                long inicio;
                long fin;
                long tiempo;
                File objeto = new File(dirAndFile + fileIndex.getName());
                Scanner lector = new Scanner(objeto);
                inicio = System.currentTimeMillis();
                while (lector.hasNextLine()) {
                    String dato = lector.nextLine();
                }
                lector.close();
                fin = System.currentTimeMillis();
                tiempo = fin - inicio;
                registro.put(objeto, tiempo);
            }
            finAct = System.currentTimeMillis();
            setTiempoAct(finAct - inicioAct);
        } catch (FileNotFoundException e) {
            System.out.println("Error de archivo");
            e.printStackTrace();

        }

        // Para ordenar los valores
        TreeMap<File, Long> mapa = new TreeMap<>(registro);
        setRegistros(mapa);

        System.out.println("\n \n");
    }
}
