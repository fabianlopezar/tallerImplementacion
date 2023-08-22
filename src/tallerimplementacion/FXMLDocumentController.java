/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerimplementacion;

import datos.Vehiculo;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import modelo.Cola;

/**
 *
 * @author fabian_esteban.lopez
 */
public class FXMLDocumentController implements Initializable {

    Cola<Vehiculo> colaVehiculos;
    
    @FXML
    private TextArea showResponse;
   
    /*Crear Vehiculo*/
    private void crearVehiculo() {
        int minYear = 2000;
        int maxYear = 2004;
        int randomYear = (int) (Math.random() * (maxYear - minYear + 1));
        String modeloV = String.valueOf(randomYear);
        String nombreD = "David";
        boolean estadoVehiculo = true;
        int minTime = 1;
        int maxTime = 5;
        int tiempo = (int) (Math.random() * (maxTime - minTime + 1));

        colaVehiculos.encolar(new Vehiculo(modeloV, nombreD, estadoVehiculo, tiempo));
    }

    private void timer() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                crearVehiculo();
                showResponse.setText(colaVehiculos.toString());
                System.out.println("deberia funcionar.");
            }
        },1000,2000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        timer();
    }

}
