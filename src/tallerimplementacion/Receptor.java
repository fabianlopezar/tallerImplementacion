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

    private int counterVehiculos=0;

    private int tiempoTotal=0;
    //falta get y set
    private int counter = 0;

    public Receptor() {
    }

    /**
     * Get the value of tiempoTotal
     *
     * @return the value of tiempoTotal
     */
    public void atenderVehiculo(Vehiculo elem) {
        int timeLimit = elem.getTiempo();
        estoyLibre = false;
        
        while (counter < timeLimit) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    counter++;
                }
            }, 1000, 1000);
        } 
        estoyLibre = false;
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
