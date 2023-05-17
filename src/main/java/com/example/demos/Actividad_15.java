package com.example.demos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class Actividad_15 extends JFrame implements ActionListener {

    static final Actividad_12 act12 = new Actividad_12();

    private TreeMap<String, List<String>> DiccionarioList;
    private File Resultados;

    public TreeMap<String, List<String>> getDiccionarioList() {
        return DiccionarioList;
    }

    public void setDiccionarioList(TreeMap<String, List<String>> diccionarioList) {
        DiccionarioList = diccionarioList;
    }

    public File getResultados() {
        return Resultados;
    }

    public void setResultados(File resultados) {
        Resultados = resultados;
    }

    JButton buscarBoton;
    private JPanel panel1;
    private JFormattedTextField formattedTextField1;
    JTextField buscarPalabra;
    JLabel respuesta;
    JLabel cargando;
    JLabel info;

    public void Interfaz (){
        JFrame frame = new JFrame("Buscador");
        frame.setVisible(true);
        frame.setSize(800,400);
        frame.setLayout(new FlowLayout());

        cargando = new JLabel("Cargando...");
        cargando.setVisible(false);
        frame.add(cargando);


        info = new JLabel("Escribe la palabra que quieres buscar: ");
        frame.add(info);

        buscarPalabra = new JTextField(20);
        frame.add(buscarPalabra);

        buscarBoton = new JButton("Buscar");
        frame.add(buscarBoton);
        buscarBoton.addActionListener(this);

        respuesta = new JLabel("Consulta los resultados en el archivo 'resultados.txt' dentro de la carpeta Actividad_15");
        respuesta.setVisible(false);
        frame.add(respuesta);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buscarBoton){
            cargando.setVisible(true);
            info.setVisible(false);
            act12.BuscadorDePalabras(buscarPalabra.getText());
            Buscar(buscarPalabra.getText());
            cargando.setVisible(false);
            info.setVisible(true);
            respuesta.setVisible(true);
        }
    }

    public void Buscar(String palabra) {
        try {

            String resultados = "Actividad_15/resultados.txt";
            File res = new File(resultados);
            setResultados(res);
            if (res.getParentFile().mkdir() || res.getParentFile().exists()) {
                res.createNewFile();
            }
            setDiccionarioList(act12.getDiccionarioList());

            FileWriter escritorRES = new FileWriter(getResultados());
            if (getDiccionarioList().get(palabra) != null) {
                List<String> coincidencias = getDiccionarioList().get(palabra);
                escritorRES.write("La palabra: " + palabra + " tiene coincidencias en: " + coincidencias.size() + " archivos\n");
                for (String e : coincidencias) {
                    escritorRES.write(e+"\n");
                }
            } else {
                escritorRES.write("No se ha encontrado la palabra: " + palabra);
            }
            escritorRES.close();
            System.out.println("Consulta los resultados en el archivo 'resultados.txt' dentro de la carpeta Actividad_15");
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
