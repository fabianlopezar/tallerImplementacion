//* Descripcion de la clase: la clase FXMLDocumentController coordina la simulación de un proceso de atención a vehículos en un taller utilizando JavaFX.
//Controla la simulación, las interacciones de usuario, 
//los cálculos de estadísticas y la generación de informes.
//@author fabian_esteban.lopez@uao.edu.co, Fabian Esteban Lopez Arias ,  Código 2216110
//* @author alejandro.sarria@uao.edu.co, alejandro sarria, Código 2225498
//*  @author jose_ale.burbano@uao.edu.co, jose alejandro burbano, Código 2225498
//*  
//* @date 31 /Agosto/2023
//* @version 1.0
/**
* Descripción del método: startBTN(ActionEvent event): Este método se activa cuando se hace clic en el botón "Start". Llama al método timer() para iniciar la simulación.

*detenerBTN(ActionEvent event): Este método se activa cuando se hace clic en el botón "Detener". Llama al método detenerTimer() para detener la simulación y mostrar resultados.

*totalVehiculosAtendidos(): Este método itera a través de la lista de receptores y calcula el total de vehículos atendidos por cada receptor. Sin embargo, la variable totalVehiculosPorReceptor no se utiliza en ningún lugar del código.

*crearVehiculo(): Genera un número aleatorio de vehículos y los agrega a la cola de vehículos (colaVehiculos).

*llenarListaReceptores(): Llena la lista listaReceptores con instancias de la clase Receptor.

*agregarAReceptor(): Recorre la lista de receptores y si un receptor está libre y hay vehículos en la cola, atiende al siguiente vehículo en la cola.

*hacerQueTodoFuncione(): Llama a los métodos necesarios para simular el paso del tiempo en la simulación. Crea vehículos, actualiza la tabla y revisa la disponibilidad de los receptores.

*revisarReceptoresLibres(): Controla la disponibilidad y el tiempo ocupado de los receptores. Si un receptor está libre y hay un vehículo en la cola, lo atiende.

*timer(): Configura un temporizador que llama al método hacerQueTodoFuncione() periódicamente.

*detenerTimer(): Detiene el temporizador, muestra resultados en el TextArea, almacena resultados en un archivo de texto y actualiza la tabla en el WebView.

*mostrarInforme(): Calcula varias estadísticas relacionadas con la atención de los receptores y las combina en una cadena que se devuelve como informe final.

*hayarNumeroMayor(): Encuentra el receptor que atendió más vehículos y devuelve un reporte sobre ello.

*calcularCantidadVehiculosAtendidosPorCadaReceptor(): Calcula la cantidad de vehículos atendidos por cada receptor y devuelve un informe sobre ello.

*hayarNumeroMenor(): Encuentra el receptor que atendió menos vehículos y devuelve un reporte sobre ello.

*calcularPromedio(): Calcula el tiempo promedio de atención de todos los receptores y devuelve un informe sobre ello.

*calcularTiempoCadaReceptor(): Calcula el tiempo total de atención para cada receptor y devuelve un informe sobre ello.

*calcularCarrosSinAtender(): Está presente como declaración, pero no tiene implementación en el código.

*almacenarEnArchivoTexto(): Almacena el informe en un archivo de texto llamado "datosReceptores.txt".

*actualizarTabla(): Utiliza el método Tools.convertirColaAHtml() para actualizar la tabla en el WebView con la información de la cola de vehículos.

*initialize(URL url, ResourceBundle rb): Inicializa la cola de vehículos, llena la lista de receptores y configura el WebEngine para el WebView en la interfaz gráfica.

* @param colaOriginal// Es la instancia de la clase “Cola” de tipo <T> que se extiende de la clase Base, lo que hace que colaOriginal es la cola que se desea duplicar, y su tipo de elementos debe ser una subclase de Base.

* @return mostrarInforme(): Retorna una cadena de caracteres que contiene un informe con varias estadísticas relacionadas con la atención de los receptores.

* @return hayarNumeroMayor(): Retorna una cadena de caracteres que informa sobre el receptor que atendió más vehículos y la cantidad atendida.

* @return calcularCantidadVehiculosAtendidosPorCadaReceptor(): Retorna una cadena de caracteres que informa sobre la cantidad de carros atendidos por cada receptor.

* @return hayarNumeroMenor(): Retorna una cadena de caracteres que informa sobre el receptor que atendió menos vehículos y la cantidad atendida.

* @return calcularPromedio(): Retorna una cadena de caracteres que informa sobre el tiempo promedio de atención de todos los receptores.

* @return calcularTiempoCadaReceptor(): Retorna una cadena de caracteres que informa sobre el tiempo total de atención para cada receptor.

* @return convertirColaAHtml: Retorna una cadena en formato HTML que representa los elementos de la cola de carros.
*/ 


package controlador;

import datos.Carro;
import datos.Tools;
import modelo.Receptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modelo.Cola;
import java.util.LinkedList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.io.PrintWriter;
import javafx.scene.control.Label;

public class FXMLDocumentController implements Initializable {

    WebEngine web;
    WebEngine web2;
    String html2;
    Cola<Carro> colaVehiculos;
    LinkedList<Receptor> listaReceptores = new LinkedList<>();
    private Timeline t;
    public int tiempoTotal;

    String totalVehiculosPorReceptor;

    @FXML
    private Label infoTXT;
    @FXML
    private Label titleTXT;
    @FXML
    private TextArea showResults;

    @FXML
    private WebView webView1;
    @FXML
    private WebView webView2;

