import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProyectoSoftware {
    public static void main(String[] args) {
        Long inicioEjecucion = System.currentTimeMillis();
        final File folder = new File("c:/CS13309_Archivos_HTML/Files");
        List<File> fileList = LectorDirectorios(folder);
        Long tiempoLect = LectorArchivos(fileList);

        Long finalEjecucion = System.currentTimeMillis();
        Long tiempoEjecucion = finalEjecucion - inicioEjecucion;
        Double valorLect = Double.valueOf(tiempoLect); valorLect = valorLect/1000;
        Double valorEjec = Double.valueOf(tiempoEjecucion); valorEjec = valorEjec/1000;

        System.out.println("Tiempo total en abrir archivos: " + tiempoLect + " milisegundos " + "ó " + valorLect + " segundos");
        System.out.println("Tiempo total de ejecucion de la aplicación:  " + tiempoEjecucion + " milisegundos " + "ó " + valorEjec + " segundos");
    }


    public static Long LectorArchivos (List<File> fileList){
        Long tiempoLect = null;
        try {
            Map<File, Long> registro = new HashMap<>();
            Long inicioLect = null;
            Long finLect = null;
            inicioLect = System.currentTimeMillis();
            for (File fileIndex : fileList) {
                Long inicio = null;
                Long fin = null;
                Long tiempo = null;
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
            TreeMap<File, Long> mapa = new TreeMap<File, Long>(registro);
            Iterator<File> itr = mapa.keySet().iterator();

            while (itr.hasNext()){
                File key = itr.next();
                System.out.println(key + "\nTiempo: " + registro.get(key) + " milisegundos");
            }
            System.out.println("\n \n");

        } catch (FileNotFoundException e) {
            System.out.println("Error de archivo");
            e.printStackTrace();

        }
        return tiempoLect;
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
