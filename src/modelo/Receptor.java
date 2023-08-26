package modelo;

import datos.Vehiculo;
import javafx.animation.Timeline;

/**
 *
 * @author INTEL i 7
 */
public class Receptor {

    Timeline timeline = new Timeline();
    private boolean estoyLibre = true;
    private int counterVehiculos = 0;
    private int tiempoTotal = 0;
    private String response = "";

    /**
     * Get the value of response
     *
     * @return the value of response
     */
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Receptor() {
    }

    public void sumarVehiculo() {
        counterVehiculos++;
        response = "el numero de vehiculos atendidos fue de: " + counterVehiculos;
    }

    // Atender al vehiculo
    public void atenderVehiculo(Vehiculo elem) {
        int timeWait = elem.getTiempo() * 1000;
        sumarVehiculo();
        System.out.println("estoy ocupado " + elem);
        System.out.println("estoy counter " + counterVehiculos);
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    /**
     * Set the value of tiempoTotal
     *
     * @param tiempoTotal new value of tiempoTotal
     */
    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    /**
     * Get the value of counterVehiculos
     *
     * @return the value of counterVehiculos
     */
    public int getCounterVehiculos() {
        return counterVehiculos;
    }

    /**
     * Set the value of counterVehiculos
     *
     * @param counterVehiculos new value of counterVehiculos
     */
    public void setCounterVehiculos(int counterVehiculos) {
        this.counterVehiculos = counterVehiculos;
    }

    /**
     * Get the value of estoyLibre
     *
     * @return the value of estoyLibre
     */
    public boolean getEstoyLibre() {
        return estoyLibre;
    }

    /**
     * Set the value of estoyLibre
     *
     * @param estoyLibre new value of estoyLibre
     */
    public void setEstoyLibre(boolean estoyLibre) {
        this.estoyLibre = estoyLibre;
    }

}
