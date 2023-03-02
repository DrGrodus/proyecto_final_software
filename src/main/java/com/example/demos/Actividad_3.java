package com.example.demos;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Actividad_3 {

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

    public void ListadoDeArchivos() {
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        long tiempoAct = 0;
        String Path = new File("").getAbsolutePath().concat("\\RemovedHTML");
        final File folderBuscar = new File(Path);

        try {
            File archivo = new File("RemovedHTML");
            if (archivo.exists()) {
                List<File> fileList = LectorDirectorios(folderBuscar);
                //System.out.println("A");
                RouteToCNF(fileList, registro);


            } else {
                System.out.println("Oh!");

            }
        } catch (Exception e) {
            //System.out.println("Error");
            e.printStackTrace();
        }
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
            String dirAndFile = "OnlyWords/onlyWords";

            String fileName = name.getName();
            fileName = fileName.replaceAll("remove_HTML", "");
            File archivo = new File(dirAndFile + fileName);
            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    Cleaner(name, archivo);
                } else {
                    Cleaner(name, archivo);
                }
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
        String onlyWords = "[-A-z/.]{2,}+";
        List listaDePalabras = new ArrayList();

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
            Pattern pat = Pattern.compile(onlyWords);



            if(texto.matches(onlyWords)) {
                System.out.println("A");
                texto = texto.replaceAll("\\s", "\n");
            }
            //escritor.write(texto);
            escritor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* </Recurso adaptado> */
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
