package com.example.demos;

import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Actividad_2 {

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

    public void RecolectorInformacion() {

    }

    public void LectorArchivos(List<File> fileList) {
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        long tiempoA;
        File nameFile;
        inicioAct = System.currentTimeMillis();
        RouteToCNF(fileList, registro);
        finAct = System.currentTimeMillis();
        tiempoA = finAct - inicioAct;
        setTiempoAct(tiempoA);

        // Para ordenar los valores
        TreeMap<File, Long> mapa = new TreeMap<File, Long>(registro);
        setRegistros(mapa);
    }

    public void RouteToCNF(List<File> fileList, Map<File, Long> registro) {
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
            String dirAndFile = "RemovedHTML/remove_HTML";
            File archivo = new File(dirAndFile + name.getName());
            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    Cleaner(name, archivo);
                } else {
                    Cleaner(name, archivo);
                }
            } else {
                throw new IOException("Error al crear el directorio " + archivo.getParent());
            }
            return archivo;
        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();
        }
        return null;
    }


    public void Cleaner(File name, File archivo) {

        /* <Recurso adaptado> */
        // https://www.geeksforgeeks.org/java-program-to-read-a-file-to-string/
        // el bloque try es para checar las excepciones cuando
        // un objeto de la clase BufferedReader es creado
        // para leer la direccion del archivo
        String regexNOHTML = "<[^>]*>";
        String unescapeChars = "&#\\d{3}?";
        try {
            File file = new File(name.toURI());
            // Declarando el objeto de la clase StringBuilder
            StringBuilder builder = new StringBuilder();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String str;
            FileWriter escritor = new FileWriter(archivo);

            // Checa la condicional por el método buffer.readLine()
            // mientras sea verdadero el ciclo while correra
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }

            String texto = builder.toString();

            texto = texto.replaceAll(regexNOHTML, "");
            texto = texto.replaceAll(unescapeChars, "");
            texto = StringEscapeUtils.unescapeHtml4(texto);
            escritor.write(texto);
            escritor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        /* </Recurso adaptado> */
    }
}
