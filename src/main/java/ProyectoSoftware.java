import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProyectoSoftware {
    static TreeMap<File, Long> mapa = new TreeMap<>();
    //static List lista;

    public static void main(String[] args) {
        Long inicioEjecucion = System.currentTimeMillis();
        final File folder = new File("c:/CS13309_Archivos_HTML/Files");
        List<File> fileList = LectorDirectorios(folder);
        Long tiempoClean = LectorArchivos(fileList);

        Long finalEjecucion = System.currentTimeMillis();
        long tiempoEjecucion = finalEjecucion - inicioEjecucion;

        double valorEjec = (double) tiempoEjecucion;
        valorEjec = valorEjec / 1000;
        Double valorClean = Double.valueOf(tiempoClean);
        valorClean = valorClean / 1000;

        PrintLog(tiempoEjecucion, valorEjec, tiempoClean, valorClean);
    }

    public static void PrintLog(Long tiempoEjecucion, Double valorEjec, Long tiempoClean, Double valorClean) {
        try {
            String archivoToLog = CreateLog();
            if (!Objects.equals(archivoToLog, "")) {
                FileWriter escritor = new FileWriter(archivoToLog);

                for (File key : mapa.keySet()) {
                    escritor.write(key + " Tiempo: " + mapa.get(key) + " milisegundos\n\n");
                }



                escritor.write("\nTiempo total en eliminar todas las etiquetas HTML: " + tiempoClean + " milisegundos " + "ó " + valorClean + " segundos");
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
            File archivo = new File("a2_2802776.txt");
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo Log ya existe");
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
        long tiempoClean;
        Map<File, Long> registro = new HashMap<>();
        long inicioClean; long finClean;
        File nameFile;
        inicioClean = System.currentTimeMillis();
        for (File fileIndex : fileList) {
            long inicioC; long finC; long tiempoC;
            inicioC = System.currentTimeMillis();
            nameFile = CreateNewFiles(fileIndex);
            finC = System.currentTimeMillis();
            tiempoC = finC - inicioC;
            registro.put(nameFile, tiempoC);
        }
        finClean = System.currentTimeMillis();
        tiempoClean = finClean - inicioClean;

        // Para ordenar los valores
        mapa = new TreeMap<>(registro);

        for (File key : mapa.keySet()) {
            System.out.println(key + "\nTiempo: " + registro.get(key) + " milisegundos");
        }
        System.out.println("\n \n");

        return tiempoClean;
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
