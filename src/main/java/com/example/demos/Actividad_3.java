package com.example.demos;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Actividad_3 {

    private long tiempoAct;

    private TreeMap<File, Long> registros;

    private List<String> collecionDePalabras;
    private final List recolector = new ArrayList<>();

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

    public List getCollecionDePalabras() {
        return collecionDePalabras;
    }

    public void setCollecionDePalabras(List collecionDePalabras) {
        this.collecionDePalabras = collecionDePalabras;
    }

    public void ListadoDeArchivos() {
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        long tiempoAct = 0;
        String Path = new File("").getAbsolutePath().concat("\\Actividad_2");
        final File folderBuscar = new File(Path);

        try {
            File archivo = new File("Actividad_2");
            if (archivo.exists()) {
                inicioAct = System.currentTimeMillis();
                List<File> fileList = LectorDirectorios(folderBuscar);
                RouteToCNF(fileList, registro);
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
        TreeMap<File, Long> mapa = new TreeMap<>(registro);
        setRegistros(mapa);
        setCollecionDePalabras(recolector);
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
            String dirAndFile = "Actividad_3/onlyWords";

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
        String onlyWords = "([-A-z/.]{2,}+)";

        try {
            File file = new File(name.toURI());
            // Declarando el objeto de la clase StringBuilder
            StringBuilder builder = new StringBuilder();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String str;
            List<String> parrafo = new ArrayList<>();
            FileWriter escritor = new FileWriter(archivo);

            // Checa la condicional por el método buffer.readLine()
            // mientras sea verdadero el ciclo while correra
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }

            String texto = builder.toString();

            // se asigna el regex al pattern para ser usado por la clase Matcher,
            // la cual hará el trabajo de encontrar coincidencias
            Pattern pat = Pattern.compile(onlyWords);
            Matcher m = pat.matcher(texto);
            while (m.find()) {
                String down = m.group(1).substring(0, 1).toLowerCase() + m.group(1).substring(1);
                parrafo.add(down);
            }
            Pattern pat1 = Pattern.compile("--");
            Matcher m1;
            Collections.sort(parrafo);
            List<String> palabrasPorArchivo = new ArrayList<>();
            for (String elem : parrafo) {
                m1 = pat1.matcher(elem);
                if (!m1.find()) {
                    escritor.write(elem + System.lineSeparator());
                    palabrasPorArchivo.add(elem);
                }
            }
            recolector.add(palabrasPorArchivo);
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
