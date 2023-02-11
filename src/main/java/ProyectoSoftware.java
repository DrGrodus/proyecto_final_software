import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProyectoSoftware {
    static TreeMap<File, Long> mapa = new TreeMap<>();

    public static void main(String[] args) {


        System.out.println("Bienvenido!");
        String Path = new File("").getAbsolutePath().concat("\\CS13309_Archivos_HTML\\Files");

        final File folder = new File(Path);
        List<File> fileList = LectorDirectorios(folder);
        Scanner scan = new Scanner(System.in);
        int response = 1;


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

                    long tiempoLect = LectorArchivos(fileList);
                    CreateLog(response);

                    long finalEjecucion = System.currentTimeMillis();
                    long tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    double valorLect = tiempoLect;
                    valorLect = valorLect / 1000;
                    double valorEjec = tiempoEjecucion;
                    valorEjec = valorEjec / 1000;

                    PrintLog(tiempoEjecucion, tiempoLect, valorEjec, valorLect, response);

                    break;
                case 2:
                    System.out.println("WIP");

                    inicioEjecucion = System.currentTimeMillis();

                    Long tiempoClean = LectorArchivos(fileList);
                    //CreateLog(response);

                    finalEjecucion = System.currentTimeMillis();
                    tiempoEjecucion = finalEjecucion - inicioEjecucion;

                    double valorClean = tiempoClean;
                    valorClean = valorClean / 1000;
                    valorEjec = tiempoEjecucion;
                    valorEjec = valorEjec / 1000;

                    PrintLog(tiempoEjecucion, tiempoClean, valorEjec, valorClean, response);

                    CreateLog(response);

                    break;
                case 3:
                    System.out.println("WIP2");

                    CreateLog(response);

                    break;
                case 4:
                    System.out.println("WIP3");

                    CreateLog(response);
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
    public static void PrintLog(long tiempoEjecucion, long tiempoAct, Double valorEjec, Double valorAct, int response) {
        try {
            String archivoToLog = CreateLog(response);
            if (!Objects.equals(archivoToLog, "")) {
                FileWriter escritor = new FileWriter(archivoToLog);

                for (File key : mapa.keySet()) {
                    escritor.write(key + " Tiempo: " + mapa.get(key) + " milisegundos\n\n");
                }
                switch (response){
                    case 1:
                        escritor.write("\nTiempo total en abrir archivos: " + tiempoAct + " milisegundos " + "ó " + valorAct + " segundos");
                        break;
                    case 2:
                        System.out.println("WIP");
                        //escritor.write("\nTiempo total en eliminar todas las etiquetas HTML: " + tiempoClean + " milisegundos " + "ó " + valorClean + " segundos");

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

                escritor.write("\nTiempo total de ejecucion de la aplicación:  " + tiempoEjecucion + " milisegundos " + "ó " + valorEjec + " segundos");
                escritor.close();
            }
        } catch (IOException e) {
            System.out.println("Error de IO");
            e.printStackTrace();
        }
    }

    public static String CreateLog(int number) {
        try {
            String placeholder = "aR_2802776.txt";
            String fileName = placeholder.replace('R',Character.forDigit(number,10));
            File archivo = new File(fileName);
            if(archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName()+"\n\n");
            } else {
                System.out.println("El archivo Log ya existe\n\n");
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
        try {
            File file = new File(name.toURI());
            Scanner lector = new Scanner(file);
            FileWriter escritor = new FileWriter(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                linea = linea.replaceAll("<[^>]*>", "");
                escritor.write(linea);
            }
            escritor.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static Long LectorArchivos(List<File> fileList) {
        Long tiempoLect = null;
        try {
            Map<File, Long> registro = new HashMap<>();
            long inicioLect;
            long finLect;
            inicioLect = System.currentTimeMillis();
            for (File fileIndex : fileList) {
                long inicio;
                long fin;
                long tiempo;
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
            finLect = System.currentTimeMillis();
            tiempoLect = finLect - inicioLect;

            // Para ordenar los valores
            mapa = new TreeMap<File, Long>(registro);

            System.out.println("\n \n");

        } catch (FileNotFoundException e) {
            System.out.println("Error de archivo");
            e.printStackTrace();

        }
        return tiempoLect;
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
