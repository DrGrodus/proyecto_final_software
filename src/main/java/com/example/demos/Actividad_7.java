package com.example.demos;

import java.io.*;
import java.util.*;

public class Actividad_7 {
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
            String posting = "Actividad_7/Posting.txt";

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

        TreeMap<String, Integer> repeticionesGlobales = new TreeMap<>();
        List<TreeMap<String, Integer>> repeticionesPorArchivo = new ArrayList<>();

        TreeMap<String, List<String>> relacionPalabraArchivo = new TreeMap<>();

        List<String> fileNames = new ArrayList<>();
        for (List<String> aux : todasLasPalabras) {
            int i = 0;
            TreeMap<String, Integer> repeticionesPA = new TreeMap<>();
            for (String elem : aux) {
                if (i == 1) {
                    if (repeticionesPA.containsKey(elem)) {
                        repeticionesPA.put(elem, repeticionesPA.get(elem) + 1);
                    } else {
                        repeticionesPA.put(elem, 1);
                    }
                } else {
                    fileNames.add(aux.get(0));
                }
                i = 1;

            }
            repeticionesPorArchivo.add(repeticionesPA);
        }
        int j = 0;

        for (TreeMap<String, Integer> repeticionesPoA : repeticionesPorArchivo) {
            for (Map.Entry<String, Integer> entrada : repeticionesPoA.entrySet()) {
                String palabra = entrada.getKey();
                List<String> aux = new ArrayList<>();

                if (repeticionesGlobales.containsKey(palabra)) {
                    repeticionesGlobales.put(palabra, repeticionesGlobales.get(palabra) + 1);

                    if (!relacionPalabraArchivo.get(palabra).contains(fileNames.get(j))) {
                        aux = relacionPalabraArchivo.get(palabra);
                        aux.add(fileNames.get(j));
                        relacionPalabraArchivo.put(palabra, aux);
                    }

                } else {
                    repeticionesGlobales.put(palabra, 1);
                    aux.add(fileNames.get(j));
                    relacionPalabraArchivo.put(palabra, aux);
                }
            }
            j++;
        }
        EscribirArchivos(relacionPalabraArchivo, repeticionesPorArchivo);

    }

    public void EscribirArchivos(TreeMap<String, List<String>> relacionPalabraArchivo, List<TreeMap<String, Integer>> repeticionesPorArchivo) {
        try {
            FileWriter escritorDCC = new FileWriter(getDiccionario());
            FileWriter escritorPst = new FileWriter(getPosting());

            // Archivo Posting
            for (TreeMap<String, Integer> repeticionesPoA : repeticionesPorArchivo) {
                for (Map.Entry<String, Integer> entradaPoA : repeticionesPoA.entrySet()) {
                    String palabraPoA = entradaPoA.getKey();
                    Integer coincidencias = entradaPoA.getValue();
                    List<String> tmp = relacionPalabraArchivo.get(palabraPoA);
                    for (String elem : tmp) {
                        escritorPst.write(elem + "; " + coincidencias + "\n");
                    }
                }
            }
            escritorPst.close();

            // Archivo Diccionario
            for (Map.Entry<String, List<String>> entradaRPA : relacionPalabraArchivo.entrySet()) {
                String palabraRPA = entradaRPA.getKey();
                List<String> archivos = entradaRPA.getValue();
                //escritorDCC.write("[Nombre de la palabra]" + "[Numero de archivos]" + "[Índice inicial, en el archivo posting, de la palabra]");
            }

        } catch (IOException e) {
            System.out.println("Error IO");
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
