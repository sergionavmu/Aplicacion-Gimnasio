package View;

public class Interfaz {

    public static String generarHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("\t<body>");
        html.append("\t\t<h2>PANTALLA DE ADMIN</h2>");
        html.append("\t\t<form method=\"post\" action=\"/usuario\">");
        html.append("\t\t\t<input type=\"text\" name=\"usuari\" placeholder=\"Usuari\"><br>");
        html.append("\t\t\t<input type=\"text\" name=\"dataAlta\" placeholder=\"Data Alta\"><br>");
        html.append("\t\t\t<input type=\"submit\" value=\"Crear Usuario\">");
        html.append("\t\t</form>");
        html.append("\t\t<p>Reserves:</p>");
        html.append("\t\t\t<p>1 Classe de Yoga 22/09/2023 portar esterilla</p>");
        html.append("\t\t\t<p>2,Classe de Body Pump,21/10/2023,,COMPLETAT</p>");
        html.append("\t</body>");
        html.append("</html>");

        return html.toString();
    }

}
