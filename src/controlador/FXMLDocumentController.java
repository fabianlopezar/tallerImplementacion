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

/**
 *
 *
 * Controller class for FXMLDocument.fxml
 */
public class FXMLDocumentController implements Initializable {

    Cola<Vehiculo> colaVehiculos;
    LinkedList<Receptor> listaReceptor = new LinkedList<>();
    Timer timer = new Timer();
    Timer timerReceptor = new Timer();

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

        // Cargar la URL en el WebView
        WebEngine webEngine = webView1.getEngine();
        webEngine.load("file:///E:/PS4/pagina_.html"); // Cambia esta URL por la que desees
    }

    @FXML
    private void detenerBTN(ActionEvent event) {
        detenerTimer();
    }

    private void totalVehiculosAtendidos() {

        for (int i = 0; i < listaReceptor.size(); i++) {
            totalVehiculosPorReceptor = "receptor " + i + 1 + " atendio: " + listaReceptor.get(i).getCounterVehiculos() + " vehiculos.";
        }
    }

    //Crear Vehiculo
    private void crearVehiculo() {
        int minYear = 2000;
        int maxYear = 2024;
        int randomYear = (int) (Math.random() * (maxYear - minYear + 1));
        String modeloV = String.valueOf(randomYear);
        String nombreD = "David";
        boolean estadoVehiculo = true;
        int minTime = 1;
        int maxTime = 5;
        int tiempo = (int) (Math.random() * (maxTime - minTime + 1));

        colaVehiculos.encolar(new Vehiculo(modeloV, nombreD, estadoVehiculo, tiempo));
    }

    /*Llenar lista receptores*/
    private void llenarListaReceptores() {
        listaReceptor.add(new Receptor());
        /*listaReceptor.add(new Receptor());
        listaReceptor.add(new Receptor());
        listaReceptor.add(new Receptor());*/
    }

    private void agregarAReceptor() {
        for (Receptor elem : listaReceptor) {
            if (elem.getEstoyLibre() == true && !colaVehiculos.estaVacia()) {
                System.out.println("soy la cola: " + colaVehiculos);
                elem.atenderVehiculo(colaVehiculos.desencolar());

            }
        }
    }

    public void detenerTimer() {
        timer.cancel();
        timerReceptor.cancel();
        String response = listaReceptor.get(0).getResponse();
        totalVehiculosAtendidos();

        showResults.setText(response + "\n" + totalVehiculosPorReceptor);
        System.out.println("Deberia Detenerse.");

    }

    private void hacerQueTodoFuncione() {
        crearVehiculo();
        //revisarReceptoresLibres();
        //tiempoTotal++;
        //webEngineCola.loadContent(hacerHtmlCola());
        //webEngineReceptores.loadContent(hacerHtmlReceptores());
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        llenarListaReceptores();

    }
}
