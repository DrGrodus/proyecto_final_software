import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProyectoSoftware {
    static TreeMap<File, Long> mapa = new TreeMap<File, Long>();
    public static void main(String[] args) {
        Long inicioEjecucion = System.currentTimeMillis();
        final File folder = new File("c:/CS13309_Archivos_HTML/Files");
        List<File> fileList = LectorDirectorios(folder);
        Long tiempoClean = LectorArchivos(fileList);

        Long finalEjecucion = System.currentTimeMillis();
        Long tiempoEjecucion = finalEjecucion - inicioEjecucion;

        Double valorEjec = Double.valueOf(tiempoEjecucion); valorEjec = valorEjec/1000;

        PrintLog(tiempoEjecucion, valorEjec);
    }

    public static void PrintLog (Long tiempoEjecucion, Double valorEjec) {
        try {
            String archivoToLog = CreateLog();
            if (!Objects.equals(archivoToLog, "")) {
                FileWriter escritor = new FileWriter(archivoToLog);

                Iterator<File> itr = mapa.keySet().iterator();
                while (itr.hasNext()) {
                    File key = itr.next();
                    escritor.write(key + " Tiempo: " + mapa.get(key) + " milisegundos\n\n");
                }
                //escritor.write("\nTiempo total en abrir archivos: " + tiempoLect + " milisegundos " + "ó " + valorLect + " segundos");
                escritor.write("\nTiempo total de ejecucion de la aplicación:  " + tiempoEjecucion + " milisegundos " + "ó " + valorEjec + " segundos");
                escritor.close();
            }
        } catch (IOException e) {
            System.out.println("Error de IO");
            e.printStackTrace();
        }
    }

    public static String CreateLog () {
        try {
            File archivo = new File("a2_2802776.txt");
            if(archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe");
            }
            return archivo.toString();
        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();
        }
        return "";
    }

    public static void CreateNewFiles (File name) {
        try {
            File archivo = new File("remove_"+name);
            if(archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
                RemoveHTMLWriteNewFiles(archivo);
            } else {
                System.out.println("El archivo ya existe");
            }
            // return archivo.toString();
        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();
        }
        //return "";
    }

    public static void RemoveHTMLWriteNewFiles (File name) {
        int a = 0;
    }

    public static Long LectorArchivos (List<File> fileList) {
        Long tiempoClean = null;
        try {
            Map<File, Long> registro = new HashMap<>();
            Long inicioClean = null; Long finClean = null;
            inicioClean = System.currentTimeMillis();
            for (File fileIndex : fileList) {
                Long inicio = null;
                Long fin = null;
                Long tiempo = null;
                File objeto = new File(fileIndex.toURI());
                Scanner lector = new Scanner(objeto);
                inicio = System.currentTimeMillis();
                CreateNewFiles(fileIndex);

                while (lector.hasNextLine()) {
                    String dato = lector.nextLine();
                }
                lector.close();
                fin = System.currentTimeMillis();
                tiempo = fin - inicio;
                registro.put(fileIndex, tiempo);
            }
            finClean = System.currentTimeMillis();
            tiempoClean = finClean - inicioClean;

            // Para ordenar los valores
            mapa = new TreeMap<File, Long>(registro);
            Iterator<File> itr = mapa.keySet().iterator();

            while (itr.hasNext()) {
                File key = itr.next();
                System.out.println(key + "\nTiempo: " + registro.get(key) + " milisegundos");
            }
            System.out.println("\n \n");

        } catch (FileNotFoundException e) {
            System.out.println("Error de archivo");
            e.printStackTrace();

        }
        return tiempoClean;
    }

    public static List<File> LectorDirectorios (final File folder) {
        List<File> fileList = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                LectorDirectorios(fileEntry);
            } else {
                fileList.add(new File(fileEntry.toURI()));
            }
        }
        return fileList;
    }
}
