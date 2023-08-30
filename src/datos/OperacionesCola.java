package datos;

import modelo.Base;
import modelo.Cola;

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
