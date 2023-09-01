//@author fabian_esteban.lopez@uao.edu.co, Fabian Esteban Lopez Arias ,  Código 2216110
//* @author alejandro.sarria@uao.edu.co, alejandro sarria, Código 2225498
//*  @author jose_ale.burbano@uao.edu.co, jose alejandro burbano, Código 2225498
//* @date 30 /Agosto/2023
//* @version 1.0
package datos;

import java.util.LinkedList;
import modelo.Cola;
import modelo.Receptor;

public class Tools {
/**
* Descripción del método: convertirColaAHtml () Toma la cola y la convierte en una representación de HTML

* @param elemV // Es la cola que se desea convertir en una representación de HTML

* @return retorna una cadena de caracteres que contiene la representación en formato HTML.
*/ 

    public static String convertirColaAHtml(Cola<Carro> elemV) {
        StringBuilder sb = new StringBuilder("<html>");
        Cola<Carro> colaV = OperacionesCola.duplicarCola(elemV);

        sb.append("<noscript>").append("</noscript>");
        if (!colaV.estaVacia()) {
            sb.append("<table align=\"center\"border=\"2\">");
            sb.append("<tr>").append("<th>").append("Vehiculos").append("</th>").append("</tr>");
            do {
                Carro vAUX = colaV.desencolar();
                sb.append("<tr>").append("<td>");
                sb.append(vAUX.getModeloV() + "-");
                sb.append(vAUX.getNombreD() + "-");
                sb.append(vAUX.getTiempo());
                sb.append("</td>").append("</tr>");
            } while (!colaV.estaVacia());
            sb.append("</table>");
        } else {
            sb.append("<h1 align=\"center\">").append("Cola Vacia").append("</h1>");
        }
        sb.append("</html>");
        return sb.toString();
    }

    public static String convertirColaReceptorAHtml(String modelo, String nombre, int tiempo, boolean ocupado) {
        
       
        StringBuilder sb = new StringBuilder("<html>");
        sb.append("<noscript></noscript>");
        sb.append("<table align=\"center\" border=\"2\">");

// Encabezado de la tabla
        sb.append("<tr>");
        sb.append("<th>Datos</th>");
        sb.append("<th>Receptor 1</th>");
        sb.append("<th>Receptor 2</th>");
        sb.append("<th>Receptor 3</th>");
        sb.append("<th>Receptor 4</th>");
        sb.append("</tr>");

// Filas de datos
        // Fila 1
        sb.append("<tr>");
        sb.append("<td>Modelo</td>");
        sb.append("<td>" + modelo + "</td>");
        sb.append("<td>Dato 13</td>");
        sb.append("<td>Dato 14</td>");
        sb.append("<td>Dato 15</td>");
        sb.append("</tr>");

// Fila 2
        sb.append("<tr>");
        sb.append("<td>Nombre</td>");
        sb.append("<td>" + nombre + "</td>");
        sb.append("<td>Dato 23</td>");
        sb.append("<td>Dato 24</td>");
        sb.append("<td>Dato 25</td>");
        sb.append("</tr>");

// Fila 3
        sb.append("<tr>");
        sb.append("<td>Tiempo</td>");
        sb.append("<td>"+ tiempo +"</td>");
        sb.append("<td>Dato 33</td>");
        sb.append("<td>Dato 34</td>");
        sb.append("<td>Dato 35</td>");
        sb.append("</tr>");

// Fila 4
        sb.append("<tr>");
        sb.append("<td>Ocupado</td>");
        sb.append("<td>"+ocupado+"</td>");
        sb.append("<td>Dato 43</td>");
        sb.append("<td>Dato 44</td>");
        sb.append("<td>Dato 45</td>");
        sb.append("</tr>");

        sb.append("</table>");
        sb.append("</html>");

        return sb.toString();

    }

    /*   public static String convertirColaAHtml(Cola<Vehiculo> colaVehiculos, String texto) {
        String html = "<br><center>Se retiro" + texto + "</center></br>";
        StringBuilder sb = new StringBuilder(convertirColaAHtml(colaVehiculos));
        try{
            sb.replace(sb.indexOf("<noscript>"),sb.indexOf("</noscript"),html);
        }catch(StringIndexOutOfBoundsException e){   
        }
        return sb.toString();
    }*/
}
