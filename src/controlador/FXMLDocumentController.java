//* Descripcion de la clase: la clase FXMLDocumentController coordina la simulación de un proceso de atención a vehículos en un taller utilizando JavaFX.
//Controla la simulación, las interacciones de usuario, 
//los cálculos de estadísticas y la generación de informes.
//@author fabian_esteban.lopez@uao.edu.co, Fabian Esteban Lopez Arias ,  Código 2216110
//* @author alejandro.sarria@uao.edu.co, alejandro sarria, Código 2225498
//*  @author jose_ale.burbano@uao.edu.co, jose alejandro burbano, Código 2225498
//*  
//* @date 31 /Agosto/2023
//* @version 1.0
package controlador;

import datos.Carro;
import datos.Tools;
import modelo.Receptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modelo.Cola;
import java.util.LinkedList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.io.PrintWriter;
import javafx.scene.control.Label;

public class FXMLDocumentController implements Initializable {

    WebEngine web;
    WebEngine web2;
    String html2;
    Cola<Carro> colaVehiculos;
    LinkedList<Receptor> listaReceptores = new LinkedList<>();
    private Timeline t;
    public int tiempoTotal;

    String totalVehiculosPorReceptor;

    @FXML
    private Label infoTXT;
    @FXML
    private Label titleTXT;
    @FXML
    private TextArea showResults;

    @FXML
    private WebView webView1;
    @FXML
    private WebView webView2;

    @FXML
    private void startBTN(ActionEvent event) {
        timer();
    }

    @FXML
    private void detenerBTN(ActionEvent event) {
        detenerTimer();
    }

    private void totalVehiculosAtendidos() {

        for (int i = 0; i < listaReceptores.size(); i++) {
            totalVehiculosPorReceptor = "receptor " + i + 1 + " atendio: " + listaReceptores.get(i).getCounterVehiculos() + " vehiculos.";
        }
    }

    //Crear Vehiculo
    private void crearVehiculo() {
        int numberVehiculos = (int) (Math.random() * 6);

        for (int i = 0; i < numberVehiculos; i++) {
            Carro v = FactoryVehiculo.create();
            colaVehiculos.encolar(v);

        }
        System.out.println("Soy la cola: " + colaVehiculos);
    }

    /*Llenar lista receptores*/
    private void llenarListaReceptores() {
        for (int i = 0; i < 4; i++) {
            listaReceptores.add(new Receptor());
        }
    }

    private void agregarAReceptor() {
        for (Receptor elem : listaReceptores) {
            if (elem.getEstoyLibre() == true && !colaVehiculos.estaVacia()) {
                System.out.println("soy la cola: " + colaVehiculos.toString());
                elem.atenderVehiculo(colaVehiculos.desencolar());

            }
        }
    }

    private void hacerQueTodoFuncione() {
        crearVehiculo();
        actualizarTabla();
        revisarReceptoresLibres();
        tiempoTotal++;
    }

    private void revisarReceptoresLibres() {
        for (Receptor elem : listaReceptores) {

            if (elem.getEstoyLibre() && !colaVehiculos.estaVacia()) {
                Carro v = colaVehiculos.desencolar();
                elem.setEstoyLibre(false);
                elem.setTiempoOcupado(v.getTiempo());
                elem.setCounterVehiculos(elem.getCounterVehiculos() + 1);
                elem.atenderVehiculo(v);
                html2 = Tools.convertirColaReceptorAHtml(v.getModeloV(), v.getNombreD(), v.getTiempo(), elem.getEstoyLibre());

                System.out.println("Soy el receptor: " + elem + " estoy ocupado " + elem.getTiempoOcupado());
                elem.setTiempoTotal(v.getTiempo() + elem.getTiempoTotal());

            } else {
                if (elem.getTiempoOcupado() > 0) {
                    elem.setTiempoOcupado(elem.getTiempoOcupado() - 1);

                }
                if (elem.getTiempoOcupado() == 0) {
                    elem.setEstoyLibre(true);
                    System.out.println("Soy el receptor: " + elem + " estoy libre");
                }
            }
        }
    }

