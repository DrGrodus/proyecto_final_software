package com.example.demos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Actividad_12 {
    static final Actividad_11 act11 = new Actividad_11();
    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private TreeMap<String, List<String>> DiccionarioList;
    private File Posting;
    private List<TreeMap<String, Double>> PostingList;
    private File Documentos;
    private TreeMap<String, Integer> DocumentosList;
    private File Resultados;
    private String Palabra;

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

    public TreeMap<String, Integer> getDocumentosList() {
        return DocumentosList;
    }

    public void setDocumentosList(TreeMap<String, Integer> documentosList) {
        DocumentosList = documentosList;
    }

    public File getResultados() {
        return Resultados;
    }

    public void setResultados(File resultados) {
        Resultados = resultados;
    }

    public String getPalabra() {
        return Palabra;
    }

    public void setPalabra(String palabra) {
        Palabra = palabra;
    }

    public void BuscadorDePalabras(String buscarPalabra) {
        setPalabra(buscarPalabra);
        if (getDiccionarioList() == null) {
            act11.masRelacionConArchivos();

            setDiccionarioList(act11.getDiccionarioList());
            setPostingList(act11.getPostingList());
            setDocumentosList(act11.getDocumentosList());

            listarArchivos();
            CrearArchivos();
            EscribirArchivos();
            Buscar();
        } else {
            Buscar();
        }
    }

    public void listarArchivos() {
        String Path = new File("").getAbsolutePath().concat("\\Actividad_3");
        final File folderBuscar = new File(Path);
        List<File> fileList = LectorDirectorios(folderBuscar);
        List<String> palabrasDelArchivo = null;
        palabrasDelArchivo = new ArrayList<>();
        for (File name : fileList) {
            palabrasDelArchivo.add(name.getName().replaceAll("onlyWords_", ""));
        }
        TreeMap<String, Integer> listaDeArchivos = new TreeMap<>();
        int i = 1;
        for (String elem : palabrasDelArchivo) {
            listaDeArchivos.put(elem, i);
            i++;
        }
        setDocumentosList(listaDeArchivos);
    }

    public void CrearArchivos() {
        try {
            String diccionario = "Actividad_12/Diccionario.txt";
            String posting = "Actividad_12/Posting.txt";
            String documentos = "Actividad_12/Documentos.txt";
            String resultados = "Actividad_12/resultados.txt";

            File dcc = new File(diccionario);
            setDiccionario(dcc);
            File ping = new File(posting);
            setPosting(ping);
            File docs = new File(documentos);
            setDocumentos(docs);
            File res = new File(resultados);
            setResultados(res);
            if (dcc.getParentFile().mkdir() || dcc.getParentFile().exists()) {
                dcc.createNewFile();
            }
            if (ping.getParentFile().mkdir() || ping.getParentFile().exists()) {
                ping.createNewFile();
            }
            if (docs.getParentFile().mkdir() || docs.getParentFile().exists()) {
                docs.createNewFile();
            }
            if (res.getParentFile().mkdir() || res.getParentFile().exists()) {
                res.createNewFile();
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


            // Archivo Documentos
            for (Map.Entry<String, Integer> entradaDOC : getDocumentosList().entrySet()) {
                String nombre = entradaDOC.getKey();
                Integer id = entradaDOC.getValue();
                escritorDOC.write(id + "; " + nombre + "\n");
            }
            escritorDOC.close();

            int j = 1;
            List<Integer> indices = new ArrayList<>();
            indices.add(0);
            // Archivo Posting
            for (TreeMap<String, Double> posting : getPostingList()) {
                for (Map.Entry<String, Double> entradaPST : posting.entrySet()) {
                    String nombreDeArchivo = entradaPST.getKey();
                    Double peso = entradaPST.getValue();
                    Integer docID = getDocumentosList().get(nombreDeArchivo);
                    escritorPST.write(docID + ";    " + peso + "\n");
                }
                indices.add(posting.size() + indices.get(j - 1));
                j++;

            }
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
            e.printStackTrace();

        }
    }

    public void Buscar() {
        try {
            FileWriter escritorRES = new FileWriter(getResultados());
            if (getDiccionarioList().get(getPalabra()) != null) {
                List<String> coincidencias = getDiccionarioList().get(getPalabra());
                escritorRES.write("La palabra: " + getPalabra() + " tiene coincidencias en: " + coincidencias.size() + " archivos\n");
                for (String e : coincidencias) {
                    escritorRES.write(e+"\n");
                }
            } else {
                escritorRES.write("No se ha encontrado la palabra: " + getPalabra());
            }
            escritorRES.close();
            System.out.println("Consulta los resultados en el archivo 'resultados.txt' dentro de la carpeta Actividad_12");
        }catch (IOException e) {
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
