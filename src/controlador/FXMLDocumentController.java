package controlador;

import modelo.Receptor;
import datos.Vehiculo;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
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

public class FXMLDocumentController implements Initializable {

    Cola<Vehiculo> colaVehiculos;
    LinkedList<Receptor> listaReceptores = new LinkedList<>();
    private Timeline t;
    public int tiempoTotal;

    String totalVehiculosPorReceptor;

    @FXML
    private TextArea showResponse;
    @FXML
    private TextArea showResults;

    @FXML
    private WebView webView1;

    @FXML
    private void agregarBTN(ActionEvent event) {
        crearVehiculo();
    }

    @FXML
    private void startBTN(ActionEvent event) {

        timer();
        // Cargar la URL en el WebView
        WebEngine webEngine = webView1.getEngine();
        webEngine.load("file:///E:/PS4/pagina_.html"); // Cambia esta URL por la que desees
    }

    @FXML
    private void detenerBTN(ActionEvent event) {

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
            Vehiculo v = FactoryVehiculo.create();
            colaVehiculos.encolar(v);
        }
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
                System.out.println("soy la cola: " + colaVehiculos);
                elem.atenderVehiculo(colaVehiculos.desencolar());

            }
        }
    }

    private void hacerQueTodoFuncione() {
        crearVehiculo();
        System.out.println("cree un vehiculo");
        revisarReceptoresLibres();
        tiempoTotal++;
        //webEngineCola.loadContent(hacerHtmlCola());
        //webEngineReceptores.loadContent(hacerHtmlReceptores());

    }

    private void revisarReceptoresLibres() {
        for (Receptor elem : listaReceptores) {
            if (elem.getEstoyLibre() && !colaVehiculos.estaVacia()) {
                Vehiculo v = colaVehiculos.desencolar();
                elem.setEstoyLibre(false);
                elem.setTiempoOcupado(v.getTiempo());
                elem.setTiempoTotal(v.getTiempo() + elem.getTiempoTotal());
                elem.setCounterVehiculos(elem.getCounterVehiculos() + 1);
            } else {
                if (elem.getTiempoOcupado() > 0) {
                    elem.setTiempoOcupado(elem.getTiempoOcupado() - 1);
                    /* if (elem == listaReceptores.get(0)) {

                    }*/
                }
            }
        }
    }

    public void timer() {

        t = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hacerQueTodoFuncione();
                System.out.println("deberia funcionar");
            }
        }));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        llenarListaReceptores();

    }
}
