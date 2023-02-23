package com.example;

import com.example.demos.*;

import java.io.*;
import java.util.*;

public class ProyectoSoftware {
    static TreeMap<File, Long> mapa = new TreeMap<>();
    static int response = 1;
    static String folderNoHTML = "RemovedHTML";
    static Actividad_1 act1 = new Actividad_1();
    static Actividad_2 act2 = new Actividad_2();
    static Actividad_3 act3 = new Actividad_3();
    static Actividad_4 act4 = new Actividad_4();
    static Actividad_5 act5 = new Actividad_5();

    public static void main(String[] args) {

        System.out.println("Bienvenido!");
        String Path = new File("").getAbsolutePath().concat("\\CS13309_Archivos_HTML\\Files");

        final File folder = new File(Path);
        List<File> fileList = LectorDirectorios(folder);
        Scanner scan = new Scanner(System.in);
        String archivoToLog;


        while (response != 0) {
            System.out.println("Demostraciones \nIngresa el numero de la seleccion deseada:" +
                    "\n 1.- Actividad 1" +
                    "\n 2.- Actividad 2" +
                    "\n 3.- Actividad 3" +
                    "\n 4.- Actividad 4" +
                    "\n 5.- Actividad 5" +
                    "\n 0.- Salir" +
                    "");
                response = scan.nextInt();

            switch (response) {
                case 1:
                    long inicioEjecucion = System.currentTimeMillis();

                    act1.LectorArchivos(fileList);
                    archivoToLog = CreateLog();

                    long tiempoAct = act1.getTiempoAct();

                    long finalEjecucion = System.currentTimeMillis();
                    long tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    TreeMap<File, Long> registros = act1.getRegistros();

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, 0);
                    break;
                case 2:
                    tiempoEjecucion = 0; tiempoAct = 0;
                    inicioEjecucion = System.currentTimeMillis();
                    // tiempoAct = LectorArchivos(fileList);
                    act2.LectorArchivos(fileList);
                    archivoToLog = CreateLog();

                    tiempoAct = act2.getTiempoAct();

                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null; registros = act2.getRegistros();

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, 0);
                    break;
                case 3:
                    System.out.println("WIP2");
                    inicioEjecucion = System.currentTimeMillis();
                    long tiempoBuscar = 0;
                    long tiempoAdicional = 0;
                    String pathFolder = new File("").getAbsolutePath().concat("\\" + folderNoHTML);
                    File folderNOHTML = new File(pathFolder);
                    if (!folderNOHTML.exists()) {
                        tiempoAdicional = LectorArchivos(fileList);
                    }
                    tiempoBuscar = RecolectorPalabras();

                    archivoToLog = CreateLog();

                    // Metodo

                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    //PrintLog(tiempoEjecucion, tiempoBuscar, valorEjec, valorBuscar, archivoToLog);
                    break;
                case 4:
                    System.out.println("WIP3");

                    archivoToLog = CreateLog();
                    break;
                case 5:
                    System.out.println("WIP4");

                    break;

                case 0:
                    System.out.println("Gracias por usar el programa!");
                    break;
                default:
                    System.out.println("Esa opcion todavía no ha sido implementada");
                    break;
            }
        }
    }

    public static void PrintLog(long tiempoEjecucion, long tiempoAct, String archivoToLog, TreeMap<File, Long> registros, int tiempoAdicional) {
        try {
            if (!Objects.equals(archivoToLog, "")) {
                FileWriter escritor = new FileWriter(archivoToLog);

                for (File key : registros.keySet()) {
                    escritor.write(key + " Tiempo: " + registros.get(key) + " milisegundos\n\n");
                }

                double valorAct = tiempoAct;
                valorAct = valorAct / 1000;
                double valorEjec = tiempoEjecucion;
                valorEjec = valorEjec / 1000;

                switch (response) {
                    case 1:
                        escritor.write("\nTiempo total en abrir archivos: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;
                    case 2:
                        escritor.write("\nTiempo total en eliminar todas las etiquetas HTML: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");

                        break;
                    case 3:
                        System.out.println("WIP2");
                        if (tiempoAdicional > 0) {
                            System.out.println("Tiempo adicional: " + tiempoAdicional);
                        }
                        escritor.write("\nTiempo total en recolectar todas las palabras y ordenarlas alfabeticamente: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");

                        break;
                    case 4:
                        System.out.println("WIP3");

                        break;

                    case 5:
                        System.out.println("WIP4");

                        break;
                    default:
                        System.out.println("Oh!");
                        break;
                }

                escritor.write("\nTiempo total de ejecucion de la aplicación:  " + tiempoEjecucion + " milisegundos " + "ó " + valorEjec + " segundos");
                escritor.close();
            }
        } catch (IOException e) {
            System.out.println("Error de IO");
            e.printStackTrace();
        }
    }

    public static String CreateLog() {
        try {
            String placeholder = "aR_2802776.txt";
            String fileName = placeholder.replace('R', Character.forDigit(response, 10));
            File archivo = new File(fileName);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName() + "\n\n");
            } else {
                System.out.println("El archivo Log: " + archivo.getName() + " ya existe.\n\n");
            }
            return archivo.toString();
        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();
        }
        return "";
    }

    public static File CreateNewFiles(File name) {
        try {
            String dirAndFile = "";
            switch (response) {
                case 3:
                    dirAndFile = "StrippedText/strip_string";
                    break;
                case 4:
                    System.out.println("WIP4");

                    break;
                case 5:
                    System.out.println("WIP5");

                    break;
                default:
                    System.out.println("Mmm...");

                    break;

            }
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

    public static void Cleaner(File name, File archivo) {

        /* <Recurso adaptado> */
        // https://www.geeksforgeeks.org/java-program-to-read-a-file-to-string/
        // el bloque try es para checar las excepciones cuando
        // un objeto de la clase BufferedReader es creado
        // para leer la direccion del archivo
        String regexNOHTML = "<[^>]*>";
        String strip = "A";
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
            switch (response) {
                case 3:
                    texto = texto.replaceAll(strip, "");
                    escritor.write(texto);
                    escritor.close();
                    break;

                case 4:
                    System.out.println("WIP4");

                    break;

                case 5:
                    System.out.println("WIP5");

                    break;

                default:
                    System.out.println("No?");
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* </Recurso adaptado> */

    }

    public static long RecolectorPalabras() {
        long inicioAct;
        long finAct;
        long tiempoAct = 0;
        String Path = new File("").getAbsolutePath().concat("\\RemovedHTML");
        final File folderBuscar = new File(Path);
        try {
            File archivo = new File(folderNoHTML);
            if (archivo.exists()) {
                System.out.println("Ye");
                List<File> fileList = LectorDirectorios(folderBuscar);
                System.out.println("A");
                /* RouteToCNF(); */


            } else {
                System.out.println("Oh!");

            }
        } catch (Exception e) {
            System.out.println("Error");
        }

            /*
        } catch (IOException e){
            System.out.println("Error IO, parece ser que la carpeta no existe");
        }
        */

        return tiempoAct;

    }


    public static Long LectorArchivos(List<File> fileList) {
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        long tiempoAct = 0;
            switch (response) {
                case 3:
                    System.out.println("WIP2");
                    File nameFileN;
                    inicioAct = System.currentTimeMillis();
                    RouteToCNF(fileList, registro);
                    finAct = System.currentTimeMillis();
                    tiempoAct = finAct - inicioAct;
                    break;
                case 4:
                    System.out.println("WIP3");
                    break;
                default:
                    System.out.println("Oh!");
                    break;
            }

            // Para ordenar los valores
            mapa = new TreeMap<File, Long>(registro);

            System.out.println("\n \n");
        return tiempoAct;
    }

    private static void RouteToCNF(List<File> fileList, Map<File, Long> registro) {
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

    public static List<File> LectorDirectorios(final File folder) {
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
