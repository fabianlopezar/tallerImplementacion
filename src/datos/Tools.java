package datos;

import modelo.Cola;

public class Tools {

    public static String convertirColaAHtml(Cola<Carro> elemV) {
        StringBuilder sb = new StringBuilder("<html>");
        Cola<Carro> colaV = OperacionesCola.duplicarCola(elemV);

        sb.append("<noscript>").append("</noscript>");
        if (!colaV.estaVacia()) {
            sb.append("<table align=\"center\"border=\"2\">");
            sb.append("<tr>").append("<th>").append("Vehiculos").append("<\th>").append("<\tr>");
            do {
                Carro vAUX = colaV.desencolar();
                sb.append("<tr>").append("<td>");
                sb.append(vAUX.getModeloV() + "-");
                sb.append(vAUX.getNombreD() + "-");
                sb.append(vAUX.getTiempo() );
                sb.append("</td>").append("</tr>");
            } while (!colaV.estaVacia());
            sb.append("</table>");
        } else {
            sb.append("<h1 align=\"center\">").append("Cola Vacia").append("</h1>");
        }
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
