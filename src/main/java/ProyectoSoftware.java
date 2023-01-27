import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProyectoSoftware {

    public static void main(String[] args) {
        try {
            File folder = new File("./CS13309_Archivos_HTML/Files/");
            List<File> fileList = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
            for(File fileEntry : folder.listFiles()){
                if(fileEntry.isDirectory()){
                    list

                }

            }

        } catch (NullPointerException e) {

        }
    }
}
