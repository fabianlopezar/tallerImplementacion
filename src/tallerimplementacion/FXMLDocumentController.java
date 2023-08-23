package tallerimplementacion;

import datos.Vehiculo;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modelo.Cola;
import javafx.scene.control.Label;
import java.util.LinkedList;

/**
 *
 *
 * Controller class for FXMLDocument.fxml
 */
public class FXMLDocumentController implements Initializable {

    Cola<Vehiculo> colaVehiculos;

    LinkedList<Receptor> listaReceptor = new LinkedList<>();

    @FXML
    private Label estoyLibre1;
    @FXML
    private Label estoyLibre2;
    @FXML
    private Label estoyLibre3;
    @FXML
    private Label estoyLibre4;

    @FXML
    private TextArea showResponse;

    @FXML
    private WebView webView1;

    @FXML
    private void agregarBTN(ActionEvent event) {
        crearVehiculo();

    }

    @FXML
    private void startBTN(ActionEvent event) {

        timer();
        timerReceptor();

        // Cargar la URL en el WebView
        WebEngine webEngine = webView1.getEngine();
        webEngine.load("file:///E:/PS4/pagina_.html"); // Cambia esta URL por la que desees
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

    private void showBoolean() {
        //String.valueOf(valorBooleano);
        // estoyLibre1.setText("hola");
        /*estoyLibre2.setText(String.valueOf(listaReceptor.get(0).getEstoyLibre()));
        estoyLibre3.setText(String.valueOf(listaReceptor.get(0).getEstoyLibre()));
        estoyLibre4.setText(String.valueOf(listaReceptor.get(0).getEstoyLibre()));*/
    }

    private void agregarAReceptor() {
        //System.out.println("en ejecucion");
        for (Receptor elem : listaReceptor) {
            if (elem.getEstoyLibre() == true && !colaVehiculos.estaVacia()) {
                //debo eliminar el primero y agregarlo al receptor.

                //System.out.println(colaVehiculos.estaVacia());
                System.out.println("soy la cola: " + colaVehiculos);
                elem.atenderVehiculo(colaVehiculos.desencolar());

            }
        }
    }

    private void timer() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                crearVehiculo();
                showBoolean();
                showResponse.setText(colaVehiculos.toString());

            }
        }, 1000, 2000);//deberia ser random
    }

    private void timerReceptor() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {

                agregarAReceptor();

            }
        }, 1000, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        llenarListaReceptores();

    }
}
