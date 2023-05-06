package com.example;

import com.example.demos.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProyectoSoftware {
    static Integer response = 1;
    static final Actividad_1 act1 = new Actividad_1();
    static final Actividad_2 act2 = new Actividad_2();
    static final Actividad_3 act3 = new Actividad_3();
    static final Actividad_4 act4 = new Actividad_4();
    static final Actividad_5 act5 = new Actividad_5();
    static final Actividad_6 act6 = new Actividad_6();
    static final Actividad_7 act7 = new Actividad_7();
    static final Actividad_8 act8 = new Actividad_8();
    static final Actividad_9 act9 = new Actividad_9();
    static final Actividad_10 act10 = new Actividad_10();
    static final Actividad_11 act11 = new Actividad_11();

    public static void main(String[] args) {

        System.out.println("Bienvenido!");
        String Path = new File("").getAbsolutePath().concat("\\CS13309_Archivos_HTML\\Files");

        final File folder = new File(Path);
        List<File> fileList = LectorDirectorios(folder);
        Scanner scan = new Scanner(System.in);
        String archivoToLog;


        while (response != 0) {
            String dato = "";
            response = null;
            System.out.println("Demostraciones \nIngresa el numero de la selección deseada:" +
                    "\n 1.-  Actividad 1" +
                    "\n 2.-  Actividad 2" +
                    "\n 3.-  Actividad 3" +
                    "\n 4.-  Actividad 4" +
                    "\n 5.-  Actividad 5" +
                    "\n 6.-  Actividad 6" +
                    "\n 7.-  Actividad 7" +
                    "\n 8.-  Actividad 8" +
                    "\n 9.-  Actividad 9" +
                    "\n 10.- Actividad 10" +
                    "\n 11.- Actividad 11" +
                    "\n 12.- Actividad 12" +
                    "\n 14.- Actividad 14" +
                    "\n 15.- Actividad 15" +
                    "\n 0.-  Salir" +
                    "");
            dato = scan.nextLine();
            try {
                if (Integer.parseInt(dato) >= 0) {
                    response = Integer.parseInt(dato);
                }
            } catch (Exception e) {
                response = 99;
            }

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
                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    inicioEjecucion = System.currentTimeMillis();
                    act2.LectorArchivos(fileList);
                    archivoToLog = CreateLog();

                    tiempoAct = act2.getTiempoAct();

                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null;
                    registros = act2.getRegistros();

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, 0);
                    break;
                case 3:
                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    inicioEjecucion = System.currentTimeMillis();
                    long tiempoAdicional = 0;
                    String pathFolder = new File("").getAbsolutePath().concat("\\" + "Actividad_2");
                    File folderNOHTML = new File(pathFolder);
                    if (!folderNOHTML.exists()) {
                        act2.LectorArchivos(fileList);
                        tiempoAdicional = act2.getTiempoAct();
                    }
                    act3.ListadoDeArchivos();
                    tiempoAct = act3.getTiempoAct();

                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null;
                    registros = act3.getRegistros();

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, tiempoAdicional);
                    break;
                case 4:
                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */
                    if (act3.getCollecionDePalabras() == null) {
                        act3.ListadoDeArchivos();
                    }
                    tiempoAdicional += act3.getTiempoAct();

                    act4.setTodasLasPalabras(act3.getCollecionDePalabras());
                    act4.CrearArchivo();
                    tiempoAct = act4.getTiempoAct();
                    /* </Métodos> */

                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null;
                    registros = act4.getRegistros();

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, tiempoAdicional);
                    break;
                case 5:

                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */
                    if (act4.getPalabrasOrdenadas() == null) {
                        act3.ListadoDeArchivos();
                        act4.setTodasLasPalabras(act3.getCollecionDePalabras());
                        act4.CrearArchivo();
                    }
                    tiempoAdicional += act3.getTiempoAct();
                    tiempoAdicional += act4.getTiempoAct();

                    act5.setListado(act4.getPalabrasOrdenadas());
                    act5.LeerListaDePalabras();
                    tiempoAct = act5.getTiempoAct();
                    /* </Métodos> */

                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null;
                    registros = act5.getRegistros();


                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, tiempoAdicional);
                    break;

                case 6:
                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */
                    if (act3.getCollecionDePalabras() == null) {
                        act3.ListadoDeArchivos();
                    }
                    tiempoAdicional += act3.getTiempoAct();

                    act6.setPalabrasPorArchivo(act3.getCollecionDePalabras());
                    act6.Contabilizar();
                    tiempoAct = act6.getTiempoAct();
                    /* </Métodos> */

                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null;
                    registros = act6.getRegistros();

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, registros, tiempoAdicional);
                    break;

                case 7:

                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */

                    act7.RecolectarYRelacionar();

                    /* </Métodos> */
                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    registros = null;

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, null, tiempoAdicional);
                    break;

                case 8:
                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */

                    act8.RecolectarYRelacionar();

                    /* </Métodos> */
                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, null, tiempoAdicional);
                    break;

                case 9:

                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */
                    act9.Limpiador();

                    /* </Métodos> */

                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, null, tiempoAdicional);
                    break;

                case 10:
                    System.out.println("WIP1");

                    tiempoEjecucion = 0;
                    tiempoAct = 0;
                    tiempoAdicional = 0;
                    inicioEjecucion = System.currentTimeMillis();

                    /* <Métodos> */
                    act10.ManejadorDePeso();

                    /* </Métodos> */

                    archivoToLog = CreateLog();
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    PrintLog(tiempoEjecucion, tiempoAct, archivoToLog, null, tiempoAdicional);

                    break;

                case 11:
                    System.out.println("WIP2");

                    break;

                case 12:
                    System.out.println("WIP3");

                    break;

                case 14:
                    System.out.println("WIP4");

                    break;

                case 15:
                    System.out.println("WIP5");

                    break;

                case 0:
                    System.out.println("Gracias por usar el programa!");
                    break;
                default:
                    System.out.println("Esa opción todavía no ha sido implementada");
                    break;
            }
        }
    }

    public static void PrintLog(long tiempoEjecucion, long tiempoAct, String archivoToLog, TreeMap<File, Long> registros, long tiempoAdicional) {
        try {
            if (!Objects.equals(archivoToLog, "")) {
                FileWriter escritor = new FileWriter(archivoToLog);

                if(registros != null) {
                    for (File key : registros.keySet()) {
                        escritor.write(key + " Tiempo: " + registros.get(key) + " milisegundos\n\n");
                    }
                }

                double valorAct = tiempoAct;
                valorAct /= 1000;
                double valorEjec = tiempoEjecucion;
                valorEjec /= 1000;
                double valorAdc = tiempoAdicional;
                valorAdc /= 1000;

                switch (response) {
                    case 1:
                        escritor.write("\nTiempo total en abrir archivos: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;

                    case 2:
                        escritor.write("\nTiempo total en eliminar todas las etiquetas HTML: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;

                    case 3:
                        if (tiempoAdicional > 0) {
                            escritor.write("Tiempo adicional: " + tiempoAdicional + " milisegundos " + "ó " + valorAdc + " segundos");
                        }
                        escritor.write("\nTiempo total en recolectar todas las palabras y ordenarlas alfabéticamente: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;

                    case 4:
                        if (tiempoAdicional > 0) {
                            escritor.write("Tiempo adicional: " + tiempoAdicional + " milisegundos " + "ó " + valorAdc + " segundos");
                        }
                        escritor.write("\nTiempo total en juntar las palabras de todos los archivos: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;

                    case 5:
                        if (tiempoAdicional > 0) {
                            escritor.write("Tiempo adicional: " + tiempoAdicional + " milisegundos " + "ó " + valorAdc + " segundos");
                        }

                        escritor.write("\nTiempo total en contabilizar las coincidencias de las palabras: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");

                        break;

                    case 6:
                        if (tiempoAdicional > 0) {
                            escritor.write("Tiempo adicional: " + tiempoAdicional + " milisegundos " + "ó " + valorAdc + " segundos");
                        }
                        escritor.write("\nTiempo total en contar el numero de veces que aparece en total y en cuantos archivos aparece: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");

                        break;

                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 14:
                    case 15:
                        break;

                    default:
                        System.out.println("Oh!");
                        break;
                }

                escritor.write("\nTiempo total de ejecución de la aplicación:  " + tiempoEjecucion + " milisegundos " + "ó " + valorEjec + " segundos");
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
            String fileName = placeholder.replaceAll("R", String.valueOf(response));
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