    public void timer() {

        t = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hacerQueTodoFuncione();

            }
        }));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    public void detenerTimer() {
        t.stop();
        showResults.setText(mostrarInforme());
        almacenarEnArchivoTexto();
        //showResults.setText();

    }
//--------------------- MOSTRAR RESULTADOS -----------------------------------------//

    public String mostrarInforme() {
        String informe = "";
        String inf1 = calcularCantidadVehiculosAtendidosPorCadaReceptor();
        String inf2 = calcularTiempoCadaReceptor();
        String inf3 = hayarNumeroMayor();
        String inf4 = hayarNumeroMenor();
        String inf5 = calcularPromedio();
        informe = inf1 + inf2 + inf3 + inf4 + inf5;
        return informe;
    }

    public String hayarNumeroMayor() {
        String reporte = "";
        int numeroMayor = listaReceptores.get(0).getCounterVehiculos();
        int numeroReceptor = 0;
        for (int i = 0; i < listaReceptores.size(); i++) {
            if (listaReceptores.get(i).getCounterVehiculos() > numeroMayor) {
                numeroMayor = listaReceptores.get(i).getCounterVehiculos();
                numeroReceptor = i;
            }
        }
        reporte = "El receptor " + numeroReceptor + "antendio mas carros , y la cantidad fue de: " + numeroMayor + "\n";
        return reporte;
    }

    public String calcularCantidadVehiculosAtendidosPorCadaReceptor() {
        String reporte = "";
        int i = 0;
        for (Receptor elem : listaReceptores) {
            i++;
            reporte = reporte + "La Cantidad de carros atendidos por el receptor " + i + " fueron de : " + elem.getCounterVehiculos() + "\n";
        }
        return reporte;
    }

    public String hayarNumeroMenor() {
        String reporte = "";
        int numeroMenor = listaReceptores.get(0).getCounterVehiculos();
        int numeroReceptor = 0;
        for (int i = 0; i < listaReceptores.size(); i++) {
            if (listaReceptores.get(i).getCounterVehiculos() < numeroMenor) {
                numeroMenor = listaReceptores.get(i).getCounterVehiculos();
                numeroReceptor = i;
            }
        }
        reporte = "El receptor con menor vehiculos atendidos fue " + numeroReceptor + ", y la cantidad fue de: " + numeroMenor + "\n";
        return reporte;
    }

    public String calcularPromedio() {
        String reporte = "";
        int tiempoTotalReceptores = 0;
        for (Receptor elem : listaReceptores) {
            tiempoTotalReceptores += elem.getTiempoTotal();
        }
        tiempoTotalReceptores = tiempoTotalReceptores / 4;
        reporte = "El tiempo promedio es de : " + tiempoTotalReceptores + "\n";
        return reporte;
    }

    public String calcularTiempoCadaReceptor() {
        String reporte = "";
        for (int i = 0; i < listaReceptores.size(); i++) {
            reporte = reporte + "El receptor #" + i + " atendio un total de: " + listaReceptores.get(i).getTiempoTotal() + "s" + "\n";
        }
        return reporte;
    }

    public String calcularCarrosSinAtender() {
        String reporte = "";
        return reporte;
    }

    public void almacenarEnArchivoTexto() {
        try {
            String nombreArchivo = "datosReceptores.txt";
            PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)));
            String linea = mostrarInforme();
            salida.println(linea);
            salida.close();
        } catch (IOException e) {
            System.out.println("Sucedio un error en almacenarEnArchivoTexto: " + e);
        }
    }

    public void actualizarTabla() {
        String html = Tools.convertirColaAHtml(colaVehiculos);
        //String html2 = Tools.convertirColaReceptorAHtml("toyota", "Fabian", 5);

        web.loadContent(html);
        web2.loadContent(html2);

        //llamar web view2
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        llenarListaReceptores();
        web = webView1.getEngine();
        web2 = webView2.getEngine();
    }
}
