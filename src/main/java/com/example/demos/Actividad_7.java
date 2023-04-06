package com.example.demos;

import java.io.*;
import java.util.*;

public class Actividad_7 {

    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;

    private File Posting;
    private List collecionDePalabras;
    private final List recolector = new ArrayList<>();
    private List<List<String>> palabrasPorArchivo;

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

    public File getDiccionario() {
        return Diccionario;
    }

    public void setDiccionario(File diccionario) {
        Diccionario = diccionario;
    }

    public File getPosting() {
        return Posting;
    }

    public void setPosting(File posting) {
        Posting = posting;
    }

    public List getCollecionDePalabras() {
        return collecionDePalabras;
    }

    public void setCollecionDePalabras(List collecionDePalabras) {
        this.collecionDePalabras = collecionDePalabras;
    }

    public List<List<String>> getPalabrasPorArchivo() {
        return palabrasPorArchivo;
    }

    public void setPalabrasPorArchivo(List<List<String>> palabrasPorArchivo) {
        this.palabrasPorArchivo = palabrasPorArchivo;
    }

    public void RecolectarYRelacionar() {
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
            String diccionario = "Actividad_7/Diccionario.txt";
            String posting = "Actividad_7/posting.txt";

            for (File name : fileList) {
                String fileName = name.getName();
                fileName = fileName.replaceAll("onlyWords_", "");
                File dcc = new File(diccionario);
                setDiccionario(dcc);
                File ping = new File(posting);
                setPosting(ping);
                if (dcc.getParentFile().mkdir() || dcc.getParentFile().exists()) {
                    if (dcc.createNewFile()) {
                        RecolectarPalabras(name, fileName);
                    } else if (ping.createNewFile()) {
                        RecolectarPalabras(name, fileName);
                    } else {
                        RecolectarPalabras(name, fileName);
                    }
                }
            }
            Relacionar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RecolectarPalabras(File name, String fileName) {
        try {
            File file = new File(name.toURI());
            // Declarando el objeto de la clase StringBuilder
            StringBuilder builder = new StringBuilder();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String str;
            List<String> palabrasPorArchivo = new ArrayList<>();

            // Checa la condicional por el método buffer.readLine()
            // mientras sea verdadero el ciclo while correra
            while ((str = buffer.readLine()) != null) {
                palabrasPorArchivo.add(str);
            }
            recolector.add(palabrasPorArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Relacionar () {
        setCollecionDePalabras(recolector);
        setPalabrasPorArchivo(getCollecionDePalabras());

        TreeMap<String, Integer> repeticionesG = new TreeMap<>();
        for (int i = 0; i < getCollecionDePalabras().size(); i++) {
            List<String> aux = getPalabrasPorArchivo().get(i);
            for (String elem : aux) {
                if (repeticionesG.containsKey(elem)) {
                    repeticionesG.put(elem, repeticionesG.get(elem) + 1);
                } else {
                    repeticionesG.put(elem, 1);
                }
            }
        }
        System.out.println(repeticionesG);
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
