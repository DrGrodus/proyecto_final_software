package com.example.demos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Actividad_10 {
    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private List<TreeMap<String, Integer>> DiccionarioList;
    private File Posting;
    private TreeMap<String, List<String>> PostingList;

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
    /*
    tf: frecuencia de terminos
    idf: frecuencia inversa de documentos o posting file

    tf.idf = (número de repeticiones * 100)/ número total de tokens en el documento
     */

    static final Actividad_9 act9 = new Actividad_9();

    public void ManejadorDePeso() {
        act9.Limpiador();
        setPostingList(act9.getPostingList());
        setPosting(act9.getPosting());
        setDiccionarioList(act9.getDiccionarioList());
        setDiccionario(act9.getDiccionario());

        int a = 0;



    }

}
