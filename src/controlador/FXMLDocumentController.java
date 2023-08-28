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
    LinkedList<Receptor> listaReceptores = new LinkedList<>();
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
        listaReceptores.add(new Receptor());
        /*listaReceptor.add(new Receptor());
        listaReceptor.add(new Receptor());
        listaReceptor.add(new Receptor());*/
    }
    
    private void agregarAReceptor() {
        for (Receptor elem : listaReceptores) {
            if (elem.getEstoyLibre() == true && !colaVehiculos.estaVacia()) {
                System.out.println("soy la cola: " + colaVehiculos);
                elem.atenderVehiculo(colaVehiculos.desencolar());
                
            }
        }
    }
    
    public void detenerTimer() {
        timer.cancel();
        timerReceptor.cancel();
        String response = listaReceptores.get(0).getResponse();
        totalVehiculosAtendidos();
        
        showResults.setText(response + "\n" + totalVehiculosPorReceptor);
        System.out.println("Deberia Detenerse.");
        
    }
    
    private void hacerQueTodoFuncione() {
        crearVehiculo();
        revisarReceptoresLibres();
        //tiempoTotal++;
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
            }else{
                if(elem.getTiempoOcupado()>0){
                    elem.setTiempoOcupado(elem.getTiempoOcupado()-1);
                    if(elem==listaReceptores.get(0)){
                        
                    }
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        llenarListaReceptores();
        
    }
}
