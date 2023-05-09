package com.example.demos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Actividad_11 {
    static final Actividad_10 act10 = new Actividad_10();
    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private TreeMap<String, List<String>> DiccionarioList;
    private File Posting;
    private List<TreeMap<String, Double>> PostingList;
    private File Documentos;
    private TreeMap<Integer, String> DocumentosList;

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

    public List<TreeMap<String, Double>> getPostingList() {
        return PostingList;
    }

    public void setPostingList(List<TreeMap<String, Double>> postingList) {
        PostingList = postingList;
    }

    public File getDocumentos() {
        return Documentos;
    }

    public void setDocumentos(File documentos) {
        Documentos = documentos;
    }

    public TreeMap<Integer, String> getDocumentosList() {
        return DocumentosList;
    }

    public void setDocumentosList(TreeMap<Integer, String> documentosList) {
        DocumentosList = documentosList;
    }

    public void masRelacionConArchivos() {
        act10.ManejadorDePeso();
        setPostingList(act10.getNewPostingList());
        setDiccionarioList(act10.getDiccionarioList());

        listarArchivos();
        CrearArchivos();
        EscribirArchivos();

    }

    public void listarArchivos () {
        String Path = new File("").getAbsolutePath().concat("\\Actividad_3");
        final File folderBuscar = new File(Path);
        List<File> fileList = LectorDirectorios(folderBuscar);
        List<String> palabrasDelArchivo = null;
        palabrasDelArchivo = new ArrayList<>();
        for (File name : fileList) {
            palabrasDelArchivo.add(name.getName().replaceAll("onlyWords_", ""));
        }
        TreeMap<Integer, String> listaDeArchivos = new TreeMap<>();
        int i = 1;
        for (String elem : palabrasDelArchivo) {
            listaDeArchivos.put(i, elem);
            i++;
        }
        setDocumentosList(listaDeArchivos);
    }

    public void CrearArchivos() {
        try {
            String diccionario = "Actividad_11/Diccionario.txt";
            String posting = "Actividad_11/Posting.txt";
            String documentos = "Actividad_11/Documentos.txt";

            File dcc = new File(diccionario);
            setDiccionario(dcc);
            File ping = new File(posting);
            setPosting(ping);
            File docs = new File(documentos);
            setDocumentos(docs);
            if (dcc.getParentFile().mkdir() || dcc.getParentFile().exists()) {
                dcc.createNewFile();
            }
            if (ping.getParentFile().mkdir() || ping.getParentFile().exists()) {
                ping.createNewFile();
            }
            if (docs.getParentFile().mkdir() || docs.getParentFile().exists()) {
                docs.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void EscribirArchivos() {
        try {
            FileWriter escritorDCC = new FileWriter(getDiccionario());
            FileWriter escritorPST = new FileWriter(getPosting());
            FileWriter escritorDOC = new FileWriter(getDocumentos());

            getDiccionarioList();
            getPostingList();
            getDocumentosList();

            int j = 1;
            List<Integer> indices = new ArrayList<>();
            indices.add(0);

            // Archivo Documentos
            for(Map.Entry<Integer, String> entradaDOC : getDocumentosList().entrySet()) {
                Integer id = entradaDOC.getKey();
                String nombre = entradaDOC.getValue();
                escritorDOC.write(id + "; " + nombre + "\n");
            }
            escritorDOC.close();

            // Archivo Posting
            for(TreeMap<String, Double> posting : getPostingList()) {
                for(Map.Entry<String, Double> entradaPST : posting.entrySet()) {
                    String nombreDeArchivo = entradaPST.getKey();
                    Double peso = entradaPST.getValue();
                    //tmp = getDiccionarioList().get(nombreDeArchivo);
                        //for(String elem : tmp) {
                            Integer docID = null;
                            for(Map.Entry<Integer, String> entradaDOC : getDocumentosList().entrySet()) {
                                if(entradaDOC.getValue().equals(nombreDeArchivo)) {
                                    docID = entradaDOC.getKey();
                                }
                            }
                            escritorPST.write(docID +"; " + peso + "\n");
                        //}
                        //indices.add(tmp.size() + indices.get(j-1));
                        j++;

                }

            }
            escritorPST.close();

            // Archivo Diccionario



        } catch (IOException e) {
            e.printStackTrace();
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
