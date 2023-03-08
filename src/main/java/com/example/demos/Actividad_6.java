package com.example.demos;

public class Actividad_6 {

    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private List<String> palabrasPorArchivo;
    private TreeMap<String, Set<Integer, Integer>> cuentaPalabrasPorArchivo;

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

    public void Contabilizar () {
        
        /*
        for (int i = 0; i < getTodasLasPalabras().size(); i++) {
            List<String> aux = (List<String>) getTodasLasPalabras().get(i);
            for (int j = 0; j < aux.size(); j++) {
                todasLP.add(aux.get(j));
            }
        }
        */
        for(int i = 0; i < getpalabras().size(); i++) {
            List<String> aux = (List<String>) getpalabras().get(i);
            for (int j = 0; j < aux.size(); j++) {
                if(cuentaPalabrasPorArchivo.containsKey(aux.contains())){
                    cuentaPalabrasPorArchivo.put(getpalabras().get(i));
                }
                
            }
            
        }

    }


    public void CrearArchivo() {
        Map<File, Long> registro = new HashMap<>();
        try {
            long inicioAct;
            long finAct;
            inicioAct = System.currentTimeMillis();
            //File archivo = new File("UniqueWords/uniqueWords.txt");

            if (archivo.getParentFile().mkdir() || archivo.getParentFile().exists()) {
                if (archivo.createNewFile()) {
                    EscribirArchivo(archivo);
                } else {
                    EscribirArchivo(archivo);
                }
            }
            finAct = System.currentTimeMillis();
            setTiempoAct(finAct - inicioAct);

            registro.put(archivo, getTiempoAct());
            TreeMap<File, Long> mapa = new TreeMap<>(registro);
            setRegistros(mapa);
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }

    public void EscribirArchivo(File archivo) {
        try {
            FileWriter escritor = new FileWriter(archivo);
            for(Map.Entry<String, Integer> entry : getpalabrasUnicas().entrySet()) {
                String palabra = entry.getKey();
                Integer cuenta = entry.getValue();

                //escritor.write(palabra + "; " + cuenta + " veces repetidas.\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }
    
}
