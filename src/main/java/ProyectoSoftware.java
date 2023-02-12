import java.io.*;
import java.util.*;

public class ProyectoSoftware {
    static TreeMap<File, Long> mapa = new TreeMap<>();
    static int response = 1;

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
                    "\n 0.- Salir" +
                    "");
            response = scan.nextInt();

            switch (response) {
                case 1:
                    long inicioEjecucion = System.currentTimeMillis();

                    long tiempoLect = LectorArchivos(fileList, response);
                    archivoToLog = CreateLog();

                    long finalEjecucion = System.currentTimeMillis();
                    long tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    double valorLect = tiempoLect;
                    valorLect = valorLect / 1000;
                    double valorEjec = tiempoEjecucion;
                    valorEjec = valorEjec / 1000;

                    PrintLog(tiempoEjecucion, tiempoLect, valorEjec, valorLect, archivoToLog);

                    break;
                case 2:
                    inicioEjecucion = System.currentTimeMillis();
                    long tiempoClean = LectorArchivos(fileList, response);
                    archivoToLog = CreateLog();

                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    double valorClean = tiempoClean;
                    valorClean = valorClean / 1000;
                    valorEjec = tiempoEjecucion;
                    valorEjec = valorEjec / 1000;

                    PrintLog(tiempoEjecucion, tiempoClean, valorEjec, valorClean, archivoToLog);
                    break;
                case 3:
                    System.out.println("WIP2");
                    inicioEjecucion = System.currentTimeMillis();
                    //Long tiempoClean = LectorArchivos(fileList, response);

                    // Metodo

                    long tiempoBuscar = 0;
                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    double valorBuscar = tiempoBuscar;
                    valorBuscar = valorBuscar / 1000;
                    valorEjec = tiempoEjecucion;
                    valorEjec = valorEjec / 1000;

                    archivoToLog = CreateLog();

                    //PrintLog(tiempoEjecucion, tiempoBuscar, valorEjec, valorBuscar, archivoToLog);
                    break;
                case 4:
                    System.out.println("WIP3");

                    archivoToLog = CreateLog();
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

    public static void PrintLog(long tiempoEjecucion, long tiempoAct, Double valorEjec, Double valorAct, String archivoToLog) {
        try {
            if (!Objects.equals(archivoToLog, "")) {
                FileWriter escritor = new FileWriter(archivoToLog);

                for (File key : mapa.keySet()) {
                    escritor.write(key + " Tiempo: " + mapa.get(key) + " milisegundos\n\n");
                }
                switch (response) {
                    case 1:
                        escritor.write("\nTiempo total en abrir archivos: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;
                    case 2:
                        escritor.write("\nTiempo total en eliminar todas las etiquetas HTML: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");

                        break;
                    case 3:
                        System.out.println("WIP2");
                        escritor.write("\nTiempo total en recolectar todas las palabras y ordenarlas alfabeticamente: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");

                        break;
                    case 4:
                        System.out.println("WIP3");

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
            File archivo = new File("RemovedHTML/remove_HTML" + name.getName());
            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    RemoveHTMLWriteNewFiles(name, archivo);
                } else {
                    RemoveHTMLWriteNewFiles(name, archivo);
                }
            } else {
                throw new IOException("Error al crear un directorio " + archivo.getParent());
            }
            return archivo;
        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();
        }
        return null;
    }

    public static void RemoveHTMLWriteNewFiles(File name, File archivo) {

        /* <Recurso adaptado> */
        // https://www.geeksforgeeks.org/java-program-to-read-a-file-to-string/
        // el bloque try es para checar las excepciones cuando
        // un objeto de la clase BufferedReader es creado
        // para leer la direccion del archivo
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
            texto = texto.replaceAll("<[^>]*>", "");
            escritor.write(texto);
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* </Recurso adaptado> */

    }

    public static void RecolectorPalabras (){

    }

    public static void OrdenarPalabras () {

    }


    public static Long LectorArchivos(List<File> fileList, int response) {
        Map<File, Long> registro = new HashMap<>();
        long inicioAct;
        long finAct;
        long tiempoAct = 0;
        try {
            switch (response) {
                case 1:
                    inicioAct = System.currentTimeMillis();
                    long auxtemp = 0;
                    for (File fileIndex : fileList) {
                        long inicio; long fin; long tiempo;
                        File objeto = new File(fileIndex.toURI());
                        Scanner lector = new Scanner(objeto);
                        inicio = System.currentTimeMillis();
                        while (lector.hasNextLine()) {
                            String dato = lector.nextLine();
                        }
                        lector.close();
                        fin = System.currentTimeMillis();
                        tiempo = fin - inicio;
                        registro.put(fileIndex, tiempo);
                    }
                    finAct = System.currentTimeMillis();
                    tiempoAct = finAct - inicioAct;
                    break;
                case 2:
                    File nameFile;
                    inicioAct = System.currentTimeMillis();
                    for (File fileIndex : fileList) {
                        long inicio; long fin; long tiempo;
                        inicio = System.currentTimeMillis();
                        nameFile = CreateNewFiles(fileIndex);
                        fin = System.currentTimeMillis();
                        tiempo = fin - inicio;
                        registro.put(nameFile, tiempo);
                    }
                    finAct = System.currentTimeMillis();
                    tiempoAct = finAct - inicioAct;
                    break;
                case 3:
                    System.out.println("WIP2");
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

        } catch (FileNotFoundException e) {
            System.out.println("Error de archivo");
            e.printStackTrace();

        }
        return tiempoAct;
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
