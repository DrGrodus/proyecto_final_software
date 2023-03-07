package com.example.demos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Actividad_5 {

    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private TreeMap<String, Integer> palabrasUnicas;
    private List<String> listado;

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

    public TreeMap<String, Integer> getpalabrasUnicas() {
        return palabrasUnicas;
    }

    public void setpalabrasUnicas(TreeMap<String, Integer> cpalabrasUnicas) {
        palabrasUnicas = cpalabrasUnicas;
    }

    public List<String> getListado() {
        return listado;
    }

    public void setListado(List<String> listado) {
        this.listado = listado;
    }


    public void LeerListaDePalabras () {
        TreeMap<String, Integer> contadorPalabrasUnicas = new TreeMap<>();

        for(String elem : getListado()) {
            if(contadorPalabrasUnicas.containsKey(elem)) {
                contadorPalabrasUnicas.put(elem, contadorPalabrasUnicas.get(elem)+1);
            } else {
                contadorPalabrasUnicas.put(elem,1);
            }
        }
        setpalabrasUnicas(contadorPalabrasUnicas);
        CrearArchivo();
    }

    public void CrearArchivo() {
        Map<File, Long> registro = new HashMap<>();
        try {
            long inicioAct;
            long finAct;
            inicioAct = System.currentTimeMillis();
            File archivo = new File("UniqueWords/uniqueWords.txt");

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
            for(Map.Entry<String, Integer> entry : getpalabrasUnicas().entrySet()) {
                String palabra = entry.getKey();
                Integer cuenta = entry.getValue();

                escritor.write(palabra + "; " + cuenta + " veces repetidas.\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }
}
