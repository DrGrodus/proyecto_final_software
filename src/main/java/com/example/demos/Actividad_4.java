package com.example.demos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Actividad_4 {

    private long tiempoAct;

    private TreeMap<File, Long> registros;

    private List<List<String>> todasLasPalabras;
    private List<String> palabrasOrdenadas;

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

    public List<List<String>> getTodasLasPalabras() {
        return todasLasPalabras;
    }

    public void setTodasLasPalabras(List<List<String>> todasLasPalabras) {
        this.todasLasPalabras = todasLasPalabras;
    }

    public List<String> getPalabrasOrdenadas() {
        return palabrasOrdenadas;
    }

    public void setPalabrasOrdenadas(List<String> palabrasOrdenadas) {
        this.palabrasOrdenadas = palabrasOrdenadas;
    }

    public void CrearArchivo() {
        Map<File, Long> registro = new HashMap<>();
        try {
            long inicioAct;
            long finAct;
            inicioAct = System.currentTimeMillis();
            File archivo = new File("Actividad_4/allWords.txt");

            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    EscribirArchivo(archivo);
                } else {
                    EscribirArchivo(archivo);
                }
            }
            finAct = System.currentTimeMillis();
            setTiempoAct(finAct - inicioAct);

            registro.put(archivo, getTiempoAct());
            TreeMap<File, Long> mapa = new TreeMap<>(registro);
            setRegistros(mapa);
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }

    public void EscribirArchivo(File archivo) {
        try {
            FileWriter escritor = new FileWriter(archivo);
            List<String> todasLP = new ArrayList<>();
            for (int i = 0; i < getTodasLasPalabras().size(); i++) {
                List<String> aux = (List<String>) getTodasLasPalabras().get(i);
                for (int j = 0; j < aux.size(); j++) {
                    todasLP.add(aux.get(j));
                }
            }
            Collections.sort(todasLP);
            setPalabrasOrdenadas(todasLP);
            for (String elem : todasLP) {
                escritor.write(elem + System.lineSeparator());
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }


}
