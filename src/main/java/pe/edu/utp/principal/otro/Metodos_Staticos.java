package pe.edu.utp.principal.otro;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ESTA CLASE SIRVE PARA EXPORTAR los menu seleccionado por el usuario, ademas sirve para registar y exportar todos los registros del programa
 */

public class Metodos_Staticos {
    /**
     * Clase : REGISTRO, sirve para registrar y exportar los datos, mendiante dos parametos
     */
    private static StringBuilder REGISTROS=new StringBuilder() ;
    public static void GUARDAR_ARCHIVOS(String path, String contenido){
        try {
            File file = new File(path);
            if (!file.exists()){
                if (!file.createNewFile()){
                    return ;
                }
            }
            FileWriter archivo = new FileWriter(file);
            PrintWriter writer = new PrintWriter(archivo);
            writer.print(contenido);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param log_errores
     * La funcion de AGREGAR_REGISTROS, es la de exporta todos los errores y capturar en tiempo real los errores
     */
    public static void AGREGAR_REGISTROS(String log_errores){
        Date fecha_actual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ");

        String fechaHoraFormateada = formato.format(fecha_actual);

        String path ="./src/main/resources/REGISTRO_ERRORES.log";
        REGISTROS.append( fechaHoraFormateada+log_errores+"\n");
        GUARDAR_ARCHIVOS(path,REGISTROS.toString());
    }
}
