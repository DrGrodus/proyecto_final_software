package com.example.demos;

import java.io.File;
import java.util.*;

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

    public void ContarYRelacionar() {
        long inicioAct;
        long finAct;
        long tiempoAct;
        inicioAct = System.currentTimeMillis();

        String Path = new File("").getAbsolutePath().concat("\\Actividad_3");
        final File folderBuscar = new File(Path);

        try {
            File archivo = new File("Actividad_3");
            if (archivo.exists()) {
                inicioAct = System.currentTimeMillis();
                List<File> fileList = LectorDirectorios(folderBuscar);
                CrearArchivos(fileList);
                finAct = System.currentTimeMillis();
                tiempoAct = finAct - inicioAct;
                setTiempoAct(tiempoAct);
            } else {
                System.out.println("Oh!");
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }


        finAct = System.currentTimeMillis();
        tiempoAct = finAct - inicioAct;
    }

    public void CrearArchivos (List<File> fileList) {
        try {
            String diccionario = "Actividad_7/Diccionario";
            String posting = "Actividad_7/posting";

            for(File name : fileList) {
                String fileName = name.getName();
                fileName = fileName.replaceAll("onlyWords_", "");
                File dcc = new File(diccionario);
                File ping = new File(posting);
                if(dcc.getParentFile().mkdir() || dcc.getParentFile().exists()) {
                    if(dcc.createNewFile()) {
                        Contabilizar();
                    } else if (ping.createNewFile()) {
                        Contabilizar();
                    } else {
                        Contabilizar();
                    }
                }
            }

        } catch (Exception e) {

        }
    }

    public void Contabilizar () {
        try {


        } catch (Exception e) {

        }
    }

    public void Relacionar () {

    }

    public List<File> LectorDirectorios(final File folder) {
        List<File> fileList = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                LectorDirectorios(fileEntry);
            } else {
                fileList.add(new File(String.valueOf(fileEntry)));
            }
        }
        return fileList;
    }
}
