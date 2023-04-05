package com.example.demos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Actividad_6 {

    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private List<List<String>> palabrasPorArchivo;
    private TreeMap<String, Integer> repeticionesGlobales;
    private TreeMap<String, Integer> repeticionesPorArchivo;

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

    public List<List<String>> getPalabrasPorArchivo() {
        return palabrasPorArchivo;
    }

    public void setPalabrasPorArchivo(List<List<String>> palabrasPorArchivo) {
        this.palabrasPorArchivo = palabrasPorArchivo;
    }

    public TreeMap<String, Integer> getRepeticionesGlobales() {
        return repeticionesGlobales;
    }

    public void setRepeticionesGlobales(TreeMap<String, Integer> repeticionesGlobales) {
        this.repeticionesGlobales = repeticionesGlobales;
    }

    public TreeMap<String, Integer> getRepeticionesPorArchivo() {
        return repeticionesPorArchivo;
    }

    public void setRepeticionesPorArchivo(TreeMap<String, Integer> repeticionesPorArchivo) {
        this.repeticionesPorArchivo = repeticionesPorArchivo;
    }

    public void Contabilizar() {
        long inicioAct;
        long finAct;
        long tiempoAct;

        TreeMap<String, Integer> repeticionesG = new TreeMap<>();
        TreeMap<String, Integer> repeticionesPA = new TreeMap<>();
        inicioAct = System.currentTimeMillis();

        for (int i = 0; i < getPalabrasPorArchivo().size(); i++) { // leer archivo por archivo
            TreeMap<String, Integer> auxiliar = new TreeMap<>();
            List<String> aux = getPalabrasPorArchivo().get(i);
            for (String elem : aux) { // leer palabra por palabra
                if (repeticionesG.containsKey(elem)) {
                    repeticionesG.put(elem, repeticionesG.get(elem) + 1);
                } else {
                    repeticionesG.put(elem, 1);
                }
            }
            for(Map.Entry<String, Integer> entrada : repeticionesG.entrySet()){
                String palabra = entrada.getKey();
                Integer contador = entrada.getValue();
                if(repeticionesPA.containsKey(palabra)){
                    repeticionesPA.put(palabra, contador+1);
                } else {
                    repeticionesPA.put(palabra, 1);
                }
            }
        }
        setRepeticionesGlobales(repeticionesG);
        setRepeticionesPorArchivo(repeticionesPA);
        finAct = System.currentTimeMillis();
        tiempoAct = finAct - inicioAct;

        CrearArchivo(tiempoAct);
    }


    public void CrearArchivo(long tiempoAct) {
        Map<File, Long> registro = new HashMap<>();
        try {
            long inicioA;
            long finA;
            long tiempoA;
            inicioA = System.currentTimeMillis();
            File archivo = new File("Actividad_6/matchesAcrossFiles.txt");

            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    EscribirArchivo(archivo);
                } else {
                    EscribirArchivo(archivo);
                }
            }
            finA = System.currentTimeMillis();
            //setTiempoAct(finAct - inicioAct);
            tiempoA = finA - inicioA;
            tiempoA += tiempoAct;
            setTiempoAct(tiempoA);

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
            for(Map.Entry<String, Integer> entry : getRepeticionesGlobales().entrySet()) {
                String palabra = entry.getKey();
                Integer cuenta = entry.getValue();

                escritor.write(palabra + "; " + cuenta + " veces repetidas." + "; " + getRepeticionesPorArchivo().get(palabra) + " archivos coincidentes" + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }

}
