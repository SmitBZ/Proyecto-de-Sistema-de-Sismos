package pe.edu.utp.principal.otro;
import java.io.IOException;
/**
 * @author Cesar Abel Bustamante Inoach
 * @author Fabricio Arturo Elias Gonzales
 * @author Huilla Rosillo Jesús Antonio
 * @author Bocanegra Zurita Roberth Smit
 * @author Ruiz Oblitas Moisés Alejandro
 * @author Angel Arcadio Cespedes Palomino
 */
public class MenuPrincipal {
    /**
     * @param args
     * @throws IOException Si es que pasa un problema al monento de la ejecucacion del programa, este sera capturado
     * Reportes.LLAMADA_MENU() su funcion es la de llamar a todas clases que estar sujetas a esta
     */
    public static void main(String[] args) throws IOException {
        //ESTA ES LA CLASE PRINCIPAL, DESDE DONDE SE LLAMA AL PROYECTO
        Reportes.LLAMADA_MENU();
    }
}
