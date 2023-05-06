package com.example.demos;

import java.io.*;
import java.util.*;

public class Actividad_9 {

    private long tiempoAct;
    private TreeMap<File, Long> registros;
    private File Diccionario;
    private List<TreeMap<String, Integer>> DiccionarioList;
    private File Posting;
    private TreeMap<String, List<String>> PostingList;
    private List<String> stopList;

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

    public File getDiccionario() {
        return Diccionario;
    }

    public void setDiccionario(File diccionario) {
        Diccionario = diccionario;
    }

    public List<TreeMap<String, Integer>> getDiccionarioList() {
        return DiccionarioList;
    }

    public void setDiccionarioList(List<TreeMap<String, Integer>> diccionarioList) {
        DiccionarioList = diccionarioList;
    }

    public File getPosting() {
        return Posting;
    }

    public void setPosting(File posting) {
        Posting = posting;
    }

    public TreeMap<String, List<String>> getPostingList() {
        return PostingList;
    }

    public void setPostingList(TreeMap<String, List<String>> postingList) {
        PostingList = postingList;
    }

    public List<String> getStopList() {
        return stopList;
    }

    public void setStopList(List<String> stopList) {
        this.stopList = stopList;
    }

    public void Limpiador() {
        List<String> stList = new ArrayList<>();
        Collections.addAll(stList, "a", "about", "above", "according", "across", "actually", "adj", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also", "although", "always", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anywhere", "are", "area", "areas", "aren't", "around", "as", "ask", "asked", "asking", "asks", "at", "away", "b", "back", "backed", "backing", "backs", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "began", "begin", "beginning", "behind", "being", "beings", "below", "beside", "besides", "best", "better", "between", "beyond", "big", "billion", "both", "but", "by", "c", "came", "can", "can't", "cannot", "caption", "case", "cases", "certain", "certainly", "clear", "clearly", "co", "come", "could", "couldn't", "d", "did", "didn't", "differ", "different", "differently", "do", "does", "doesn't", "don't", "done", "down", "downed", "downing", "downs", "during", "e", "each", "early", "eg", "eight", "eighty", "either", "else", "elsewhere", "end", "ended", "ending", "ends", "enough", "etc", "even", "evenly", "ever", "every", "everybody", "everyone", "everything", "everywhere", "except", "f", "face", "faces", "fact", "facts", "far", "felt", "few", "fifty", "find", "finds", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "further", "furthered", "furthering", "furthers", "g", "gave", "general", "generally", "get", "gets", "give", "given", "gives", "go", "going", "good", "goods", "got", "great", "greater", "greatest", "group", "grouped", "grouping", "groups", "h", "had", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "he's", "hence", "her", "here", "here's", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "high", "higher", "highest", "him", "himself", "his", "how", "however", "hundred", "i", "i'd", "i'll", "i'm", "i've", "ie", "if", "important", "in", "inc", "indeed", "instead", "interest", "interested", "interesting", "interests", "into", "is", "isn't", "it", "it's", "its", "itself", "j", "just", "kl", "large", "largely", "last", "later", "latest", "latter", "latterly", "least", "less", "let", "let's", "lets", "like", "likely", "long", "longer", "longest", "ltd", "m", "made", "make", "makes", "making", "man", "many", "may", "maybe", "me", "meantime", "meanwhile", "member", "members", "men", "might", "million", "miss", "more", "moreover", "most", "mostly", "mr", "mrs", "much", "must", "my", "myself", "n", "namely", "necessary", "need", "needed", "needing", "needs", "neither", "never", "nevertheless", "new", "newer", "newest", "next", "nine", "ninety", "no", "nobody", "non", "none", "nonetheless", "noone", "nor", "not", "nothing", "now", "nowhere", "number", "numbers", "o", "of", "off", "often", "old", "older", "oldest", "on", "once", "one", "one's", "only", "onto", "open", "opened", "opens", "or", "order", "ordered", "ordering", "orders", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "overall", "own", "p", "part", "parted", "parting", "parts", "per", "perhaps", "place", "places", "point", "pointed", "pointing", "points", "possible", "present", "presented", "presenting", "presents", "problem", "problems", "put", "puts", "q", "quite", "r", "rather", "really", "recent", "recently", "right", "room", "rooms", "s", "said", "same", "saw", "say", "says", "second", "seconds", "see", "seem", "seemed", "seeming", "seems", "seven", "seventy", "several", "she", "she'd", "she'll", "she's", "should", "shouldn't", "show", "showed", "showing", "shows", "sides", "since", "six", "sixty", "small", "smaller", "smallest", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "state", "states", "still", "stop", "such", "sure", "t", "take", "taken", "taking", "ten", "than", "that", "that'll", "that's", "that've", "the", "their", "them", "themselves", "then", "thence", "there", "there'd", "there'll", "there're", "there's", "there've", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "they'd", "they'll", "they're", "they've", "thing", "things", "think", "thinks", "thirty", "this", "those", "though", "thought", "thoughts", "thousand", "three", "through", "throughout", "thru", "thus", "to", "today", "together", "too", "took", "toward", "towards", "trillion", "turn", "turned", "turning", "turns", "twenty", "two");
        setStopList(stList);

        RecolectarYRelacionar();
    }

