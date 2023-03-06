package com.example.demos;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Actividad_5 {

    private long tiempoAct;

    private TreeMap<File, Long> registros;

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

    public List<String> getListado() {
        return listado;
    }

    public void setListado(List<String> listado) {
        this.listado = listado;
    }

    public void LeerListaDePalabras() {
        TreeMap<String, Integer> contadorPalabrasUnicas = new TreeMap<>();
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        String Path = new File("").getAbsolutePath().concat("\\OnlyWords");
        final File folderBuscar = new File(Path);
        try {
            File archivo = new File("OnlyWords");
            if(archivo.exists()) {
                inicioAct = System.currentTimeMillis();
                List<File> listaFile = LectorDirectorios(folderBuscar);
                RouteToCNF(listaFile, registro);
                finAct = System.currentTimeMillis();
                setTiempoAct(finAct - inicioAct);
            } else {
                System.out.println("Oh!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TreeMap<File, Long> mapa = new TreeMap<>(registro);
        setRegistros(mapa);

    }

    private void RouteToCNF(List<File> fileList, Map<File, Long> registro) {
        File nameFileN;
        for (File fileIndex : fileList) {
            long inicio;
            long fin;
            long tiempo;
            inicio = System.currentTimeMillis();
            nameFileN = CreateNewFiles(fileIndex);
            fin = System.currentTimeMillis();
            tiempo = fin - inicio;
            registro.put(nameFileN, tiempo);
        }
    }

    public File CreateNewFiles(File name) {
        try {
            String dirAndFile = "UniqueWords/uniqueWords";

            String fileName = name.getName();
            fileName = fileName.replaceAll("onlyWords", "");
            File archivo = new File(dirAndFile + fileName);
            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    ContadorDePalabrasUnicas(name, archivo);
                } else {
                    ContadorDePalabrasUnicas(name, archivo);
                }
            }
            return archivo;

        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();
        }
        return null;
    }

    public void ContadorDePalabrasUnicas (File name, File archivo){

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
