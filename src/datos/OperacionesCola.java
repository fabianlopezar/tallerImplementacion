//@author fabian_esteban.lopez@uao.edu.co, Fabian Esteban Lopez Arias ,  Código 2216110
//* @author alejandro.sarria@uao.edu.co, alejandro sarria, Código 2225498
//*  @author jose_ale.burbano@uao.edu.co, jose alejandro burbano, Código 2225498
package datos;

import modelo.Base;
import modelo.Cola;
/**
* Descripción del método: duplicarCola () Crea dos instancias de la clase Cola<T>, colaAUX y colaDuplicada.
Utiliza un bucle while para vaciar la colaOriginal, desencolando elementos y encolándolos en colaAUX.
Utiliza otro bucle while para vaciar colaAUX, desencolando elementos originales, copiándolos y encolando tanto el original como la copia en sus respectivas colas.
Retorna la colaDuplicada, que ahora contiene copias de los elementos de la cola original.


* @param colaOriginal// Es la instancia de la clase “Cola” de tipo <T> que se extiende de la clase Base, lo que hace que colaOriginal es la cola que se desea duplicar, y su tipo de elementos debe ser una subclase de Base.

* @return retorna la cola duplicada
*/ 

public class OperacionesCola {

    public static < T extends Base> Cola<T> duplicarCola(Cola<T> colaOriginal) {
        Cola<T> colaAUX = new Cola<>();
        Cola<T> colaDuplicada = new Cola<>();
        while (!colaOriginal.estaVacia()) {
            T elem = colaOriginal.desencolar();
            colaAUX.encolar(elem);
        }
        while (!colaAUX.estaVacia()) {
            T elemOriginal = colaAUX.desencolar();
            T elemCopiado = (T) elemOriginal.copy();
            colaOriginal.encolar(elemOriginal);
            colaDuplicada.encolar(elemCopiado);
        }
        return colaDuplicada;
    }
}
