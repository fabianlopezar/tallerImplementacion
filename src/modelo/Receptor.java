//* Descripcion de la clase: la clase Receptor modela un receptor en un proceso de atención a vehículos. 
//Mantiene un registro de los vehículos atendidos, su estado de ocupación y el tiempo total de atención.
//Los métodos permiten simular la atención a vehículos y actualizar información relacionada.
//@author fabian_esteban.lopez@uao.edu.co, Fabian Esteban Lopez Arias ,  Código 2216110
//* @author alejandro.sarria@uao.edu.co, alejandro sarria, Código 2225498
//*  @author jose_ale.burbano@uao.edu.co, jose alejandro burbano, Código 2225498
//* @date 30 /Agosto/2023
//* @version 1.0
package modelo;

import datos.Carro;
//*/
//* Descripción del método: duplicarCola () Crea dos instancias de la clase Cola<T>, colaAUX y colaDuplicada.
//Utiliza un bucle while para vaciar la colaOriginal, desencolando elementos y encolándolos en colaAUX.
//Utiliza otro bucle while para vaciar colaAUX, desencolando elementos originales, copiándolos y encolando tanto el original como la copia en sus respectivas colas.
//Retorna la colaDuplicada, que ahora contiene copias de los elementos de la cola original.
//
//
//* @param colaOriginal// Es la instancia de la clase “Cola” de tipo <T> que se extiende de la clase Base, lo que hace que colaOriginal es la cola que se desea duplicar, y su tipo de elementos debe ser una subclase de Base.
//
//* @return retorna la cola duplicada
//*/ 

public class Receptor {

    private boolean estoyLibre = true;
    private int counterVehiculos = 0;
    private int tiempoTotal = 0;

    private int tiempoOcupado;

    /**
     * Get the value of tiempoOcupado
     *
     * @return the value of tiempoOcupado
     */
    public int getTiempoOcupado() {
        return tiempoOcupado;
    }

    /**
     * Set the value of tiempoOcupado
     *
     * @param tiempoOcupado new value of tiempoOcupado
     */
    public void setTiempoOcupado(int tiempoOcupado) {
        this.tiempoOcupado = tiempoOcupado;
    }

    /**
     * Get the value of response
     *
     * @return the value of response
     */
    public Receptor() {
    }

    public void sumarVehiculo() {
        counterVehiculos++;
    }

    // Atender al vehiculo
    public Carro atenderVehiculo(Carro elem) {
        return elem;
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