    public void RecolectarYRelacionar() {
        long inicioAct;
        long finAct;
        long tiempoAct;
        Map<File, Long> registro = new HashMap<>();
        inicioAct = System.currentTimeMillis();

        String Path = new File("").getAbsolutePath().concat("\\Actividad_3");
        final File folderBuscar = new File(Path);

        try {
            File archivo = new File("Actividad_3");
            if (archivo.exists()) {
                inicioAct = System.currentTimeMillis();
                List<File> fileList = LectorDirectorios(folderBuscar);
                CrearArchivos();
                RecolectarPalabras(fileList);
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
        finAct = System.currentTimeMillis();
        tiempoAct = finAct - inicioAct;
    }

    public void CrearArchivos() {
        try {
            String diccionario = "Actividad_9/Diccionario.txt";
            String posting = "Actividad_9/Posting.txt";

            File dcc = new File(diccionario);
            setDiccionario(dcc);
            File ping = new File(posting);
            setPosting(ping);
            if (dcc.getParentFile().mkdir() || dcc.getParentFile().exists()) {
                dcc.createNewFile();
            }
            if (ping.getParentFile().mkdir() || ping.getParentFile().exists()) {
                ping.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RecolectarPalabras(List<File> fileList) {
        try {
            List<List<String>> todasLasPalabras = new ArrayList<>();
            List<String> rechazadas = new ArrayList<>();
            for (File name : fileList) {
                File file = new File(name.toURI());
                // Declarando el objeto de la clase StringBuilder
                BufferedReader buffer = new BufferedReader(new FileReader(file));
                String str;
                List<String> palabrasDelArchivo = new ArrayList<>();
                palabrasDelArchivo.add(name.getName().replaceAll("onlyWords_", ""));


                // Checa la condicional por el método buffer.readLine()
                // mientras sea verdadero el ciclo while correrá
                while ((str = buffer.readLine()) != null) {
                    if (!getStopList().contains(str)) {
                        palabrasDelArchivo.add(str);
                    } else {
                        rechazadas.add(str);
                    }
                }
                todasLasPalabras.add(palabrasDelArchivo);
            }
            Relacionar(todasLasPalabras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Relacionar(List<List<String>> todasLasPalabras) {

        TreeMap<String, Integer> repeticionesGlobales = new TreeMap<>();
        List<TreeMap<String, Integer>> repeticionesPorArchivo = new ArrayList<>();

        TreeMap<String, List<String>> relacionPalabraArchivo = new TreeMap<>();

        List<String> fileNames = new ArrayList<>();
        for (List<String> aux : todasLasPalabras) {
            int i = 0;
            TreeMap<String, Integer> repeticionesPA = new TreeMap<>();
            for (String elem : aux) {
                if (i == 1) {
                    if (repeticionesPA.containsKey(elem)) {
                        repeticionesPA.put(elem, repeticionesPA.get(elem) + 1);
                    } else {
                        repeticionesPA.put(elem, 1);
                    }
                } else {
                    fileNames.add(aux.get(0));
                }
                i = 1;

            }
            repeticionesPorArchivo.add(repeticionesPA);
        }
        int j = 0;

        for (TreeMap<String, Integer> repeticionesPoA : repeticionesPorArchivo) {
            for (Map.Entry<String, Integer> entrada : repeticionesPoA.entrySet()) {
                String palabra = entrada.getKey();
                List<String> aux = new ArrayList<>();

                if (repeticionesGlobales.containsKey(palabra)) {
                    repeticionesGlobales.put(palabra, repeticionesGlobales.get(palabra) + 1);

                    if (!relacionPalabraArchivo.get(palabra).contains(fileNames.get(j))) {
                        aux = relacionPalabraArchivo.get(palabra);
                        aux.add(fileNames.get(j));
                        relacionPalabraArchivo.put(palabra, aux);
                    }

                } else {
                    repeticionesGlobales.put(palabra, 1);
                    aux.add(fileNames.get(j));
                    relacionPalabraArchivo.put(palabra, aux);
                }
            }
            j++;
        }
        setDiccionarioList(repeticionesPorArchivo);
        setPostingList(relacionPalabraArchivo);
        EscribirArchivos(relacionPalabraArchivo, repeticionesPorArchivo);

    }

    public void EscribirArchivos(TreeMap<String, List<String>> relacionPalabraArchivo, List<TreeMap<String, Integer>> repeticionesPorArchivo) {
        try {
            FileWriter escritorDCC = new FileWriter(getDiccionario());
            FileWriter escritorPst = new FileWriter(getPosting());

            // Limpiador
            List<String> rechazadas = new ArrayList<>();
            for (Map.Entry<String, List<String>> entradaRPA : relacionPalabraArchivo.entrySet()) {
                String palabraRPA = entradaRPA.getKey();
                List<String> archivos = entradaRPA.getValue();
                if (archivos.size() <= 2) {
                    rechazadas.add(palabraRPA);
                }
            }

            for (String palabraRechazada : rechazadas) {
                relacionPalabraArchivo.remove(palabraRechazada);
            }

            int j = 1;
            List<Integer> indices = new ArrayList<>();
            indices.add(0);
            // Archivo Posting
            for (TreeMap<String, Integer> repeticionesPoA : repeticionesPorArchivo) {
                for (Map.Entry<String, Integer> entradaPoA : repeticionesPoA.entrySet()) {
                    String palabraPoA = entradaPoA.getKey();
                    Integer coincidencias = entradaPoA.getValue();
                    List<String> tmp = new ArrayList<>();
                    if (relacionPalabraArchivo.get(palabraPoA) != null) {
                        tmp = relacionPalabraArchivo.get(palabraPoA);
                        for (String elem : tmp) {
                            escritorPst.write(elem + "; " + coincidencias + "\n");
                        }
                    }
                    indices.add(tmp.size() + indices.get(j - 1));
                    j++;
                }
            }
            escritorPst.close();

            // Archivo Diccionario
            int k = 0;
            for (Map.Entry<String, List<String>> entradaRPA : relacionPalabraArchivo.entrySet()) {
                String palabraRPA = entradaRPA.getKey();
                List<String> archivos = entradaRPA.getValue();
                escritorDCC.write(palabraRPA + "; " + archivos.size() + "; " + indices.get(k) + "\n");
                k++;
            }
            escritorDCC.close();

        } catch (IOException e) {
            System.out.println("Error IO");
        }
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
