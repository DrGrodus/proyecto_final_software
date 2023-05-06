package com.example.demos;

import java.io.*;
import java.util.*;

public class Actividad_7 {
    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private List<TreeMap<String, Integer>> DiccionarioList;
    private File Posting;
    private TreeMap<String, List<String>> PostingList;
    private File AuxPosting;

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

    public List<TreeMap<String, Integer>> getDiccionarioList() {
        return DiccionarioList;
    }

    public void setDiccionarioList(List<TreeMap<String, Integer>> diccionarioList) {
        DiccionarioList = diccionarioList;
    }

    public File getPosting() {
        return Posting;
    }

    public void setPosting(File posting) {
        Posting = posting;
    }

    public TreeMap<String, List<String>> getPostingList() {
        return PostingList;
    }

    public void setPostingList(TreeMap<String, List<String>> postingList) {
        PostingList = postingList;
    }

    public File getAuxPosting() {
        return AuxPosting;
    }

    public void setAuxPosting(File auxPosting) {
        AuxPosting = auxPosting;
    }

    public void RecolectarYRelacionar() {
        long inicioAct;
        long finAct;
        long tiempoAct;
        Map<File, Long> registro = new HashMap<>();
        inicioAct = System.currentTimeMillis();

        String Path = new File("").getAbsolutePath().concat("\\Actividad_3");
        final File folderBuscar = new File(Path);

        try {
            File archivo = new File("Actividad_3");
            if (archivo.exists()) {
                inicioAct = System.currentTimeMillis();
                List<File> fileList = LectorDirectorios(folderBuscar);
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
            String auxPos = "Actividad_7/Nota sobre 'Posting.txt'.txt";

            File dcc = new File(diccionario);
            setDiccionario(dcc);
            File ping = new File(posting);
            setPosting(ping);
            File auxPst = new File(auxPos);
            setAuxPosting(auxPst);
            if(dcc.getParentFile().mkdir() || dcc.getParentFile().exists()){
                dcc.createNewFile();
            }
            if(ping.getParentFile().mkdir() || ping.getParentFile().exists()){
                ping.createNewFile();
            }
            if(auxPst.getParentFile().mkdir() || auxPst.getParentFile().exists()){
                auxPst.createNewFile();
            }



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
                BufferedReader buffer = new BufferedReader(new FileReader(file));
                String str;
                List<String> palabrasDelArchivo = new ArrayList<>();
                palabrasDelArchivo.add(name.getName().replaceAll("onlyWords_", ""));

                // Checa la condicional por el método buffer.readLine()
                // mientras sea verdadero el ciclo while correrá
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
        setDiccionarioList(repeticionesPorArchivo);
        setPostingList(relacionPalabraArchivo);
        EscribirArchivos(relacionPalabraArchivo, repeticionesPorArchivo);

    }

    public void EscribirArchivos(TreeMap<String, List<String>> relacionPalabraArchivo, List<TreeMap<String, Integer>> repeticionesPorArchivo) {
        try {
            FileWriter escritorDCC = new FileWriter(getDiccionario());
            FileWriter escritorPst = new FileWriter(getPosting());
            FileWriter escritorApt = new FileWriter(getAuxPosting());

            int j = 1;
            List<Integer> indices = new ArrayList<>();
            indices.add(0);
            // Archivo Posting
            for (TreeMap<String, Integer> repeticionesPoA : repeticionesPorArchivo) {
                for (Map.Entry<String, Integer> entradaPoA : repeticionesPoA.entrySet()) {
                    String palabraPoA = entradaPoA.getKey();
                    Integer coincidencias = entradaPoA.getValue();
                    List<String> tmp = relacionPalabraArchivo.get(palabraPoA);
                    for (String elem : tmp) {
                        escritorPst.write(elem + "; " + coincidencias + "\n");
                    }
                    indices.add(tmp.size() + indices.get(j-1));
                    j++;
                }
            }
            escritorPst.close();

            // Archivo Diccionario
            int k = 0;
            for (Map.Entry<String, List<String>> entradaRPA : relacionPalabraArchivo.entrySet()) {
                String palabraRPA = entradaRPA.getKey();
                List<String> archivos = entradaRPA.getValue();
                escritorDCC.write(palabraRPA + "; " + archivos.size() + "; " + indices.get(k) + "\n");
                k++;
            }
            escritorDCC.close();

            escritorApt.write("El archivo que se genera es muy pesado para Github");
            escritorApt.close();

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
