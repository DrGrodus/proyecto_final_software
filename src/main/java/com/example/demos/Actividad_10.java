package com.example.demos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Actividad_10 {
    static final Actividad_9 act9 = new Actividad_9();
    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private TreeMap<String, List<String>> DiccionarioList;
    private File Posting;
    private List<TreeMap<String, Integer>> PostingList;
    private List<TreeMap<String, Double>> NewPostingList;

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

    public TreeMap<String, List<String>> getDiccionarioList() {
        return DiccionarioList;
    }

    public void setDiccionarioList(TreeMap<String, List<String>> diccionarioList) {
        DiccionarioList = diccionarioList;
    }

    public File getPosting() {
        return Posting;
    }

    public void setPosting(File posting) {
        Posting = posting;
    }

    public List<TreeMap<String, Integer>> getPostingList() {
        return PostingList;
    }

    public void setPostingList(List<TreeMap<String, Integer>> postingList) {
        PostingList = postingList;
    }

    public List<TreeMap<String, Double>> getNewPostingList() {
        return NewPostingList;
    }
    /*
    tf: frecuencia de términos
    idf: frecuencia inversa de documentos o posting file

    tf.idf = (número de repeticiones de la palabra (o token) * 100)/ número total de las palabras únicas en el archivo posting
     */

    public void setNewPostingList(List<TreeMap<String, Double>> newPostingList) {
        NewPostingList = newPostingList;
    }

    public void ManejadorDePeso() {
        act9.Limpiador();
        setPostingList(act9.getPostingList());
        setDiccionarioList(act9.getDiccionarioList());
        CrearArchivos();

        TreeMap<String, Double> pesoDeLaPalabra = new TreeMap<>();
        for (Map.Entry<String, List<String>> entrada : getDiccionarioList().entrySet()) {
            String palabra = entrada.getKey();
            List<String> documentos = entrada.getValue();
            Double peso = (double) (documentos.size() * 100);
            peso /= getPostingList().size();
            peso = Math.floor(peso * 1e3) / 1e3; // redondeamos a tres decimales, para hacerlo más simple de leer
            pesoDeLaPalabra.put(palabra, peso);

        }

        EscribirArchivos(pesoDeLaPalabra);

    }

    public void CrearArchivos() {
        try {
            String diccionario = "Actividad_10/Diccionario.txt";
            String posting = "Actividad_10/Posting.txt";

            File dcc = new File(diccionario);
            setDiccionario(dcc);
            File ping = new File(posting);
            setPosting(ping);
            if (dcc.getParentFile().mkdir() || dcc.getParentFile().exists()) {
                dcc.createNewFile();
            }
            if (ping.getParentFile().mkdir() || ping.getParentFile().exists()) {
                ping.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void EscribirArchivos(TreeMap<String, Double> pesoDeLaPalabra) {
        try {
            FileWriter escritorDCC = new FileWriter(getDiccionario());
            FileWriter escritorPST = new FileWriter(getPosting());

            int j = 1;
            List<Integer> indices = new ArrayList<>();
            indices.add(0);


            // Archivo Posting
            List<TreeMap<String, Double>> listaPost = new ArrayList<>();
            for (TreeMap<String, Integer> repeticiones : getPostingList()) {
                for (Map.Entry<String, Integer> entrada : repeticiones.entrySet()) {
                    String palabra = entrada.getKey();
                    Integer coincidencias = entrada.getValue();
                    List<String> tmp = null;
                    TreeMap<String, Double> auxNPost = new TreeMap<>();
                    if (getDiccionarioList().get(palabra) != null) {
                        tmp = getDiccionarioList().get(palabra);
                        Double peso = pesoDeLaPalabra.get(palabra);
                        for (String elem : tmp) {
                            escritorPST.write(elem + "; " + peso + "\n");
                            auxNPost.put(elem, peso);
                        }
                        indices.add(tmp.size() + indices.get(j - 1));
                        j++;
                    }
                    if (!auxNPost.isEmpty()) {
                        listaPost.add(auxNPost);
                    }
                }
            }
            setNewPostingList(listaPost);
            escritorPST.close();


            // Archivo Diccionario
            int k = 0;
            for (Map.Entry<String, List<String>> entrada : getDiccionarioList().entrySet()) {
                String palabra = entrada.getKey();
                List<String> documentos = entrada.getValue();
                escritorDCC.write(palabra + "; " + documentos.size() + "; " + indices.get(k) + "\n");
                k++;
            }
            escritorDCC.close();


        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }

}