    @FXML
    private void startBTN(ActionEvent event) {
        timer();
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
            Carro v = FactoryVehiculo.create();
            colaVehiculos.encolar(v);

        }
        System.out.println("Soy la cola: " + colaVehiculos);
    }

    /*Llenar lista receptores*/
    private void llenarListaReceptores() {
        for (int i = 0; i < 4; i++) {
            listaReceptores.add(new Receptor());
        }
    }

    private void agregarAReceptor() {
        for (Receptor elem : listaReceptores) {
            if (elem.getEstoyLibre() == true && !colaVehiculos.estaVacia()) {
                System.out.println("soy la cola: " + colaVehiculos.toString());
                elem.atenderVehiculo(colaVehiculos.desencolar());

            }
        }
    }

    private void hacerQueTodoFuncione() {
        crearVehiculo();
        actualizarTabla();
        revisarReceptoresLibres();
        tiempoTotal++;
    }

    private void revisarReceptoresLibres() {
        for (Receptor elem : listaReceptores) {

            if (elem.getEstoyLibre() && !colaVehiculos.estaVacia()) {
                Carro v = colaVehiculos.desencolar();
                elem.setEstoyLibre(false);
                elem.setTiempoOcupado(v.getTiempo());
                elem.setCounterVehiculos(elem.getCounterVehiculos() + 1);
                elem.atenderVehiculo(v);
                html2 = Tools.convertirColaReceptorAHtml(v.getModeloV(), v.getNombreD(), v.getTiempo(), elem.getEstoyLibre());

                System.out.println("Soy el receptor: " + elem + " estoy ocupado " + elem.getTiempoOcupado());
                elem.setTiempoTotal(v.getTiempo() + elem.getTiempoTotal());

            } else {
                if (elem.getTiempoOcupado() > 0) {
                    elem.setTiempoOcupado(elem.getTiempoOcupado() - 1);

                }
                if (elem.getTiempoOcupado() == 0) {
                    elem.setEstoyLibre(true);
                    System.out.println("Soy el receptor: " + elem + " estoy libre");
                }
            }
        }
    }

    public void timer() {

        t = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hacerQueTodoFuncione();

            }
        }));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    public void detenerTimer() {
        t.stop();
        showResults.setText(mostrarInforme());
        almacenarEnArchivoTexto();
        //showResults.setText();

    }
//--------------------- MOSTRAR RESULTADOS -----------------------------------------//

    public String mostrarInforme() {
        String informe = "";
        String inf1 = calcularCantidadVehiculosAtendidosPorCadaReceptor();
        String inf2 = calcularTiempoCadaReceptor();
        String inf3 = hayarNumeroMayor();
        String inf4 = hayarNumeroMenor();
        String inf5 = calcularPromedio();
        informe = inf1 + inf2 + inf3 + inf4 + inf5;
        return informe;
    }

    public String hayarNumeroMayor() {
        String reporte = "";
        int numeroMayor = listaReceptores.get(0).getCounterVehiculos();
        int numeroReceptor = 0;
        for (int i = 0; i < listaReceptores.size(); i++) {
            if (listaReceptores.get(i).getCounterVehiculos() > numeroMayor) {
                numeroMayor = listaReceptores.get(i).getCounterVehiculos();
                numeroReceptor = i;
            }
        }
        reporte = "El receptor " + numeroReceptor + "antendio mas carros , y la cantidad fue de: " + numeroMayor + "\n";
        return reporte;
    }

    public String calcularCantidadVehiculosAtendidosPorCadaReceptor() {
        String reporte = "";
        int i = 0;
        for (Receptor elem : listaReceptores) {
            i++;
            reporte = reporte + "La Cantidad de carros atendidos por el receptor " + i + " fueron de : " + elem.getCounterVehiculos() + "\n";
        }
        return reporte;
    }

    public String hayarNumeroMenor() {
        String reporte = "";
        int numeroMenor = listaReceptores.get(0).getCounterVehiculos();
        int numeroReceptor = 0;
        for (int i = 0; i < listaReceptores.size(); i++) {
            if (listaReceptores.get(i).getCounterVehiculos() < numeroMenor) {
                numeroMenor = listaReceptores.get(i).getCounterVehiculos();
                numeroReceptor = i;
            }
        }
        reporte = "El receptor con menor vehiculos atendidos fue " + numeroReceptor + ", y la cantidad fue de: " + numeroMenor + "\n";
        return reporte;
    }

    public String calcularPromedio() {
        String reporte = "";
        int tiempoTotalReceptores = 0;
        for (Receptor elem : listaReceptores) {
            tiempoTotalReceptores += elem.getTiempoTotal();
        }
        tiempoTotalReceptores = tiempoTotalReceptores / 4;
        reporte = "El tiempo promedio es de : " + tiempoTotalReceptores + "\n";
        return reporte;
    }

    public String calcularTiempoCadaReceptor() {
        String reporte = "";
        for (int i = 0; i < listaReceptores.size(); i++) {
            reporte = reporte + "El receptor #" + i + " atendio un total de: " + listaReceptores.get(i).getTiempoTotal() + "s" + "\n";
        }
        return reporte;
    }

    public String calcularCarrosSinAtender() {
        String reporte = "";
        return reporte;
    }

    public void almacenarEnArchivoTexto() {
        try {
            String nombreArchivo = "datosReceptores.txt";
            PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)));
            String linea = mostrarInforme();
            salida.println(linea);
            salida.close();
        } catch (IOException e) {
            System.out.println("Sucedio un error en almacenarEnArchivoTexto: " + e);
        }
    }

    public void actualizarTabla() {
        String html = Tools.convertirColaAHtml(colaVehiculos);
        //String html2 = Tools.convertirColaReceptorAHtml("toyota", "Fabian", 5);

        web.loadContent(html);
        web2.loadContent(html2);

        //llamar web view2
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaVehiculos = new Cola<>();
        llenarListaReceptores();
        web = webView1.getEngine();
        web2 = webView2.getEngine();
    }
}
