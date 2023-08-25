package tallerimplementacion;

import datos.Vehiculo;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author INTEL i 7
 */
public class Receptor {

    private boolean estoyLibre = true;

    private int counterVehiculos = 0;

    private int tiempoTotal = 0;
    //falta get y set
    private int counter = 0;

    private String response = "el numero de vehiculos atendidos fue de: " + counterVehiculos;

    /**
     * Get the value of response
     *
     * @return the value of response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set the value of response
     *
     * @param response new value of response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    public Receptor() {
    }

    /**
     * Get the value of tiempoTotal
     *
     * @return the value of tiempoTotal
     */
    public void atenderVehiculo(Vehiculo elem) {
        int timeLimit = elem.getTiempo() * 1000;
        counterVehiculos++;

        System.out.println("estoy ocupado " + elem);
        estoyLibre = false;

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(timeLimit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("estoy libre");
            estoyLibre = true;
        });
        thread.start();

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
