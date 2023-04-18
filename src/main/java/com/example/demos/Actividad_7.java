package com.example.demos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Actividad_7 {

    private final List recolector = new ArrayList<>();
    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private File Posting;
    private List collecionDePalabras;
    private List<List<String>> palabrasPorArchivo;
    private List<File> fileNames;

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

    public List<File> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<File> fileNames) {
        this.fileNames = fileNames;
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
                setFileNames(fileList);
                CrearArchivos();
                RecolectarPalabras(fileList);
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

    public void CrearArchivos() {
        try {
            String diccionario = "Actividad_7/Diccionario.txt";
            String posting = "Actividad_7/posting.txt";

            File dcc = new File(diccionario);
            setDiccionario(dcc);
            File ping = new File(posting);
            setPosting(ping);
            dcc.createNewFile();
            ping.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RecolectarPalabras(List<File> fileList) {
        try {
            List<List<String>> todasLasPalabras = new ArrayList<>();
            for (File name : fileList) {
                File file = new File(name.toURI());
                // Declarando el objeto de la clase StringBuilder
                StringBuilder builder = new StringBuilder();
                BufferedReader buffer = new BufferedReader(new FileReader(file));
                String str;
                List<String> palabrasDelArchivo = new ArrayList<>();
                palabrasDelArchivo.add(name.getName().replaceAll("onlyWords_", ""));

                // Checa la condicional por el método buffer.readLine()
                // mientras sea verdadero el ciclo while correra
                while ((str = buffer.readLine()) != null) {
                    palabrasDelArchivo.add(str);
                }
                todasLasPalabras.add(palabrasDelArchivo);
            }
            Relacionar(todasLasPalabras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Relacionar(List<List<String>> todasLasPalabras) {

        TreeMap<String, Integer> diccionarioPT1 = new TreeMap<>();
        TreeMap<String, Integer> diccionarioPT2 = new TreeMap<>();
        TreeMap<String, Integer> posting = new TreeMap<>();

        TreeMap<String, Integer> repeticionesG = new TreeMap<>();
        TreeMap<String, Integer> repeticionesPA = new TreeMap<>();

        for (int i = 0; i < todasLasPalabras.size(); i++) {
            List<String> aux = todasLasPalabras.get(i);
            for (String elem : aux) {
                if (!Objects.equals(elem, "[(A-z\\d).html]+")) {
                    if (repeticionesG.containsKey(elem)) {
                        repeticionesG.put(elem, repeticionesG.get(elem) + 1);
                    } else {
                        repeticionesG.put(elem, 1);
                    }
                }
            }

            for (Map.Entry<String, Integer> entrada : repeticionesG.entrySet()) {
                String palabra = entrada.getKey();
                Integer contador = entrada.getValue();
                if (repeticionesPA.containsKey(palabra)) {
                    repeticionesPA.put(palabra, contador + 1);
                } else {
                    repeticionesPA.put(palabra, 1);
                }
            }
        }



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
